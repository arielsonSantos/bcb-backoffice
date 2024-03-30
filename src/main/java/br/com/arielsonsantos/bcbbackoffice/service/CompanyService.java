package br.com.arielsonsantos.bcbbackoffice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arielsonsantos.bcbbackoffice.dto.company.CompanyDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.Company;
import br.com.arielsonsantos.bcbbackoffice.exception.ResourceNotFoundException;
import br.com.arielsonsantos.bcbbackoffice.exception.UniqueIdentifierViolationException;
import br.com.arielsonsantos.bcbbackoffice.repository.CompanyRepository;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.ICompanyService;

@Service
@Transactional
@Qualifier("companyService")
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Company findById(final Long id) {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company '%s' not found!".formatted(id)));
    }

    @Override
    public Company createCompany(final CompanyDTO companyDTO) {
        if (companyRepository.findByCnpj(companyDTO.getCnpj()).isPresent())
            throw new UniqueIdentifierViolationException("Company with CNPJ %s already exists".formatted(companyDTO.getCnpj()));

        return companyRepository.save(companyDTO.toEntity());
    }

    @Override
    public Company updateCompany(final Long id, final CompanyDTO companyDTO) {
        final Optional<Company> optionalCompany = companyRepository.findByCnpj(companyDTO.getCnpj());
        if (optionalCompany.isPresent() && !optionalCompany.get().getId().equals(id))
            throw new UniqueIdentifierViolationException("Company with CNPJ %s already exists".formatted(companyDTO.getCnpj()));

        return companyRepository.save(merge(companyDTO, findById(id)));
    }

    private Company merge(final CompanyDTO companyDTO, final Company persitedCompany) {
        persitedCompany.setName(companyDTO.getName());
        persitedCompany.setCnpj(companyDTO.getCnpj());
        return persitedCompany;
    }
}
