package br.com.arielsonsantos.bcbbackoffice.dto.message;

import br.com.arielsonsantos.bcbbackoffice.dto.customer.CustomerIdentityDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;
import br.com.arielsonsantos.bcbbackoffice.enums.MessageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateMessageDTO extends CustomerIdentityDTO {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private String type;

    @NotBlank
    @Size(min = 14, max = 14)
    @Pattern(regexp = "^\\+\\d{13}$", message = "Incorrect phone number format. Use the '+#############' format")
    private String phoneNumber;

    @NotBlank
    private String messageBody;

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

    public BCBMessage toEntity() {
        final BCBMessage message = new BCBMessage();

        message.setType(MessageType.valueOf(getType()));
        message.setPhoneNumber(getPhoneNumber());
        message.setMessageBody(getMessageBody());

        return message;
    }
}
