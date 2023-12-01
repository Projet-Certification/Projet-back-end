package backend.Projetcertification.controller;

import backend.Projetcertification.entity.Canal;
import backend.Projetcertification.service.CanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("canals")
public class CanalController {
    @Autowired
    CanalService canalService;
    @GetMapping
    public ResponseEntity<List<Canal>> findAllCanal(){
        return ResponseEntity.ok(canalService.getCanaux());
    }
}
