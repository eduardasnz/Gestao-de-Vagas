package com.eduardasnz.GestaoDeVagas.useCase;

import com.eduardasnz.GestaoDeVagas.dto.ProfileCandidateDTO;
import com.eduardasnz.GestaoDeVagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository repository;

  public ProfileCandidateDTO execute(UUID idCandidate) {
    var candidate = this.repository.findById(idCandidate)
        .orElseThrow(() -> new UsernameNotFoundException("Unauthorized"));

    return ProfileCandidateDTO.builder()
        .username(candidate.getUsername())
        .name(candidate.getName())
        .email(candidate.getEmail())
        .description(candidate.getDescription())
        .id(candidate.getId())
        .build();
  }
}
