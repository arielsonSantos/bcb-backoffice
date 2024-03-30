package br.com.arielsonsantos.bcbbackoffice.dto.message;

import java.io.Serializable;

import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;

public class SendMessageQueueObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String phoneNumber;
    private String messageBody;

    public SendMessageQueueObject() {
    }

    public SendMessageQueueObject(final BCBMessage message) {
        setId(message.getId());
        setPhoneNumber(message.getPhoneNumber());
        setMessageBody(message.getMessageBody());
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
}
