package br.com.arielsonsantos.bcbbackoffice.controller;

import static org.assertj.core.api.Assertions.assertThatCollection;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import br.com.arielsonsantos.bcbbackoffice.dto.company.CompanyDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.Company;
import br.com.arielsonsantos.bcbbackoffice.repository.CompanyRepository;

public class CompanyControllerTest extends BaseControllerTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void shouldReturnEmptyArrayOnGetAllWhenThereIsNoCompanyCreated() throws Exception {
        mvc.perform(get("/companies").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isEmpty());

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldThrowExceptionOnGetByIdWhenThereIsNoCompanyCreated() throws Exception {
        mvc.perform(get("/companies/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.timestamp").isString())
                .andExpect(jsonPath("$.statusCode", is(404)))
                .andExpect(jsonPath("$.title", is("Not Found")))
                .andExpect(jsonPath("$.description", is("Resource not found")))
                .andExpect(jsonPath("$.path", is("uri=/companies/1")))
                .andExpect(jsonPath("$.details[0]", is("Company '1' not found!")));

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldCreateCompany() throws Exception {
        createCompany("Empresa Teste", "15413401000140");
    }

    @Test
    public void shouldCreateTwoCompanies() throws Exception {
        createCompany("Empresa Teste", "15413401000140");
        createCompany("Empresa 2", "68128686000134");

        assertThatList(companyRepository.findAll()).hasSize(2);
    }

    @Test
    public void shouldNotCreateTwoCompaniesWithSameCnpj() throws Exception {
        createCompany("Empresa Teste", "15413401000140");
        tryCreatingCompany("Empresa 2", "15413401000140", HttpStatus.CONFLICT, "Data integrity violated", "Company with CNPJ 15413401000140 already exists");

        assertThatList(companyRepository.findAll()).hasSize(1);
    }

    @Test
    public void shouldNotCreateCompanyWithNullName() throws Exception {
        tryCreatingCompany(null, "15413401000140", HttpStatus.BAD_REQUEST, "Input validation error", "name: must not be blank");

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldNotCreateCompanyWithEmptyName() throws Exception {
        tryCreatingCompany("", "15413401000140", HttpStatus.BAD_REQUEST, "Input validation error", "name: must not be blank");

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldNotCreateCompanyWithNullCnpj() throws Exception {
        tryCreatingCompany("Empresa", null, HttpStatus.BAD_REQUEST, "Input validation error", "cnpj: must not be blank");

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldNotCreateCompanyWithEmptyCnpj() throws Exception {
        tryCreatingCompany("Empresa", "", HttpStatus.BAD_REQUEST, "Input validation error", "cnpj: must not be blank", "cnpj: size must be between 14 and 14");

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    @Test
    public void shouldNotCreateCompanyWithCnpjShorterThan14Digits() throws Exception {
        tryCreatingCompany("Empresa", "123456789", HttpStatus.BAD_REQUEST, "Input validation error", "cnpj: size must be between 14 and 14");

        assertThatList(companyRepository.findAll()).isEmpty();
    }

    private void createCompany(final String companyName, final String companyCNPJ) throws Exception, JsonProcessingException, UnsupportedEncodingException {
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(companyName);
        companyDTO.setCnpj(companyCNPJ);

        final MvcResult result = mvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name", is(companyName)))
                .andExpect(jsonPath("$.cnpj", is(companyCNPJ)))
                .andExpect(jsonPath("$.customers").isEmpty())
                .andReturn();

        final Long id = JsonPath.parse(result.getResponse().getContentAsString()).read("$.id", Long.class);
        final Company company = companyRepository.findById(id).orElseThrow();

        assertThat(company.getName()).isEqualTo(companyName);
        assertThat(company.getCnpj()).isEqualTo(companyCNPJ);
        assertThatCollection(company.getCustomers()).isNullOrEmpty();
    }

    private void tryCreatingCompany(final String companyName, final String companyCNPJ, final HttpStatus status, final String description, final String... details) throws Exception, JsonProcessingException, UnsupportedEncodingException {
        final CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setName(companyName);
        companyDTO.setCnpj(companyCNPJ);

        mvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(companyDTO)))
                .andExpect(jsonPath("$.timestamp").isString())
                .andExpect(jsonPath("$.statusCode", is(status.value())))
                .andExpect(jsonPath("$.title", is(status.getReasonPhrase())))
                .andExpect(jsonPath("$.description", is(description)))
                .andExpect(jsonPath("$.path", is("uri=/companies")))
                .andExpect(jsonPath("$.details", containsInAnyOrder(details)));
    }
}
