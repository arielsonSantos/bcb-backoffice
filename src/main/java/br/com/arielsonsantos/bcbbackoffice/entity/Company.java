package br.com.arielsonsantos.bcbbackoffice.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Company extends BaseEntity {

    @Column(length = 14, unique = true)
    private String cnpj;

    private String name;

    @OneToMany(mappedBy = "company")
    private Set<BCBCustomer> customers;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Set<BCBCustomer> getCustomers() {
        return customers;
    }
}
