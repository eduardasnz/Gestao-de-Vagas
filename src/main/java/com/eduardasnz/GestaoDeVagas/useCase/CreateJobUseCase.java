package com.eduardasnz.GestaoDeVagas.useCase;

import com.eduardasnz.GestaoDeVagas.entities.JobEntity;
import com.eduardasnz.GestaoDeVagas.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository repository;

    public JobEntity execute(JobEntity entity) {
        return this.repository.save(entity);
    }
}
