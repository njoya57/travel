/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.agency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trav.voy.security.entities.Users;
import com.trav.voy.ville.Ville;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Latitude
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AGENCY", //
        uniqueConstraints = { //
            @UniqueConstraint(name = "AGENCY_UK", columnNames = {"name", "VILLE_ID"})})
public class Agency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AGENCY_ID")
    private Integer id;
//    @Column(unique = true)k
    private String name;
    private LocalDate creationDate;
    private int nbreBus;
    private String description;
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "VILLE_ID", referencedColumnName = "VILLE_ID")
    private Ville ville;

    @OneToMany(mappedBy = "agency")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Users> userses;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "agency")
    Collection<AgencyVilleLigne> agencyVilleLignes;

//    public void addBus(Bus bus) {
//        this.buses.add(bus);
//        bus.getAgencys().add(this);
//    }
//
//    public void removeBus(Bus bus) {
//        this.buses.remove(bus);
//        bus.getAgencys().remove(this);
//    }
}
