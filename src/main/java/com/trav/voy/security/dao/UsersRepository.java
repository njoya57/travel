/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.dao;

import com.trav.voy.agency.Agency;
import com.trav.voy.security.entities.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author MEDION
 */
public interface UsersRepository extends JpaRepository<Users, Long> {

    public Users findByUsername(String username);

    @Modifying
    @Query("update Users u set u.agency=?1 where u.id=?2")
    public void updateUser(Agency agency, long id);

}
