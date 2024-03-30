package br.com.arielsonsantos.bcbbackoffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.CreateCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.InputCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.UpdateCustomerQuotaDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;
import br.com.arielsonsantos.bcbbackoffice.entity.Balance;
import br.com.arielsonsantos.bcbbackoffice.entity.Company;
import br.com.arielsonsantos.bcbbackoffice.exception.ResourceNotFoundException;
import br.com.arielsonsantos.bcbbackoffice.exception.UniqueIdentifierViolationException;
import br.com.arielsonsantos.bcbbackoffice.repository.BCBCustomerRepository;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IBCBCustomerService;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.ICompanyService;

@Service
@Transactional
@Qualifier("customerService")
public class BCBCustomerService implements IBCBCustomerService {

    @Autowired
    private BCBCustomerRepository bcbCustomerRepository;

    @Autowired
    @Qualifier("companyService")
    private ICompanyService companyService;

    @Override
    @Transactional(readOnly = true)
    public List<BCBCustomer> findAll() {
        return bcbCustomerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BCBCustomer findById(final Long id) {
        return bcbCustomerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer '%s' not found!".formatted(id)));
    }

    @Override
    public BCBCustomer createCustomer(final CreateCustomerDTO customerDTO) {
        if (bcbCustomerRepository.findByCpf(customerDTO.getCpf()).isPresent())
            throw new UniqueIdentifierViolationException("Customer with CPF %s already exists".formatted(customerDTO.getCpf()));

        final BCBCustomer newCustomer = customerDTO.toEntity();
        newCustomer.setCompany(getCustomerCompanyFromDTO(customerDTO));

        createBalanceForCustomer(newCustomer, customerDTO.getInitialBalance(), customerDTO.getInitialQuota());

        return bcbCustomerRepository.save(newCustomer);
    }

    private Company getCustomerCompanyFromDTO(final InputCustomerDTO customerDTO) {
        return companyService.findById(customerDTO.getCompanyId());
    }

    private void createBalanceForCustomer(final BCBCustomer customer, final Double initialBalance, final Double initialQuota) {
        final Balance balance = new Balance();

        balance.setAmount(initialBalance);
        balance.setQuota(initialQuota);
        balance.setCustomer(customer);

        customer.setBalance(balance);
    }

    @Override
    public void updateAmount(final Balance balance, final Double amount) {
        balance.setAmount(Double.sum(balance.getAmount(), amount));
    }

    @Override
    public BCBCustomer updateCustomer(final Long id, final InputCustomerDTO customerDTO) {
        final Optional<BCBCustomer> optionalCustomer = bcbCustomerRepository.findByCpf(customerDTO.getCpf());
        if (optionalCustomer.isPresent() && !optionalCustomer.get().getId().equals(id))
            throw new UniqueIdentifierViolationException("Customer with CPF %s already exists".formatted(customerDTO.getCpf()));

        return bcbCustomerRepository.save(merge(customerDTO, findById(id)));
    }

    private BCBCustomer merge(final InputCustomerDTO customerDTO, final BCBCustomer persitedCustomer) {
        persitedCustomer.setName(customerDTO.getName());
        persitedCustomer.setEmail(customerDTO.getEmail());
        persitedCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        persitedCustomer.setCpf(customerDTO.getCpf());
        persitedCustomer.setCompany(getCustomerCompanyFromDTO(customerDTO));

        return persitedCustomer;
    }

    @Override
    public BCBCustomer updateCustomerQuota(final Long id, final UpdateCustomerQuotaDTO customerDTO) {
        final BCBCustomer customer = findById(id);

        customer.getBalance().setQuota(customerDTO.getQuota());

        return bcbCustomerRepository.save(customer);
    }
}
