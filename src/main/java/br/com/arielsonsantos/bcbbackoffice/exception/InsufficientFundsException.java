package br.com.arielsonsantos.bcbbackoffice.exception;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;
import br.com.arielsonsantos.bcbbackoffice.entity.Balance;

public class InsufficientFundsException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String INSUFFICIENT_FUNDS_MSG = "Insufficient funds! Balance: %s, Quota: %s, Transaction Type: %s, Transaction Value: %s";

    private Double amount;
    private Double quota;
    private String type;
    private Double value;

    public InsufficientFundsException(final Balance balance, final BCBTransaction transaction) {
        super(INSUFFICIENT_FUNDS_MSG.formatted(balance.getAmount(), balance.getQuota(), transaction.getType().toString(), transaction.getValue()));

        setAmount(balance.getAmount());
        setQuota(balance.getQuota());
        setType(transaction.getType().toString());
        setValue(transaction.getValue());
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

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static String getInsufficientFundsMsg() {
        return INSUFFICIENT_FUNDS_MSG;
    }
}
