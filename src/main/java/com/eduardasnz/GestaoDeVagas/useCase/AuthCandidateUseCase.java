package com.eduardasnz.GestaoDeVagas.useCase;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.eduardasnz.GestaoDeVagas.dto.AuthCandidateDTO;
import com.eduardasnz.GestaoDeVagas.dto.AuthCandidateResponseDTO;
import com.eduardasnz.GestaoDeVagas.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthCandidateResponseDTO execute(AuthCandidateDTO dto) throws AuthenticationException {
        var candidate = this.repository.findByUsername(dto.username()).orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect."));

        var passwordIsMatches = this.passwordEncoder.matches(dto.password(), candidate.getPassword());

        if(!passwordIsMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
            .withIssuer("java#01")
            .withSubject(candidate.getId().toString())
            .withClaim("roles", Arrays.asList("candidate"))
            .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .sign(algorithm);

      return AuthCandidateResponseDTO.builder().access_token(token).build();
    }
}
