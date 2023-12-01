package backend.Projetcertification.service;

import backend.Projetcertification.dto.MessageDTO;
import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.UtilisateurPutDto;
import backend.Projetcertification.dto.mapper.MessageMapper;
import backend.Projetcertification.dto.mapper.UtilisateurMapper;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.entity.Utilisateur;
import backend.Projetcertification.repository.CanalRepository;
import backend.Projetcertification.repository.MessageRepository;
import backend.Projetcertification.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public MessageDTO addMessage(MessageDTO newMessageDTO) {
        Message message = MessageMapper.dtoToEntity(newMessageDTO);
        System.out.println("L'heure est là " + message.getDateMessage());
        Optional<Utilisateur> optionUtilisateur = utilisateurService.getUtilisateurById(newMessageDTO.getIdUtilisateur());
        Optional<Canal> optionCanal = canalService.getCanalById(newMessageDTO.getIdCanal());

        if(optionUtilisateur.isPresent()){
            message.setUtilisateur(optionUtilisateur.get());
        }
        if(optionCanal.isPresent()){
            message.setCanal(optionCanal.get());
        }

        Message message1 = messageRepository.save(message);
        MessageDTO messageDto = MessageMapper.entityToDto(message1);
        return messageDto;
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

    public boolean deleteMessage(Integer id) {
        if (getMessageById(id).isPresent()) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean champsVidePost(MessageDTO messageDTO) {
        if (messageDTO.getContenuMessage() == null || messageDTO.getIdCanal() == null || messageDTO.getIdUtilisateur() == null) {
            return true;
        }
        return false;
    }
}
