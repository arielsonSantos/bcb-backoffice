package br.com.arielsonsantos.bcbbackoffice.service.interfaces;

import java.util.List;

import br.com.arielsonsantos.bcbbackoffice.dto.message.CreateMessageDTO;
import br.com.arielsonsantos.bcbbackoffice.entity.BCBMessage;

public interface IMessageService {

    List<BCBMessage> findAll();

    BCBMessage findById(final Long id);

    BCBMessage createMessage(final CreateMessageDTO messageDTO);
}
