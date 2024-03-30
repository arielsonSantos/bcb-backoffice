package br.com.arielsonsantos.bcbbackoffice.service.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import br.com.arielsonsantos.bcbbackoffice.dto.message.SendMessageQueueObject;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IQueueServiceSender;

@Service
@Qualifier("queueServiceSender")
public class QueueServiceSender implements IQueueServiceSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue smsSendQueue;

    @Autowired
    private Queue wppSendQueue;

    @Override
    public void sendSMS(final BCBMessage message) {
        send(smsSendQueue, new SendMessageQueueObject(message));
    }

    @Override
    public void sendWhatsapp(final BCBMessage message) {
        send(wppSendQueue, new SendMessageQueueObject(message));
    }

    @Override
    public void send(final Queue queue, final SendMessageQueueObject messageObject) {
        template.convertAndSend(queue.getName(), messageObject);
    }
}
