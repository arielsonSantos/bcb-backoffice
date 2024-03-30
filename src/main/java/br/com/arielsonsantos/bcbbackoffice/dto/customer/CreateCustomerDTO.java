package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateCustomerDTO extends InputCustomerDTO {

    private static final long serialVersionUID = 1L;

    @NotNull
    @PositiveOrZero
    private Double initialBalance;

    @NotNull
    @PositiveOrZero
    private Double initialQuota;

    public CreateCustomerDTO() {
    }

    public CreateCustomerDTO(final BCBCustomer customer) {
        super(customer);
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(final Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getInitialQuota() {
        return initialQuota;
    }

    public void setInitialQuota(final Double initialQuota) {
        this.initialQuota = initialQuota;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
