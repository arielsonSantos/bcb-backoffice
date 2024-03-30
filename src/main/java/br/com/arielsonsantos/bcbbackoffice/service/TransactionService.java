package br.com.arielsonsantos.bcbbackoffice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.CustomerIdentityDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.message.CreateMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.transaction.CreateDepositTransactionDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;
import br.com.arielsonsantos.bcbbackoffice.entity.Balance;
import br.com.arielsonsantos.bcbbackoffice.enums.TransactionType;
import br.com.arielsonsantos.bcbbackoffice.exception.InsufficientFundsException;
import br.com.arielsonsantos.bcbbackoffice.exception.ResourceNotFoundException;
import br.com.arielsonsantos.bcbbackoffice.repository.TransactionRepository;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IBCBCustomerService;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.ITransactionService;

@Service
@Transactional
@Qualifier("transactionService")
public class TransactionService implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    @Qualifier("customerService")
    private IBCBCustomerService bcbCustomerService;

    @Value("${bcb.message.sms.cost}")
    private Double smsCost;

    @Value("${bcb.message.whatsapp.cost}")
    private Double whatsappCost;

    @Override
    @Transactional(readOnly = true)
    public List<BCBTransaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BCBTransaction findById(final Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transaction '%s' not found!".formatted(id)));
    }

    @Override
    public BCBTransaction createDepositTransaction(final CreateDepositTransactionDTO transactionDTO) {
        final BCBTransaction transaction = transactionDTO.toEntity();

        final Balance balance = updateBalance(transactionDTO, transaction);

        transaction.setBalance(balance);

        return transactionRepository.save(transaction);
    }

    @Override
    public BCBTransaction createSMSTransaction(final CreateMessageDTO messageDTO) {
        final BCBTransaction transaction = new BCBTransaction();

        transaction.setType(TransactionType.sms);
        transaction.setValue(smsCost);

        final Balance balance = updateBalance(messageDTO, transaction);

        transaction.setBalance(balance);

        return transaction;
    }

    @Override
    public BCBTransaction createWhatsappTransaction(final CreateMessageDTO messageDTO) {
        final BCBTransaction transaction = new BCBTransaction();

        transaction.setType(TransactionType.whatsapp);
        transaction.setValue(whatsappCost);

        final Balance balance = updateBalance(messageDTO, transaction);

        transaction.setBalance(balance);

        return transaction;
    }

    private Balance updateBalance(final CustomerIdentityDTO customerIdentityDTO, final BCBTransaction transaction) {
        final Balance balance = bcbCustomerService.findById(customerIdentityDTO.getCustomerId()).getBalance();

        if (!TransactionType.deposit.equals(transaction.getType()))
            checkBalance(balance, transaction);

        bcbCustomerService.updateAmount(balance, transaction.getValue());
        balance.getTransactions().add(transaction);

        return balance;
    }

    private void checkBalance(final Balance balance, final BCBTransaction transaction) {
        if (Double.sum(balance.getAmount(), transaction.getValue()) < balance.getQuota() * -1)
            throw new InsufficientFundsException(balance, transaction);
    }
}
