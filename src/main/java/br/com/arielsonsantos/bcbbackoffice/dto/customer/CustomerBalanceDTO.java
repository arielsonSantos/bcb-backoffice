package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import java.io.Serializable;

import br.com.arielsonsantos.bcbbackoffice.entity.Balance;

public class CustomerBalanceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Double amount;
    private Double quota;

    public CustomerBalanceDTO(final Balance balance) {
        setAmount(balance.getAmount());
        setQuota(balance.getQuota());
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

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
