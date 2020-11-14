/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.trav.voy.agency.Agency;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author MEDION
 */
@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Users implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstname;

    private String lastname;

    @Column(name = "USERNAME", length = 36, nullable = false, unique = true)
    private String username;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false)
    private String password;

    @Column(name = "ENABLED", length = 1, nullable = false)
    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private final Collection<Roles> roles = new ArrayList<>();

    @ManyToOne
    private Agency agency;

    public Users() {
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Users(Long id, String firstname, String lastname, String username, String password, boolean enabled, Agency agency) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.agency = agency;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonSetter
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "Users{" + "firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", password=" + password + ", enabled=" + enabled + ", roles=" + roles + ", agency=" + agency + '}';
    }

}
