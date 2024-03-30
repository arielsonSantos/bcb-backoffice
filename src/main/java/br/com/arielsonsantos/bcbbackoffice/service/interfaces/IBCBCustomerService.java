package br.com.arielsonsantos.bcbbackoffice.service.interfaces;

import java.util.List;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.CreateCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.InputCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.UpdateCustomerQuotaDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;
import br.com.arielsonsantos.bcbbackoffice.entity.Balance;

public interface IBCBCustomerService {

    List<BCBCustomer> findAll();

    BCBCustomer findById(final Long id);

    BCBCustomer createCustomer(final CreateCustomerDTO customerDTO);

    void updateAmount(final Balance balance, final Double amount);

    BCBCustomer updateCustomer(final Long id, final InputCustomerDTO customerDTO);

    public BCBCustomer updateCustomerQuota(final Long id, final UpdateCustomerQuotaDTO customerDTO);
}
