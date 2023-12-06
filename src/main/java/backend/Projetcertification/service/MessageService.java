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

    public Message addMessage(MessageDTO newMessageDTO) {
        // Conversion du dto en entité Message
        Message message = MessageMapper.dtoToEntity(newMessageDTO);

        // Entité Utilisateur
        Optional<Utilisateur> optionUtilisateur = utilisateurService.getUtilisateurById(newMessageDTO.getIdUtilisateur());
        // Entité Canal
        Optional<Canal> optionCanal = canalService.getCanalById(newMessageDTO.getIdCanal());

        // Si l'utilisateur est present
        if (optionUtilisateur.isPresent()) {
            Utilisateur utilisateur = optionUtilisateur.get();
            // Si l'utilisateur est actif ( et non desactiver )
            if (utilisateur.isActif()) {
                message.setUtilisateur(optionUtilisateur.get());
                // Si le canal est present
                if (optionCanal.isPresent()) {
                    message.setCanal(optionCanal.get());
                    // On save
                    // Conversion de l'entité en DTO
                    return messageRepository.save(message);
                }
            }
        }
        return null;
    }

    public MessageDTO updateMessage(MessagePutDTO newMessagePutDto) {
        Optional<Message> op = getMessageById(newMessagePutDto.getId());
        if (op.isPresent()) {
            Message message = op.get();
            // Entité Utilisateur
            Optional<Utilisateur> optionUtilisateur = utilisateurService.getUtilisateurById(newMessagePutDto.getIdUtilisateur());
            if (optionUtilisateur.isPresent() && message.getUtilisateur().getPseudo().equals(optionUtilisateur.get().getPseudo())) {
                Utilisateur utilisateur = optionUtilisateur.get();
                // Si l'utilisateur est actif -> on peut faire la modification
                if(utilisateur.isActif()){
                    message.setNotNull(newMessagePutDto);
                    message.setUtilisateur(optionUtilisateur.get());

                    messageRepository.save(message);
                }
            }

            MessageDTO messageDTo = MessageMapper.entityToDto(message);

            return messageDTo;
        }
        return null;
    }

    public boolean updateMessageUtilisateurDifferent(MessagePutDTO newMessagePutDto) {

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
