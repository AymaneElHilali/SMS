package com.aymaneelhilali.SMS.dataaccess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @Enumerated(EnumType.STRING)
    private Sex sex;

}
