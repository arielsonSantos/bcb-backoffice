package br.com.arielsonsantos.bcbbackoffice.service.queue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;

import br.com.arielsonsantos.bcbbackoffice.dto.message.ReceiveMessageQueueObject;

@Profile("!test")
@RabbitListener(queues = "q.message-status")
public class MessageStatusReceiver {

    @RabbitHandler
    public void receive(final ReceiveMessageQueueObject messageStatus) {
        System.out.println(" [x] Received messageId: '" + messageStatus.getId() + "', MessageStatus: '" + messageStatus.getMessageStatus() + "'");
    }
}
