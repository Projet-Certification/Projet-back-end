package backend.Projetcertification.dto.mapper;

import backend.Projetcertification.dto.MessageDTO;
import backend.Projetcertification.dto.MessagePutDTO;
import backend.Projetcertification.entity.Message;

public class MessageMapper {


    // Convertir l'entité en dto
    public static MessageDTO entityToDto(Message entity) {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId(entity.getId());
        messageDTO.setContenuMessage(entity.getContenuMessage());
        messageDTO.setDateMessage(entity.getDateMessage());
        messageDTO.setHeureMessage(entity.getDateMessage());
        messageDTO.setIdCanal(entity.getCanal().getId());
        messageDTO.setIdUtilisateur(entity.getUtilisateur().getId());

        return messageDTO;
    }

    public static Message dtoToEntity(MessageDTO messageDTO) {
        Message message = new Message();

        message.setContenuMessage(messageDTO.getContenuMessage());

        return message;
    }
}
