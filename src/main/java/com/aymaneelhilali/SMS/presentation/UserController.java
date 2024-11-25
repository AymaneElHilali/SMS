package com.aymaneelhilali.SMS.presentation;

import com.aymaneelhilali.SMS.business.service.UserService;
import com.aymaneelhilali.SMS.dataaccess.entity.SMSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/saveUser")
    public SMSUser saveUser(@RequestBody SMSUser user){
        return userService.saveUser(user);
    }
    @GetMapping("api/user/{id}")
    public SMSUser getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }


}
