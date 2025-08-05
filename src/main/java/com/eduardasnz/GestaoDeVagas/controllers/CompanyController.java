package com.eduardasnz.GestaoDeVagas.controllers;


import com.eduardasnz.GestaoDeVagas.entities.CompanyEntity;
import com.eduardasnz.GestaoDeVagas.exceptions.UserAlreadyExist;
import com.eduardasnz.GestaoDeVagas.useCase.CreateCompanyUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CreateCompanyUseCase useCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity entity) {
        try {
            var createCompany = this.useCase.execute(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(createCompany);
        } catch (UserAlreadyExist e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
