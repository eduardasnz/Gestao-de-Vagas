package com.eduardasnz.GestaoDeVagas.useCase;

import com.eduardasnz.GestaoDeVagas.entities.CandidateEntity;
import com.eduardasnz.GestaoDeVagas.exceptions.UserAlreadyExist;
import com.eduardasnz.GestaoDeVagas.providers.JWTProvider;
import com.eduardasnz.GestaoDeVagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CandidateUseCase {

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public CandidateEntity execute(CandidateEntity entity){
        var userAlreadyExist = this.repository.findByUsernameOrEmail(entity.getUsername(), entity.getEmail());

        var password = encoder.encode(entity.getPassword());
        entity.setPassword(password);

        if(userAlreadyExist.isPresent()) {
            throw new UserAlreadyExist();
        }

        return this.repository.save(entity);
    }
}
