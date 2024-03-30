package br.com.arielsonsantos.bcbbackoffice.dto.company;

import br.com.arielsonsantos.bcbbackoffice.entity.Company;

public class IdentifiedCompanyDTO extends CompanyDTO {

    private static final long serialVersionUID = 1L;

    private Long id;

    public IdentifiedCompanyDTO() {
    }

    public IdentifiedCompanyDTO(final Company company) {
        super(company);

        setId(company.getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
