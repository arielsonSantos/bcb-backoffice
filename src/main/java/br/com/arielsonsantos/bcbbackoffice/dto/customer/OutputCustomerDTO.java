package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.arielsonsantos.bcbbackoffice.dto.company.IdentifiedCompanyDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;

public class OutputCustomerDTO extends IdentifiedCustomerDTO {

    private static final long serialVersionUID = 1L;

    private IdentifiedCompanyDTO company;
    private CustomerBalanceDTO balance;

    public OutputCustomerDTO() {
    }

    public OutputCustomerDTO(final BCBCustomer customer) {
        super(customer);

        setCompany(new IdentifiedCompanyDTO(customer.getCompany()));
        setBalance(new CustomerBalanceDTO(customer.getBalance()));
    }

    public IdentifiedCompanyDTO getCompany() {
        return company;
    }

    public void setCompany(final IdentifiedCompanyDTO company) {
        this.company = company;
    }

    public CustomerBalanceDTO getBalance() {
        return balance;
    }

    public void setBalance(final CustomerBalanceDTO balance) {
        this.balance = balance;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static Set<OutputCustomerDTO> toDTO(final Collection<BCBCustomer> customers) {
        return customers.stream().map(OutputCustomerDTO::new).collect(Collectors.toSet());
    }
}
