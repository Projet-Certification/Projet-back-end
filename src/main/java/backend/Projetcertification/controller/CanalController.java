package backend.Projetcertification.controller;

import backend.Projetcertification.dto.CanalDTO;
import backend.Projetcertification.dto.CanalGetMessagesDTO;
import backend.Projetcertification.dto.CanalPutDto;
import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.mapper.CanalGetMessageMapper;
import backend.Projetcertification.dto.mapper.CanalMapper;
import backend.Projetcertification.dto.mapper.UtilisateurMapper;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Message;
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
    public ResponseEntity<List<CanalDTO>> findAllCanal() {
        List<Canal> canals = canalService.getCanaux();
        List<CanalDTO> canalDTOS = new ArrayList<>();

        for (Canal entity : canals) {
            List<CanalGetMessagesDTO> listeMessagesDTO  = new ArrayList<>();

            for (Message message : entity.getMessages()) {
                CanalGetMessagesDTO canalGetMessagesDto = CanalGetMessageMapper.entityToDto(message);

                listeMessagesDTO.add(canalGetMessagesDto);
            }
            CanalDTO canalDto = CanalMapper.entityToDto(entity);
            canalDto.setListContenuMessage(listeMessagesDTO);
            canalDTOS.add(canalDto);
        }
        return ResponseEntity.ok(canalDTOS);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addCanal(@RequestBody CanalDTO newCanal) {
        if (canalService.champsVidePost(newCanal)) {
            return ResponseEntity.badRequest().body("Le pseudo n'a pas été rempli");
        }
        CanalDTO canalDTO = canalService.addCanal(newCanal);

        //Convertir le dto en putDto pour ne
        return ResponseEntity.ok(canalDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findCanalById(@PathVariable Integer id) {
        Optional<Canal> optional = canalService.getCanalById(id);
        if (optional.isPresent()) {
            Canal canal = optional.get();
            CanalDTO canalDto = CanalMapper.entityToDto(canal);

            if(!canal.getMessages().isEmpty()){
                List<CanalGetMessagesDTO> listeMessagesDTO  = new ArrayList<>();

                for (Message message : canal.getMessages()) {
                    CanalGetMessagesDTO canalGetMessagesDto = CanalGetMessageMapper.entityToDto(message);
                    listeMessagesDTO.add(canalGetMessagesDto);
                }

                canalDto.setListContenuMessage(listeMessagesDTO);
            }
            return ResponseEntity.ok(canalDto);
        } else
            return ResponseEntity.status(404).body("L'utilisateur est inexistant");
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateCanal(@RequestBody CanalPutDto CanalPatch, @PathVariable Integer id) {

        if (!CanalPatch.getId().equals(id))
            return ResponseEntity.status(404).body("L'id de l'url est différente de celle envoyer dans le body");

        Optional<Canal> optional = canalService.getCanalById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Le canal est inexistant");
        }
        if (optional.isPresent()) {
            Canal canal = optional.get();
            if (canal.isEstGeneral()) {
                return ResponseEntity.status(404).body("Vous ne pouvez pas modifier le canal général");
            }
        }

        CanalPutDto canalPutDto = canalService.updateCanal(CanalPatch,id);
        if (canalPutDto != null) {
            return ResponseEntity.ok(canalPutDto);
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
