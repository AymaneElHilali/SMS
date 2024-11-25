package com.aymaneelhilali.SMS.dataaccess.repository;

import com.aymaneelhilali.SMS.dataaccess.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
}
