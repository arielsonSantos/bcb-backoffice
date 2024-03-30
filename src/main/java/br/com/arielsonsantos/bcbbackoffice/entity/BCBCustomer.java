package br.com.arielsonsantos.bcbbackoffice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class BCBCustomer extends BaseEntity {

    private String name;
    private String email;

    @Column(length = 14)
    private String phoneNumber;

    @Column(length = 11, unique = true)
    private String cpf;

    @ManyToOne
    private Company company;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Balance balance;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(final String cpf) {
        this.cpf = cpf;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(final Company company) {
        this.company = company;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(final Balance balance) {
        this.balance = balance;
    }
}
