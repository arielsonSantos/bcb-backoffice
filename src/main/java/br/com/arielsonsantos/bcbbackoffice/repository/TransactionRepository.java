package br.com.arielsonsantos.bcbbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;

public interface TransactionRepository extends JpaRepository<BCBTransaction, Long> {

}
