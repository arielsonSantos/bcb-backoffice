package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class UpdateCustomerQuotaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @PositiveOrZero
    private Double quota;

    public Double getQuota() {
        return quota;
    }

    public void setQuota(final Double quota) {
        this.quota = quota;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
