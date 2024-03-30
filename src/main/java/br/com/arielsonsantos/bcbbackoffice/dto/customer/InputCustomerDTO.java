package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class InputCustomerDTO extends CustomerDTO {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    private Long companyId;

    public InputCustomerDTO() {
    }

    public InputCustomerDTO(final BCBCustomer customer) {
        super(customer);
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(final Long companyId) {
        this.companyId = companyId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
