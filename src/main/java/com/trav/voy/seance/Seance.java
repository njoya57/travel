/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.seance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class Seance implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     @Column(name = "SEANCE_ID")
    private Long id;
//    @Column(nullable = false)
//    @Temporal(TemporalType.TIME)
//    private Date heureDepart;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seance", cascade = CascadeType.ALL)
//    private Set<ProjectionTravel> projectionTravels = new HashSet<ProjectionTravel>();
}
