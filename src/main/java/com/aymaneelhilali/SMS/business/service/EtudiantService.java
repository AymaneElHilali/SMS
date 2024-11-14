package com.aymaneelhilali.SMS.business.service;

import com.aymaneelhilali.SMS.dataaccess.entity.Etudiant;
import com.aymaneelhilali.SMS.dataaccess.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService{

    @Autowired
    private EtudiantRepository etudiantRepository;


    public Etudiant saveEtudiant(Etudiant etudiant){

        //check if the email already exits
        String email= etudiant.getEmail();
        if ((etudiantRepository.existsByEmail(email))){

            throw new IllegalArgumentException("Email already exists!");

        }


        // Check if password is at least 9 characters
        String password = etudiant.getPassword();
        if (password.length() < 9) {
            throw new IllegalArgumentException("Password must be at least 9 characters.");
        }

        // Regex for checking password strength
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$";
        if (!password.matches(regex)) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
        }

        //hash the password and save the etudiant
        etudiant.hashPassword(password);
        return etudiantRepository.save(etudiant);

    }
}
