package com.aymaneelhilali.SMS.presentation;

import com.aymaneelhilali.SMS.business.service.EtudiantService;
import com.aymaneelhilali.SMS.dataaccess.entity.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    @PostMapping("/api/saveetudiant")
    public Etudiant saveEtudiant(@RequestBody Etudiant etudiant){
        return etudiantService.saveEtudiant(etudiant);
    }
    @GetMapping("api/etudiant/{id}")
    public Etudiant getEtudiantById(@PathVariable Long id){
        return etudiantService.getEtudiantById(id);
    }


}
