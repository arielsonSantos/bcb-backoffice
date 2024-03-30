package br.com.arielsonsantos.bcbbackoffice.dto.company;

import java.io.Serializable;

import br.com.arielsonsantos.bcbbackoffice.entity.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CompanyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;

    @NotBlank
    private String name;

    public CompanyDTO() {
    }

    public CompanyDTO(final Company company) {
        setCnpj(company.getCnpj());
        setName(company.getName());
    }

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

    public Company toEntity() {
        final Company company = new Company();

        company.setCnpj(getCnpj());
        company.setName(getName());

        return company;
    }
}
