package backend.Projetcertification.dto.mapper;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDTO;
import backend.Projetcertification.entity.Utilisateur;

public class UtilisateurMapper {


    // Convertir l'entité en dto
    public static UtilisateurDTO entityToDto(Utilisateur entity) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(entity.getId());
        utilisateurDTO.setPseudo(entity.getPseudo());
        return utilisateurDTO;
    }
    //Convertir le dto en entité
    public static Utilisateur dtoToEntity(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(utilisateurDTO.getPseudo());
        return utilisateur;
    }

    // Convertir le dto put de l'utilisateur (avec l'id) en dto de l'utilisateur (sans l'id)
    public static UtilisateurDTO putDtoToDto(UtilisateurPutDTO entity) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setPseudo(entity.getPseudo());
        return utilisateurDTO;
    }

}
