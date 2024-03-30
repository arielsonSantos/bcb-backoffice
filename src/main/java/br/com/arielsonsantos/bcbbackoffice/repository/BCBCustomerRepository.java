package br.com.arielsonsantos.bcbbackoffice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBCustomer;

public interface BCBCustomerRepository extends JpaRepository<BCBCustomer, Long> {

    Optional<BCBCustomer> findByCpf(String cpf);
}
