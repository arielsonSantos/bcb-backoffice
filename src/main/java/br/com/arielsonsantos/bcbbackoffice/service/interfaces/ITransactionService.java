package br.com.arielsonsantos.bcbbackoffice.service.interfaces;

import java.util.List;

import br.com.arielsonsantos.bcbbackoffice.dto.message.CreateMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.transaction.CreateDepositTransactionDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;

public interface ITransactionService {

    List<BCBTransaction> findAll();

    BCBTransaction findById(final Long id);

    BCBTransaction createDepositTransaction(final CreateDepositTransactionDTO transactionDTO);

    BCBTransaction createSMSTransaction(final CreateMessageDTO messageDTO);

    BCBTransaction createWhatsappTransaction(final CreateMessageDTO messageDTO);
}
