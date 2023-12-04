package backend.Projetcertification.controller;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDTO;
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

    @GetMapping("{id}")
    public ResponseEntity<?> getUtilisateurById(@PathVariable Integer id) {

        Optional<Utilisateur> optionalUtilisateur = utilisateurService.getUtilisateurById(id);
        if (optionalUtilisateur.isPresent()) {
            UtilisateurDTO utilisateurDto = UtilisateurMapper.entityToDto(optionalUtilisateur.get());

            return ResponseEntity.ok(utilisateurDto);
        } else {
            return ResponseEntity.status(404).body("L'utilisateur est inexistant");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addUtilisateur(@RequestBody UtilisateurDTO newUtilisateur) {
        if (utilisateurService.champsVidePost(newUtilisateur)) {
            return ResponseEntity.badRequest().body("Le pseudo n'a pas été rempli");
        }
        if (utilisateurService.cherchePseudo(newUtilisateur)) {
            return ResponseEntity.badRequest().body("Le pseudo existe déja");
        }

        UtilisateurDTO utilisateurDto = utilisateurService.addUtilisateur(newUtilisateur);

        return ResponseEntity.ok(utilisateurDto);

    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateUtilisateur(@RequestBody UtilisateurPutDTO utilisateurPutDto,
                                               @PathVariable Integer id) {

        if (!utilisateurPutDto.getId().equals(id))
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");

        Optional<Utilisateur> optional = utilisateurService.getUtilisateurById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("L'utilisateur est inexistant");
        }

        UtilisateurDTO newutilisateurDto = UtilisateurMapper.putDtoToDto(utilisateurPutDto);
        if (utilisateurService.champsVidePost(newutilisateurDto)) {
            return ResponseEntity.badRequest().body("L'un des champs n'a pas été rempli");
        }

        if (utilisateurService.cherchePseudo(newutilisateurDto)) {
            return ResponseEntity.badRequest().body("Le pseudo existe déja, vous devez en choisir un autre");
        }

        UtilisateurDTO utilisateurDtoSave = utilisateurService.updateUtilisateur(utilisateurPutDto, id);
        if (utilisateurDtoSave != null) {
            return ResponseEntity.ok(utilisateurDtoSave);
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
