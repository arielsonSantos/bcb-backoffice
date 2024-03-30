package br.com.arielsonsantos.bcbbackoffice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arielsonsantos.bcbbackoffice.dto.transaction.CreateDepositTransactionDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.transaction.OutputTransactionDTO;
import br.com.arielsonsantos.bcbbackoffice.service.TransactionService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("transactions")
public class TransactionController {

    @Autowired
    @Qualifier("transactionService")
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<Set<OutputTransactionDTO>> getTransaction() {
        return ResponseEntity.ok(OutputTransactionDTO.toDTO(transactionService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputTransactionDTO> getTransactionById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(new OutputTransactionDTO(transactionService.findById(id)));
    }

    @PostMapping("deposit")
    public ResponseEntity<OutputTransactionDTO> createDepositTransaction(@Valid @RequestBody final CreateDepositTransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutputTransactionDTO(transactionService.createDepositTransaction(transactionDTO)));
    }
}
