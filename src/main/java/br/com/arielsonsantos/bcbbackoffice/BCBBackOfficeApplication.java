package br.com.arielsonsantos.bcbbackoffice;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import br.com.arielsonsantos.bcbbackoffice.service.queue.MessageStatusReceiver;

@SpringBootApplication
public class BCBBackOfficeApplication {

    static final String smsSendQueueName = "q.bcb-sms";
    static final String whatsappSendQueueName = "q.bcb-wpp";

    @Bean
    Queue smsSendQueue() {
        return new Queue(smsSendQueueName);
    }

    @Bean
    Queue wppSendQueue() {
        return new Queue(whatsappSendQueueName);
    }

    @Bean
    @Profile("!test")
    MessageStatusReceiver receiver() {
        return new MessageStatusReceiver();
    }

    public static void main(final String[] args) {
        SpringApplication.run(BCBBackOfficeApplication.class, args);
    }
}
