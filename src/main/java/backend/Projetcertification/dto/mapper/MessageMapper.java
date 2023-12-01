package backend.Projetcertification.dto.mapper;

import backend.Projetcertification.dto.MessageDTO;
import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.entity.Utilisateur;

public class MessageMapper {


    // Convertir l'entité en dto
    public static MessageDTO entityToDto(Message entity) {
        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setContenuMessage(entity.getContenuMessage());
        messageDTO.setDateMessage(entity.getDateMessage());
        messageDTO.setIdCanal(entity.getCanal().getId());
        messageDTO.setIdUtilisateur(entity.getUtilisateur().getId());

        return messageDTO;
    }
    //Convertir le dto en entité
    public static Message dtoToEntity(MessageDTO messageDTO) {
        Message message = new Message();

        message.setContenuMessage(messageDTO.getContenuMessage());

        return message;
    }

    // Convertir le dto put de l'utilisateur (avec l'id) en dto de l'utilisateur (sans l'id)
//    public static UtilisateurDTO putDtoToDto(UtilisateurPutDto entity) {
//        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
//        utilisateurDTO.setPseudo(entity.getPseudo());
//        return utilisateurDTO;
//    }
}
