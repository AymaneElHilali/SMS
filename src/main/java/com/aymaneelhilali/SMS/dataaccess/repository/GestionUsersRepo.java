package com.aymaneelhilali.SMS.dataaccess.repository;

import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionUsersRepo extends JpaRepository<SMSUser, Long> {

     boolean existsByEmail(String email);

}