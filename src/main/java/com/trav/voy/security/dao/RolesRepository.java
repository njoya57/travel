/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.dao;

import com.trav.voy.security.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MEDION
 */
public interface RolesRepository extends JpaRepository<Roles, Long>{
    public Roles findByRole(String role);
}
