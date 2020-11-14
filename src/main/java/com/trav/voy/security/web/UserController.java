/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.trav.voy.security.dao.UsersRepository;
import com.trav.voy.security.entities.Users;
import com.trav.voy.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author MEDION
 */
@RestController
@Controller
public class UserController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public Users register(@RequestBody RegistrationForm data) {
        String username = data.getUsername();
        Users user = accountService.findUsersByUsername(username);
        if (user != null) {
            throw new RuntimeException("Cet utilisateur existe déjà, reessayer un autre nom d'utilisateur");
        }
        String password = data.getPassword();
        String repassword = data.getRepassword();
        if (!password.equals(repassword)) {
            throw new RuntimeException("You must confirm your password !");
        }
        Users u = new Users();
        u.setUsername(username);
        u.setPassword(password);
        u.setFirstname(data.getFirstname());
        u.setLastname(data.getLastname());

        accountService.saveUser(u);
        accountService.addRoleToUser(username, "USER");
        return (u);
    }

    @Autowired
    UsersRepository userRepository;

    @PatchMapping("/registerUpdate")
    public ResponseEntity<?> registerUpdate(@RequestBody Users user) throws JsonProcessingException {

        accountService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
//        return ResponseEntity.ok("resource saved");
    }

    @GetMapping("/users/authenticate")
    public ResponseEntity<Users> getUsers(@RequestParam("username") String username) throws JsonProcessingException {
        Users users = null;
        try {
            users = accountService.findUsersByUsername(username);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (com.trav.voy.config.MyResourceNotFoundException exc) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, "Utilisateur inexistant !!!", exc);
        }
    }

}
