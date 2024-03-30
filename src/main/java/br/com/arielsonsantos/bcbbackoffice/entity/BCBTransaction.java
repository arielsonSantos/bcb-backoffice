package br.com.arielsonsantos.bcbbackoffice.entity;

import br.com.arielsonsantos.bcbbackoffice.enums.TransactionType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class BCBTransaction extends BaseEntity {

    @Column(name = "t_value", updatable = false)
    private Double value;

    @Column(updatable = false)
    private TransactionType type;

    @ManyToOne
    private Balance balance;

    @OneToOne(mappedBy = "transaction", cascade = CascadeType.PERSIST)
    private BCBMessage message;

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(final Balance balance) {
        this.balance = balance;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(final TransactionType type) {
        this.type = type;
    }

    public BCBMessage getMessage() {
        return message;
    }

    public void setMessage(final BCBMessage message) {
        this.message = message;
    }
}
