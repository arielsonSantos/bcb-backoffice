package br.com.arielsonsantos.bcbbackoffice.enums;

import br.com.arielsonsantos.bcbbackoffice.dto.message.CreateMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBTransaction;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IQueueServiceSender;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.ITransactionService;

public enum MessageType {

    sms {

        @Override
        public BCBMessage createMessageTransaction(final ITransactionService bcbTransactionService, final CreateMessageDTO messageDTO) {
            final BCBTransaction transaction = bcbTransactionService.createSMSTransaction(messageDTO);
            return createMessage(transaction, messageDTO);
        }

        @Override
        public void sendMessage(final IQueueServiceSender queueServiceSender, final BCBMessage message) {
            queueServiceSender.sendSMS(message);
        }
    },
    whatsapp {

        @Override
        public BCBMessage createMessageTransaction(final ITransactionService bcbTransactionService, final CreateMessageDTO messageDTO) {
            final BCBTransaction transaction = bcbTransactionService.createWhatsappTransaction(messageDTO);
            return createMessage(transaction, messageDTO);
        }

        @Override
        public void sendMessage(final IQueueServiceSender queueServiceSender, final BCBMessage message) {
            queueServiceSender.sendWhatsapp(message);
        }
    };

    public abstract BCBMessage createMessageTransaction(final ITransactionService bcbTransactionService, CreateMessageDTO messageDTO);

    protected BCBMessage createMessage(final BCBTransaction transaction, final CreateMessageDTO messageDTO) {
        final BCBMessage message = messageDTO.toEntity();

        message.setType(this);
        message.setTransaction(transaction);
        message.setStatus(MessageStatus.pending);

        transaction.setMessage(message);

        return message;
    }

    public abstract void sendMessage(IQueueServiceSender queueServiceSender, BCBMessage message);
}
