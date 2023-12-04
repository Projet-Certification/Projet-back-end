package backend.Projetcertification.service;

import backend.Projetcertification.dto.CanalDTO;
import backend.Projetcertification.dto.CanalPutDto;
import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.mapper.CanalMapper;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.entity.Message;
import backend.Projetcertification.repository.CanalRepository;
import backend.Projetcertification.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Optional;

@Service
public class CanalService {
    @Autowired
    CanalRepository canalRepository;
    @Autowired
    MessageRepository messageRepository;

    public List<Canal> getCanaux() {
        return canalRepository.findAll();
    }

    public Optional<Canal> getCanalById(Integer id) {
        return canalRepository.findById(id);
    }

    public CanalDTO addCanal(CanalDTO canal) {
        Canal canalToEntity = CanalMapper.dtoToEntity(canal);
        if (getCanaux().isEmpty()) {
            canalToEntity.setEstGeneral(true);
        }else{
            canalToEntity.setEstGeneral(false);
        }
        canalRepository.save(canalToEntity);
        return CanalMapper.entityToDto(canalToEntity);
    }

    public CanalPutDto updateCanal(CanalPutDto canalPutDto, Integer id) {
        Optional<Canal> op = getCanalById(id);
        if (op.isPresent()) {

            Canal canal = op.get();
            canal.setNotNull(canalPutDto);

            if (!canalPutDto.isEstLeGeneral()) {
                canalRepository.save(canal);
            }
            return canalPutDto;
        }
        return null;
    }

    public Canal deleteCanal(Integer id) {
        Optional<Canal> find = canalRepository.findById(id);
        if (find.isPresent()) {
            Canal canal = find.get();
            if (!canal.isEstGeneral()) {
                canalRepository.deleteById(canal.getId());
            }
            return canal;
        }
        return null;

    }

    public boolean champsVidePost(CanalDTO canalDTO) {
        if (canalDTO.getNomCanal() == null || canalDTO.getNomCanal().isBlank()) {
            return true;
        }
        return false;
    }

}
