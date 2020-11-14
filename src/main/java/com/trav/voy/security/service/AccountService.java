/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.service;

import com.trav.voy.security.entities.Roles;
import com.trav.voy.security.entities.Users;


/**
 *
 * @author MEDION
 */
public interface AccountService {

    public Users saveUser(Users u);
    
    public void updateUser(Users u);

    public Roles saveRole(Roles r);

    public Users findUsersByUsername(String username);

    public void addRoleToUser(String username, String role);

    public Roles findRole(String roleName);
            
}
