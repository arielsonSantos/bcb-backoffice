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

import br.com.arielsonsantos.bcbbackoffice.dto.message.CreateMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.dto.message.OutputMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IMessageService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("messages")
public class MessageController {

    @Autowired
    @Qualifier("messageService")
    private IMessageService messageService;

    @GetMapping
    public ResponseEntity<Set<OutputMessageDTO>> getMessage() {
        return ResponseEntity.ok(OutputMessageDTO.toDTO(messageService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutputMessageDTO> getMessageById(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(new OutputMessageDTO(messageService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<OutputMessageDTO> createMessage(@Valid @RequestBody final CreateMessageDTO messageDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new OutputMessageDTO(messageService.createMessage(messageDTO)));
    }
}
