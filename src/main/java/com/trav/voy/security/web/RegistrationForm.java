/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author MEDION
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {

    private String username;
    private String password;
    private String repassword;
    private String firstname;
    private String lastname;

}
