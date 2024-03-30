package br.com.arielsonsantos.bcbbackoffice.dto.message;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;

public class OutputMessageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String type;
    private String phoneNumber;
    private String messageBody;
    private String status;
    private Double value;

    public OutputMessageDTO(final BCBMessage message) {
        setId(message.getId());
        setType(message.getType().toString());
        setPhoneNumber(message.getPhoneNumber());
        setMessageBody(message.getMessageBody());
        setStatus(message.getStatus().toString());
        setValue(message.getTransaction().getValue());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(final String messageBody) {
        this.messageBody = messageBody;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(final Double value) {
        this.value = value;
    }

    public static Set<OutputMessageDTO> toDTO(final Collection<BCBMessage> messages) {
        return messages.stream().map(OutputMessageDTO::new).collect(Collectors.toSet());
    }
}
