package com.aymaneelhilali.SMS.presentation;

import com.aymaneelhilali.SMS.business.service.GestionUsersService;
import com.aymaneelhilali.SMS.business.service.JwtService;
import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import com.aymaneelhilali.SMS.exeption.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Key;

@CrossOrigin
@RestController
public class GestionUsersController {


    @Autowired
    private JwtService jwtService;

    @Autowired
    private GestionUsersService gestionUsersService;


    @PostMapping("/api/v1/addNewUser")
    public SMSUser addNewUser( @Valid @RequestBody  SMSUser newUser){
        System.out.println("Controller");
        return gestionUsersService.addNewUser(newUser);

    }
    @GetMapping("/api/ex")
    public String ex(){
        return "hi";


    }
//    @GetMapping("/createToken")
//    public String createToken(){
//        Long id = Long.valueOf(123456789);
//        String token =jwtService.generateToken(id,"aymane@gmail.com","admin","aymane","elhilai");
//        return token;
//
//    }
    @PostMapping("/login")
    public String login(@RequestBody String email,String passwor){

        return gestionUsersService.login(email,passwor);

    }

    @GetMapping("/pass")
    public String pass(){
        return "pass";
    }


}
