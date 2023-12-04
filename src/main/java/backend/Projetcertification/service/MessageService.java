package backend.Projetcertification.service;

import backend.Projetcertification.dto.MessageDTO;
import backend.Projetcertification.dto.MessagePutDTO;
import backend.Projetcertification.dto.mapper.MessageMapper;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    CanalService canalService;

    // Récupérer tous les messages
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public MessageDTO addMessage(MessageDTO newMessageDTO) {
        // Conversion du dto en entité Message
        Message message = MessageMapper.dtoToEntity(newMessageDTO);

        // Entité Utilisateur
        Optional<Utilisateur> optionUtilisateur = utilisateurService.getUtilisateurById(newMessageDTO.getIdUtilisateur());
        // Entité Canal
        Optional<Canal> optionCanal = canalService.getCanalById(newMessageDTO.getIdCanal());

        if (optionUtilisateur.isPresent()) {
            message.setUtilisateur(optionUtilisateur.get());
        }
        if (optionCanal.isPresent()) {
            message.setCanal(optionCanal.get());
        }

        Message message1 = messageRepository.save(message);

        //Rénvoie du dto afin
        MessageDTO messageDto = MessageMapper.entityToDto(message1);
        return messageDto;
    }

    public MessageDTO updateMessage(MessagePutDTO newMessagePutDto) {
        Optional<Message> op = getMessageById(newMessagePutDto.getId());
        if (op.isPresent()) {
            Message message = op.get();
            // Entité Utilisateur
            Optional<Utilisateur> optionUtilisateur = utilisateurService.getUtilisateurById(newMessagePutDto.getIdUtilisateur());
            if (optionUtilisateur.isPresent() && message.getUtilisateur().getPseudo().equals(optionUtilisateur.get().getPseudo())) {
                message.setNotNull(newMessagePutDto);

                message.setUtilisateur(optionUtilisateur.get());
            }

            messageRepository.save(message);
            MessageDTO messageDTo = MessageMapper.entityToDto(message);

            return messageDTo;
        }
        return null;
    }

    public boolean updateMessageUtilisateurDifferennt(MessagePutDTO newMessagePutDto) {

        Optional<Message> op = getMessageById(newMessagePutDto.getId());
        if (op.isPresent()) {
            Message message = op.get();
            Optional<Utilisateur> optionUtilisateur = utilisateurService.getUtilisateurById(newMessagePutDto.getIdUtilisateur());
            if (optionUtilisateur.isPresent() && !message.getUtilisateur().getPseudo().equals(optionUtilisateur.get().getPseudo())) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteMessage(Integer id) {
        if (getMessageById(id).isPresent()) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<String> champsVidePost(MessageDTO messageDTO) {
        List<String> champsManquants = new ArrayList<>();

        if (messageDTO.getContenuMessage() == null) {
            champsManquants.add("contenuMessage");
        }

        if (messageDTO.getIdCanal() == null) {
            champsManquants.add("idCanal");
        }

        if (messageDTO.getIdUtilisateur() == null) {
            champsManquants.add("idUtilisateur");
        }
        return champsManquants;
    }

    public List<String> champsVidePut(MessagePutDTO messagePutDTO) {
        List<String> champsManquants = new ArrayList<>();

        if (messagePutDTO.getContenuMessage() == null) {
            champsManquants.add("contenuMessage");
        }

        if (messagePutDTO.getIdUtilisateur() == null) {
            champsManquants.add("idUtilisateur");
        }
        return champsManquants;
    }
}
