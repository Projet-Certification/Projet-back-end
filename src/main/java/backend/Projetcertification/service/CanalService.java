package backend.Projetcertification.service;

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
    public void addCanal(Canal canal){
        canalRepository.save(canal);
    }
    public Canal updateCanal(Canal canal) {
       return canalRepository.save(canal);
    }
    public void deleteCanal(Integer id){
        canalRepository.deleteById(id);
    }
}
