package com.eduardasnz.GestaoDeVagas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileCandidateDTO {

  private String description;
  private String username;
  private String email;
  private String name;
  private UUID id;
}
