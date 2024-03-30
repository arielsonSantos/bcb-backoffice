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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.arielsonsantos.bcbbackoffice.dto.company.CompanyDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.company.OutputCompanyDTO;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.ICompanyService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("companies")
public class CompanyController {

    @Autowired
    @Qualifier("companyService")
    private ICompanyService companyService;

    @GetMapping
    public ResponseEntity<Set<OutputCompanyDTO>> getCompanies() {
        return ResponseEntity.ok(OutputCompanyDTO.toDTO(companyService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputCompanyDTO> getCompany(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(new OutputCompanyDTO(companyService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OutputCompanyDTO> createCompany(@Valid @RequestBody final CompanyDTO companyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutputCompanyDTO(companyService.createCompany(companyDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutputCompanyDTO> updateCompany(@PathVariable("id") final Long id, @Valid @RequestBody final CompanyDTO companyDTO) {
        return ResponseEntity.ok(new OutputCompanyDTO(companyService.updateCompany(id, companyDTO)));
    }
}
