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
        return etudiantRepository.save(etudiant);
    }
}
