package br.com.arielsonsantos.bcbbackoffice.dto.customer;

import java.io.Serializable;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "^\\+\\d{13}$", message = "Incorrect phone number format. Use the '+#############' format")
    private String phoneNumber;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;

    public CustomerDTO() {
    }

    public CustomerDTO(final BCBCustomer customer) {
        setName(customer.getName());
        setEmail(customer.getEmail());
        setPhoneNumber(customer.getPhoneNumber());
        setCpf(customer.getCpf());
    }

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

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BCBCustomer toEntity() {
        final BCBCustomer customer = new BCBCustomer();

        customer.setName(getName());
        customer.setEmail(getEmail());
        customer.setPhoneNumber(getPhoneNumber());
        customer.setCpf(getCpf());

        return customer;
    }
}
