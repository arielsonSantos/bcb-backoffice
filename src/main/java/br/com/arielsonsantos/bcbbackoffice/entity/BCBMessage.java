package br.com.arielsonsantos.bcbbackoffice.entity;

import br.com.arielsonsantos.bcbbackoffice.enums.MessageStatus;
import br.com.arielsonsantos.bcbbackoffice.enums.MessageType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class BCBMessage extends BaseEntity {

    @Column(length = 14)
    private String phoneNumber;

    private String messageBody;
    private MessageStatus status;

    @Column(updatable = false)
    private MessageType type;

    @OneToOne
    private BCBTransaction transaction;

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

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(final MessageStatus status) {
        this.status = status;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(final MessageType type) {
        this.type = type;
    }

    public BCBTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(final BCBTransaction transaction) {
        this.transaction = transaction;
    }
}
