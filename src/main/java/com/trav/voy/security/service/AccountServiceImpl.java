/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.service;

import com.trav.voy.security.dao.RolesRepository;
import com.trav.voy.security.dao.UsersRepository;
import com.trav.voy.security.entities.Roles;
import com.trav.voy.security.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MEDION
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    UsersRepository userRepository;
    @Autowired
    RolesRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder  bCryptPasswordEncoder;

    @Override
    public Users saveUser(Users u) {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }
    
    @Override
    public void updateUser(Users u) {
         userRepository.updateUser(u.getAgency(),u.getId());
    }

    @Override
    public Roles saveRole(Roles r) {
        return roleRepository.save(r);
    }

    @Override
    public Users findUsersByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        Users user = userRepository.findByUsername(username);
        Roles rolename = roleRepository.findByRole(role);

        user.getRoles().add(rolename);
    }

    @Override
    public Roles findRole(String roleName){
        return roleRepository.findByRole(roleName);
    }
}
