package br.com.arielsonsantos.bcbbackoffice.dto.transaction;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.CustomerIdentityDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;
import br.com.arielsonsantos.bcbbackoffice.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateDepositTransactionDTO extends CustomerIdentityDTO {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Positive
    private Double value;

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BCBTransaction toEntity() {
        final BCBTransaction transaction = new BCBTransaction();

        transaction.setValue(getValue());
        transaction.setType(TransactionType.deposit);

        return transaction;
    }
}
