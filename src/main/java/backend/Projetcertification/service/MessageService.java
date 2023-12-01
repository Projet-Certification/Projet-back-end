package backend.Projetcertification.service;

import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.dto.mapper.UtilisateurMapper;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }
    public Message updateMessage(Message newMessage, Integer id) {
        Optional<Message> op = getMessageById(id);
        if (op.isPresent()) {
            Message message = op.get();
            message.setNotNull(newMessage);

            messageRepository.save(message);

            return newMessage;
        }
        return null;
    }
    public boolean champsVidePost(Message message) {
        if (message.getContenuMessage() == null || message.getUtilisateur() == null ||  message.getCanal() == null) {
            return true;
        }
        return false;
    }
}
