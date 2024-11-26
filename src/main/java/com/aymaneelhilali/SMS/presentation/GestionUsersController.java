package com.aymaneelhilali.SMS.presentation;

import com.aymaneelhilali.SMS.business.service.GestionUsersService;
import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import com.aymaneelhilali.SMS.dataaccess.repository.GestionUsersRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class GestionUsersController {

    @Autowired
    private GestionUsersService gestionUsersService;


    @PostMapping("/api/v1/addNewUser")
    public SMSUser addNewUser( @Valid @RequestBody  SMSUser newUser){
        return gestionUsersService.addNewUser(newUser);

    }

}
