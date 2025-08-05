package com.eduardasnz.GestaoDeVagas.controllers;

import com.eduardasnz.GestaoDeVagas.dto.CreateJobDTO;
import com.eduardasnz.GestaoDeVagas.entities.JobEntity;
import com.eduardasnz.GestaoDeVagas.useCase.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private CreateJobUseCase useCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CreateJobDTO dto, HttpServletRequest request) {
        try {
            var companyID = request.getAttribute("company_id");
            var jobEntity = JobEntity.builder()
                    .benefics(dto.getBenefics())
                    .companyId(UUID.fromString(companyID.toString()))
                    .description(dto.getDescription())
                    .level(dto.getLevel())
                    .build();

            return ResponseEntity.status(HttpStatus.CREATED).body(jobEntity);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
