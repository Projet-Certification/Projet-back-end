package backend.Projetcertification.service;

import backend.Projetcertification.dto.CanalDTO;
import backend.Projetcertification.dto.UtilisateurDTO;
import backend.Projetcertification.dto.mapper.CanalMapper;
import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.repository.CanalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanalService {
    @Autowired
    CanalRepository canalRepository;
    public List<Canal> getCanaux(){
        return canalRepository.findAll();
    }
    public Optional<Canal> getCanalById(Integer id){
        return canalRepository.findById(id);
    }
    public CanalDTO addCanal(CanalDTO canal){
        canalRepository.save(CanalMapper.dtoToEntity(canal));
        return canal;
    }
    public Canal updateCanal(Canal canal) {
       return canalRepository.save(canal);
    }

    public Canal deleteCanal(Integer id) {
        Optional<Canal> find = canalRepository.findById(id);
        if (find.isPresent()) {
            Canal canal = find.get();
            canalRepository.deleteById(canal.getId());
            return canal;
        }
        return null;

    }
    public boolean champsVidePost(CanalDTO  canalDTO) {
        if (canalDTO.getNomCanal() == null || canalDTO.getNomCanal().isBlank()) {
            return true;
        }
        return false;
    }
}
