package backend.Projetcertification.controller;

import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.service.CanalService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Canal>> findAllCanal(){
        return ResponseEntity.ok(canalService.getCanaux());
    }
    @PostMapping
    public Canal addCanal(@RequestBody Canal newCanal) {
        canalService.addCanal(newCanal);
        return newCanal;
    }
    @GetMapping("{id}")
    public ResponseEntity <?> findCanalById(@PathVariable Integer id) {
        Optional<Canal> optional = canalService.getCanalById(id);
        if (optional.isPresent()) {
            Canal canal = optional.get();
            return ResponseEntity.ok(canal);
        } else
            return ResponseEntity.notFound().build();
    }
}
