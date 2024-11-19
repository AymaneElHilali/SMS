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

        String password = etudiant.getPassword();
        //save the etudiant to chack the exeptions and hash the password when evrythink is clean
        Etudiant res = etudiantRepository.save(etudiant);
        etudiant.hashPassword(password);
        return res;

    }
}
