package br.com.arielsonsantos.bcbbackoffice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.CreateCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.InputCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.OutputCustomerDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.customer.UpdateCustomerQuotaDTO;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IBCBCustomerService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("customers")
public class BCBCustomerController {

    @Autowired
    @Qualifier("customerService")
    private IBCBCustomerService bcbCustomerService;

    @GetMapping
    public ResponseEntity<Set<OutputCustomerDTO>> getCustomers() {
        return ResponseEntity.ok(OutputCustomerDTO.toDTO(bcbCustomerService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputCustomerDTO> getCustomerById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(new OutputCustomerDTO(bcbCustomerService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OutputCustomerDTO> createCustomer(@Valid @RequestBody final CreateCustomerDTO customerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutputCustomerDTO(bcbCustomerService.createCustomer(customerDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputCustomerDTO> updateCustomer(@PathVariable("id") final Long id, @Valid @RequestBody final InputCustomerDTO customerDTO) {
        return ResponseEntity.ok(new OutputCustomerDTO(bcbCustomerService.updateCustomer(id, customerDTO)));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OutputCustomerDTO> updateCustomerQuota(@PathVariable("id") final Long id, @Valid @RequestBody final UpdateCustomerQuotaDTO customerDTO) {
        return ResponseEntity.ok(new OutputCustomerDTO(bcbCustomerService.updateCustomerQuota(id, customerDTO)));
    }
}
