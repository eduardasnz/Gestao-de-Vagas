package com.eduardasnz.GestaoDeVagas.controllers;

import com.eduardasnz.GestaoDeVagas.dto.ProfileCandidateDTO;
import com.eduardasnz.GestaoDeVagas.entities.CandidateEntity;
import com.eduardasnz.GestaoDeVagas.useCase.CandidateUseCase;
import com.eduardasnz.GestaoDeVagas.useCase.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateUseCase useCase;

    @Autowired
    private ProfileCandidateUseCase useCaseProfile;

    @PostMapping("/")
    public ResponseEntity<Object>  create(@Valid @RequestBody CandidateEntity entity) {
       try {
           var result = this.useCase.execute(entity);

           return ResponseEntity.status(HttpStatus.CREATED).body(result);
       } catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    @GetMapping("/")
    public ResponseEntity<Object> get(HttpServletRequest request) {
      var idCandidate = request.getAttribute("candidate_id");
      try {
       var profile = this.useCaseProfile.execute(UUID.fromString(idCandidate.toString()));
       return ResponseEntity.status(HttpStatus.OK).body(profile);
      } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
    }
}
