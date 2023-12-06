
package backend.Projetcertification.controller;

import backend.Projetcertification.dto.MessageDTO;
import backend.Projetcertification.dto.MessagePutDTO;
import backend.Projetcertification.dto.mapper.MessageMapper;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getAll() {
        List<Message> messages = messageService.getMessages();
        List<MessageDTO> messageDTOS = new ArrayList<>();

        for (Message entity : messages) {
//            if (entity.getUtilisateur().isActif()) {
            MessageDTO messageDTO = MessageMapper.entityToDto(entity);
//            }
            messageDTOS.add(messageDTO);
        }

        return ResponseEntity.ok(messageDTOS);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getMessageById(@PathVariable Integer id) {

        Optional<Message> optionalMessage = messageService.getMessageById(id);
        if (optionalMessage.isPresent()) {
            //if (optionalMessage.get().getUtilisateur().isActif()) {
            MessageDTO messageDTO = MessageMapper.entityToDto(optionalMessage.get());
            return ResponseEntity.ok(messageDTO);
//            } else {
//                return ResponseEntity.status(404).body("L'utilisateur est desactivé, vous ne pouvez pas voir ce " +
//                        "message");
//            }
        } else {
            return ResponseEntity.status(404).body("Le message est inexistant");
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> addMessage(@RequestBody MessageDTO messageDTO) {
        List<String> champsManquants = messageService.champsVidePost(messageDTO);
        if (!champsManquants.isEmpty()) {
            return ResponseEntity.badRequest().body("L'un des champs n'a pas été rempli " + champsManquants);
        }

        // Conversion de l'entité en DTO
        if (messageService.addMessage(messageDTO) != null) {
            MessageDTO messageDto = MessageMapper.entityToDto(messageService.addMessage(messageDTO));
            return ResponseEntity.ok(messageDto);
        }else{
            // Envoie en réponse le dto à nous en retour de la méthode addMessage
            return ResponseEntity.badRequest().body("Vous ne pouvez pas ajouter de message, ce compte est inactif");
        }

    }

    @PatchMapping("{id}")
    public ResponseEntity<?> updateMessage(@RequestBody MessagePutDTO messagePutDTO, @PathVariable Integer id) {

        if (!messagePutDTO.getId().equals(id))
            return ResponseEntity.badRequest().body("L'id de l'url est différente de celle envoyer dans le body");

        if (!messageService.getMessageById(id).isPresent()) {
            return ResponseEntity.status(404).body("Le message est inexistant");
        }
        List<String> champsManquants = messageService.champsVidePut(messagePutDTO);
        if (!champsManquants.isEmpty()) {
            return ResponseEntity.badRequest().body("L'un des champs n'a pas été rempli " + champsManquants);
        }

        if (messageService.updateMessageUtilisateurDifferent(messagePutDTO)) {
            return ResponseEntity.badRequest().body("Vous ne pouvez pas modifier le message, vous n'êtes pas " +
                    "l'utilisateur lié à ce message");
        }

        MessageDTO messageDTO = messageService.updateMessage(messagePutDTO);
        if (messageDTO != null) {
            return ResponseEntity.ok(messageDTO);
        } else {
            return ResponseEntity.badRequest().body("Une erreur c'est produite");
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
