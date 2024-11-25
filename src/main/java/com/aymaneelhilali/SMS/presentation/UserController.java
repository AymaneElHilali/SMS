package com.aymaneelhilali.SMS.presentation;

import com.aymaneelhilali.SMS.business.service.UserService;
import com.aymaneelhilali.SMS.dataaccess.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/saveUser")
    public User saveEtudiant(@RequestBody User user){
        return userService.saveUser(user);
    }
    @GetMapping("api/user/{id}")
    public User getEtudiantById(@PathVariable Long id){
        return userService.getUserById(id);
    }


}
