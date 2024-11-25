package com.aymaneelhilali.SMS.dataaccess.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SMSUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password can't be blank")
    private String password;

    @NotBlank(message = "Prenom can't be blank")
    private String prenom;

    @NotBlank(message = "Nom can't be blank")
    private String nom;

    @NotNull(message = "Date de naissance can't be null")
    @Past(message = "Date de naissance must be in the past")
    private LocalDate dateDeNaissance;

    @NotNull(message = " phone number can't be null")
    @Pattern(regexp = "^0[67][0-9]{8}$", message = "Invalid phone number")
    private String numeroTelephone;

    @Column(length = 500)
    private String adresse;

    @Enumerated(EnumType.STRING)
    private Sex sex;


    // Method to hash password
    public void hashPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    // Method to check if the password matches the hashed password
    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, this.password);
    }
}
