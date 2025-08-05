package com.eduardasnz.GestaoDeVagas.useCase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eduardasnz.GestaoDeVagas.dto.AuthCompanyDTO;
import com.eduardasnz.GestaoDeVagas.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;

@Service
public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository repository;
    @Autowired
    private PasswordEncoder encoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public String execute(AuthCompanyDTO dto) throws AuthenticationException {
        var companyExist = this.repository.findByUsername(dto.getUsername());
        if(companyExist == null) {
            throw new UsernameNotFoundException("Username not found");
        }

        // verify password
        var passwordMatches = this.encoder.matches(dto.getPassword(), companyExist.getPassword());
        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        // token
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create().withIssuer("java#01")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(companyExist.getId().toString()).sign(algorithm);
    }
}
