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

    @PatchMapping("{id}")
    public ResponseEntity<?> updateUtilisateur(@RequestBody UtilisateurPutDto utilisateurPutDto, @PathVariable Integer id) {

        if (!utilisateurPutDto.getId().equals(id))
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");

        Optional<Utilisateur> optional = utilisateurService.getUtilisateurById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("L'utilisateur est inexistant");
        }

        UtilisateurDTO utilisateurDto = utilisateurService.updateUtilisateur(utilisateurPutDto, id);
        if (utilisateurDto != null) {
            return ResponseEntity.ok(utilisateurDto);
        } else {
            return ResponseEntity.status(404).body("L'utilisateur a modifier n'a pas été trouver");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable("id") Integer id) {
        if (utilisateurService.deleteUtilisateur(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Utilisateur non trouvé");
        }
    }

}
