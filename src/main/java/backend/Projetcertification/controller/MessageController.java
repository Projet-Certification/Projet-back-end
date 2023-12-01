package backend.Projetcertification.controller;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.service.CanalService;
import backend.Projetcertification.service.MessageService;
import backend.Projetcertification.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getAll() {
        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer id) {
        Optional<Message> optionalMessage = messageService.getMessageByIdOptional(id);
        if (optionalMessage.isPresent()) {
            return ResponseEntity.ok(optionalMessage.get());
        }else{
            return ResponseEntity.status(404).body("Le message est inexistant");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addMessage(@RequestBody Message message) {

        if (messageService.champsVidePost(message)) {
            return ResponseEntity.badRequest().body("L'un des champs n'a pas été rempli");
        }

        messageService.addMessage(message);
        return ResponseEntity.ok(message);
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateMessage(@RequestBody Message message, @PathVariable Integer id) {

        if (!message.getId().equals(id))
            return ResponseEntity.badRequest().body("L'id de l'url est différente de celle envoyer dans le body");

        if (!messageService.getMessageByIdOptional(id).isPresent()) {
            return ResponseEntity.status(404).body("Le message est inexistant");
        }
        Message updatedMessage = messageService.updateMessage(message, id);
        if (updatedMessage != null) {
            return ResponseEntity.ok(updatedMessage);
        } else {
            return ResponseEntity.badRequest().body("Le message n'a pas pu être modifier");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable("id") Integer id) {
        if (messageService.deleteMessage(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).body("Le message n'a pas été trouvé");
        }
    }
}
