package backend.Projetcertification.controller;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.dto.mapper.UtilisateurMapper;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.service.UtilisateurService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("utilisateurs")
public class UtilisateurController {


    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurDTO>> getUtilisateurs() {
        List<Utilisateur> utilisateurs = utilisateurService.getUtilisateurs();
        List<UtilisateurDTO> utilisateurDTOS = new ArrayList<>();

        for (Utilisateur entity : utilisateurs) {
            UtilisateurDTO utilisateurDto = UtilisateurMapper.entityToDto(entity);
            utilisateurDTOS.add(utilisateurDto);
        }
        return ResponseEntity.ok(utilisateurDTOS);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUtilisateur(@RequestBody UtilisateurDTO newUtilisateur) {
        if (utilisateurService.champsVidePost(newUtilisateur)) {
            return ResponseEntity.badRequest().body("Le pseudo n'a pas été rempli");
        }
        UtilisateurDTO utilisateurDto = utilisateurService.addUtilisateur(newUtilisateur);

        return ResponseEntity.ok(utilisateurDto);

    }

}
