package com.eduardasnz.GestaoDeVagas.controllers;

import com.eduardasnz.GestaoDeVagas.dto.AuthCandidateDTO;
import com.eduardasnz.GestaoDeVagas.useCase.AuthCandidateUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthCandidateController {

  @Autowired
  private AuthCandidateUseCase useCase;

  @PostMapping("/candidate")
  public ResponseEntity<Object> auth(@RequestBody AuthCandidateDTO candidateDTO) {
    try {
      var token = this.useCase.execute(candidateDTO);
      return ResponseEntity.status(HttpStatus.OK).body(token);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
