package br.com.arielsonsantos.bcbbackoffice.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Balance extends BaseEntity {

    private Double amount;
    private Double quota;

    @OneToOne
    @JoinColumn(updatable = false)
    private BCBCustomer customer;

    @OneToMany(mappedBy = "balance", cascade = CascadeType.PERSIST)
    private Set<BCBTransaction> transactions;

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

    public BCBCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(final BCBCustomer customer) {
        this.customer = customer;
    }

    public Set<BCBTransaction> getTransactions() {
        return transactions;
    }
}
