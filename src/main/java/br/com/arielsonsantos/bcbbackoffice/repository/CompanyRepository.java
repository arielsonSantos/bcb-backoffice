package br.com.arielsonsantos.bcbbackoffice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arielsonsantos.bcbbackoffice.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCnpj(String cnpj);

}
