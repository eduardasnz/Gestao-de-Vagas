package com.eduardasnz.GestaoDeVagas.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Pattern(regexp = "^(?!\\s)(?!.*\\s$)\\S+$", message = "The [username] field must not contain leading or trailing spaces")
    private String username;

    @Email(message = "[E-mail] is required")
    private String email;
    @NotBlank(message = "[Password] is required")
    private String password;
    private String website;
    @NotBlank(message = "[Name] is required")
    private String name;
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
