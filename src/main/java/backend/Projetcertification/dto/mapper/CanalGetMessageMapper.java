package backend.Projetcertification.dto.mapper;

import backend.Projetcertification.dto.CanalDTO;
import backend.Projetcertification.dto.CanalGetMessagesDTO;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Message;

public class CanalGetMessageMapper {

    //Convertir l'entite en dto pour l'injecter dans la liste de contenu message qui est dans le DTO CanalDTO
    public static CanalGetMessagesDTO entityToDto(Message message) {

        CanalGetMessagesDTO canalGetMessagesDTO = new CanalGetMessagesDTO();

        canalGetMessagesDTO.setPseudoUtilisateur(message.getUtilisateur().getPseudo());
        canalGetMessagesDTO.setContenuMessage(message.getContenuMessage());
        canalGetMessagesDTO.setDateMessage(message.getDateMessage());
        canalGetMessagesDTO.setHeureMessage(message.getDateMessage());

        return canalGetMessagesDTO;
    }
}
