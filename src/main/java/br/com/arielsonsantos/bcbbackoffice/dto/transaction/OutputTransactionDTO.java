package br.com.arielsonsantos.bcbbackoffice.dto.transaction;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.OutputCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;

public class OutputTransactionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String type;
    private Double value;
    private OutputCustomerDTO customer;

    public OutputTransactionDTO(final BCBTransaction transaction) {
        setId(transaction.getId());
        setType(transaction.getType().toString());
        setValue(transaction.getValue());
        setCustomer(new OutputCustomerDTO(transaction.getBalance().getCustomer()));
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public OutputCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(final OutputCustomerDTO customer) {
        this.customer = customer;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public static Set<OutputTransactionDTO> toDTO(final Collection<BCBTransaction> transactions) {
        return transactions.stream().map(OutputTransactionDTO::new).collect(Collectors.toSet());
    }
}
