package backend.Projetcertification.controller;

import backend.Projetcertification.dto.CanalDTO;
import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.mapper.CanalMapper;
import backend.Projetcertification.dto.mapper.UtilisateurMapper;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.service.CanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("canals")
public class CanalController {

    @Autowired
    CanalService canalService;
    @GetMapping
    public ResponseEntity<List<CanalDTO>> findAllCanal(){
        List<Canal> canals = canalService.getCanaux();
        List<CanalDTO> canalDTOS = new ArrayList<>();

        for (Canal entity : canals) {
            CanalDTO canalsDto = CanalMapper.entityToDto(entity);
            canalDTOS.add(canalsDto);
        }
        return ResponseEntity.ok(canalDTOS);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCanal(@RequestBody CanalDTO newCanal) {
        if (canalService.champsVidePost(newCanal)){
            return ResponseEntity.badRequest().body("Le pseudo n'a pas été rempli");
        }
      CanalDTO canalDTO =  canalService.addCanal(newCanal);
        return ResponseEntity.ok(canalDTO) ;
    }
    @GetMapping("{id}")
    public ResponseEntity <?> findCanalById(@PathVariable Integer id) {
        Optional<Canal> optional = canalService.getCanalById(id);
        if (optional.isPresent()) {
            CanalDTO canal = CanalMapper.entityToDto(optional.get());
            return ResponseEntity.ok(canal);
        } else
            return ResponseEntity.status(404).body("L'utilisateur est inexistant");
    }
    @PatchMapping("{id}")
    public ResponseEntity<?> updateCanal(@RequestBody Canal CanalPatch, @PathVariable Integer id) {

        if (!CanalPatch.getId().equals(id))
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");

        Optional<Canal> optional = canalService.getCanalById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Le canal est inexistant");
        }
        Canal utilisateurDto = canalService.updateCanal(CanalPatch);
        if (CanalPatch != null) {
            return ResponseEntity.ok(utilisateurDto);
        } else {
            return ResponseEntity.status(404).body("Le canal a modifier n'a pas été trouver");
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCanal(@PathVariable("id") Integer id) {
        if (canalService.deleteCanal(id) != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Canal non trouvé");
        }
    }
}
