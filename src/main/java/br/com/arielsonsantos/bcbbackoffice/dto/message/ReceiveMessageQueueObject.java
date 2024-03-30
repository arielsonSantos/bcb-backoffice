package br.com.arielsonsantos.bcbbackoffice.dto.message;

import java.io.Serializable;

public class ReceiveMessageQueueObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String messageStatus;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(final String messageStatus) {
        this.messageStatus = messageStatus;
    }
}
