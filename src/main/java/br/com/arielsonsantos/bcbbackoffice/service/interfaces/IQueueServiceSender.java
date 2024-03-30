package br.com.arielsonsantos.bcbbackoffice.service.interfaces;

import org.springframework.amqp.core.Queue;

import br.com.arielsonsantos.bcbbackoffice.dto.message.SendMessageQueueObject;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;

public interface IQueueServiceSender {

    void sendSMS(BCBMessage message);

    void sendWhatsapp(BCBMessage message);

    void send(final Queue queue, final SendMessageQueueObject messageObject);
}
