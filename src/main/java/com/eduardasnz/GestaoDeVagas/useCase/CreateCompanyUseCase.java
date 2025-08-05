package com.eduardasnz.GestaoDeVagas.useCase;

import com.eduardasnz.GestaoDeVagas.entities.CompanyEntity;
import com.eduardasnz.GestaoDeVagas.exceptions.UserAlreadyExist;
import com.eduardasnz.GestaoDeVagas.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity entity){
        var companyExist = this.repository.findByUsernameOrEmail(entity.getUsername(), entity.getEmail());
        if(companyExist.isPresent()) {
            throw new UserAlreadyExist();
        }

        var password = passwordEncoder.encode(entity.getPassword());
        entity.setPassword(password);

        return this.repository.save(entity);
    }
}
