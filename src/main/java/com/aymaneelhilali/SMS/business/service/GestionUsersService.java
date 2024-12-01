package com.aymaneelhilali.SMS.business.service;

import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import com.aymaneelhilali.SMS.dataaccess.repository.GestionUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionUsersService {

    @Autowired
    private GestionUsersRepo gestionUsersRepo;

    public SMSUser addNewUser(SMSUser newUser){
        //check if that email exist
        String email = newUser.getEmail();
        if (gestionUsersRepo.existsByEmail(email)){
            throw new IllegalArgumentException("Email already Used!");
        }
        String password = newUser.getPassword();
        // Regex for checking password strength
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$";

        if (!password.matches(regex)) {
            throw new IllegalArgumentException("Password must contain at least 9 characters, one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
//        newUser.hashPassword(password);
        return gestionUsersRepo.save(newUser);

    }
}
