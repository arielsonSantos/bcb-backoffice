package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CustomerIdentityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final Long customerId) {
        this.customerId = customerId;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
