/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trav.voy.place.Place;
import com.trav.voy.projectiontravel.ProjectionTravel;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Latitude
 */
@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "BUS", //
        uniqueConstraints = {
            @UniqueConstraint(name = "BUS_UK", columnNames = "immatricule")})
public class Bus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BUS_ID")
    private Integer id;
//    @Column(unique = true)
    private String immatricule;
    private String description;
    private int nbrePlace;
    private String photo;

    @OneToMany(mappedBy = "bus")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Place> places;

    @OneToMany(mappedBy = "pk.bus")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ProjectionTravel> projectionTravels;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImmatricule() {
        return immatricule;
    }

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(int nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Collection<Place> getPlaces() {
        return places;
    }

    public void setPlaces(Collection<Place> places) {
        this.places = places;
    }

//     @JsonIgnore
    public Collection<ProjectionTravel> getProjectionTravels() {
        return projectionTravels;
    }

    public void setProjectionTravels(Collection<ProjectionTravel> projectionTravels) {
        this.projectionTravels = projectionTravels;
    }

}
