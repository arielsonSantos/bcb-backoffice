package br.com.arielsonsantos.bcbbackoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;

public interface MessageRepository extends JpaRepository<BCBMessage, Long> {

}
