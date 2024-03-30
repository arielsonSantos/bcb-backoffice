package br.com.arielsonsantos.bcbbackoffice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.arielsonsantos.bcbbackoffice.dto.message.CreateMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;
import br.com.arielsonsantos.bcbbackoffice.enums.MessageType;
import br.com.arielsonsantos.bcbbackoffice.exception.ResourceNotFoundException;
import br.com.arielsonsantos.bcbbackoffice.repository.MessageRepository;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IMessageService;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.IQueueServiceSender;
import br.com.arielsonsantos.bcbbackoffice.service.interfaces.ITransactionService;

@Service
@Transactional
@Qualifier("messageService")
public class MessageService implements IMessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    @Qualifier("transactionService")
    private ITransactionService bcbTransactionService;

    @Autowired
    @Qualifier("queueServiceSender")
    protected IQueueServiceSender queueServiceSender;

    @Override
    @Transactional(readOnly = true)
    public List<BCBMessage> findAll() {
        return messageRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BCBMessage findById(final Long id) {
        return messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message '%s' not found!".formatted(id)));
    }

    @Override
    public BCBMessage createMessage(final CreateMessageDTO messageDTO) {
        final MessageType messageType = MessageType.valueOf(messageDTO.getType());
        final BCBMessage message = messageRepository.save(messageType.createMessageTransaction(bcbTransactionService, messageDTO));

        messageType.sendMessage(queueServiceSender, message);

        return message;
    }
}
