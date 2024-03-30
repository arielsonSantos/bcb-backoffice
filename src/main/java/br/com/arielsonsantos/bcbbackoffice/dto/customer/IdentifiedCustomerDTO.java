package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import java.util.Set;
import java.util.stream.Collectors;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;

public class IdentifiedCustomerDTO extends CustomerDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    public IdentifiedCustomerDTO() {
    }

    public IdentifiedCustomerDTO(final BCBCustomer customer) {
        super(customer);

        setId(customer.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public static Set<IdentifiedCustomerDTO> toDTO(final Set<BCBCustomer> customers) {
        if (customers == null)
            return Set.of();

        return customers.stream().map(IdentifiedCustomerDTO::new).collect(Collectors.toSet());
    }
}
