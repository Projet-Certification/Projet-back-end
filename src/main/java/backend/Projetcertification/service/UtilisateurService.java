package backend.Projetcertification.service;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.dto.mapper.UtilisateurMapper;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.repository.UtilisateurRepository;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id);
    }

    public UtilisateurDTO addUtilisateur(UtilisateurDTO utilisateurDTO) {
        Utilisateur utilisateur = UtilisateurMapper.dtoToEntity(utilisateurDTO);
        utilisateurRepository.save(utilisateur);
        return utilisateurDTO;
    }

    public UtilisateurDTO updateUtilisateur(UtilisateurPutDto newUtilisateurPutDto, Integer id) {
        Optional<Utilisateur> op = getUtilisateurById(id);
        if (op.isPresent()) {
            Utilisateur utilisateur = op.get();
            utilisateur.setNotNull(newUtilisateurPutDto);

            utilisateurRepository.save(utilisateur);
            // Convertir le putDto de l'utilisateur modifier en dto normal,pour afficher que le pseudo et non l'id avec
            UtilisateurDTO utilisateurDTO = UtilisateurMapper.dtoToDtoPut(newUtilisateurPutDto);
            return utilisateurDTO;
        }
        return null;
    }
    public boolean deleteUtilisateur(Integer id) {
        if (getUtilisateurById(id).isPresent()) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean champsVidePost(UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO.getPseudo() == null || utilisateurDTO.getPseudo().isBlank()) {
            return true;
        }
        return false;
    }

}
