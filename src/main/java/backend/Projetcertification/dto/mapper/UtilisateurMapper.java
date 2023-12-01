package backend.Projetcertification.dto.mapper;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.entity.Utilisateur;

public class UtilisateurMapper {


    public static UtilisateurDTO entityToDto(Utilisateur entity) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setPseudo(entity.getPseudo());
        return utilisateurDTO;
    }
    public static Utilisateur dtoToEntity(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setPseudo(utilisateurDTO.getPseudo());
        return utilisateur;
    }

    public static UtilisateurDTO dtoToDtoPut(UtilisateurPutDto entity) {
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setPseudo(entity.getPseudo());
        return utilisateurDTO;
    }
}
