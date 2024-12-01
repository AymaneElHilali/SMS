package com.aymaneelhilali.SMS.business.service;

import com.aymaneelhilali.SMS.dataaccess.entity.MyUserDetails;
import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import com.aymaneelhilali.SMS.dataaccess.repository.GestionUsersRepo;
import com.aymaneelhilali.SMS.exeption.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyuserDetailsService implements UserDetailsService {

    @Autowired
    private GestionUsersRepo gestionUsersRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        SMSUser myuser = gestionUsersRepo.findByEmail(email);
        if (myuser == null){
            throw new NotFoundException("user Not Found");
        }
        return new MyUserDetails(myuser);
    }
}
