package com.aymaneelhilali.SMS.dataaccess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor@NoArgsConstructor
public class Etudiant {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String prenom;
    private String nom;
    private LocalDate dateDeNaissance;
    @Column(length = 500)
    private String adresse;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private String nomParent1;
    private String nomParent2;
    private String telephone1;
    private String telephone2;

    public void hashPassword(String password) {
        // Hash the password
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public boolean checkPassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, this.password);
    }

}
