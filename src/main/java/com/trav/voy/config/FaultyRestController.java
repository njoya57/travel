/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Latitude
 */
@RestController
public class FaultyRestController {
    
    @GetMapping("/exception")
    public ResponseEntity<Void> requestWithException() {
        throw new RuntimeException("Error in the faulty controller!");
    }

}