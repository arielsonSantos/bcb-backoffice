package br.com.arielsonsantos.bcbbackoffice.service.interfaces;

import java.util.List;

import br.com.arielsonsantos.bcbbackoffice.dto.company.CompanyDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.Company;

public interface ICompanyService {

    List<Company> findAll();

    Company findById(final Long id);

    Company createCompany(final CompanyDTO companyDTO);

    Company updateCompany(final Long id, final CompanyDTO companyDTO);
}
