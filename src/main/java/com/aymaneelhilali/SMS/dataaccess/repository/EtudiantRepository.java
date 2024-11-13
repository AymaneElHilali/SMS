package com.aymaneelhilali.SMS.dataaccess.repository;

import com.aymaneelhilali.SMS.dataaccess.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
