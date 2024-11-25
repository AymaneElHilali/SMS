package com.aymaneelhilali.SMS.business.service;

import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import com.aymaneelhilali.SMS.dataaccess.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public SMSUser saveUser(SMSUser user){

        //check if the email already exits
        String email= user.getEmail();
        if ((userRepository.existsByEmail(email))){

            throw new IllegalArgumentException("Email already exists!");

        }

        String password = user.getPassword();
        // Regex for checking password strength
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{9,}$";

        if (!password.matches(regex)) {
            throw new IllegalArgumentException("Password must contain at least 9 characters, one uppercase letter, one lowercase letter, one digit, and one special character.");
        }
        user.hashPassword(password);
        return userRepository.save(user) ;

    }

    public SMSUser getUserById(Long id){
        SMSUser res = userRepository.findById(id).orElse(null);
        if (res == null){
            throw new IllegalArgumentException("0 user found with that id!");
        }
        return res;

    }
}
