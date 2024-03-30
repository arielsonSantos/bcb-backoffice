package br.com.arielsonsantos.bcbbackoffice.dto.company;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.IdentifiedCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.Company;

public class OutputCompanyDTO extends IdentifiedCompanyDTO {

    private static final long serialVersionUID = 1L;

    private Set<IdentifiedCustomerDTO> customers;

    public OutputCompanyDTO() {
    }

    public OutputCompanyDTO(final Company company) {
        super(company);

        setCustomers(IdentifiedCustomerDTO.toDTO(company.getCustomers()));
    }

    public Set<IdentifiedCustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(final Set<IdentifiedCustomerDTO> customers) {
        this.customers = customers;
    }

    public static Set<OutputCompanyDTO> toDTO(final Collection<Company> companies) {
        return companies.stream().map(OutputCompanyDTO::new).collect(Collectors.toSet());
    }
}
