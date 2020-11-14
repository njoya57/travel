/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.travel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trav.voy.projectiontravel.ProjectionTravel;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author Latitude
 */
@Entity
//@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
//@Table(uniqueConstraints = {
//    @UniqueConstraint(columnNames = {"travelDate", "numero"})})
public class Travel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRAVEL_ID")
    private long id;
    private int numero;
    private LocalDate travelDate;
    private LocalTime heureDepart;
    private String villeDepart;
    private String villeArrivee;
    @Enumerated(EnumType.STRING)
    private TravelStatus statut;
    @Enumerated(EnumType.STRING)
    private CategoryTravel categoryTravel;

    @OneToMany(mappedBy = "pk.travel")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<ProjectionTravel> projectionTravels;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public CategoryTravel getCategoryTravel() {
        return categoryTravel;
    }

    public void setCategoryTravel(CategoryTravel categoryTravel) {
        this.categoryTravel = categoryTravel;
    }

//    @JsonIgnore
    public Collection<ProjectionTravel> getProjectionTravels() {
        return projectionTravels;
    }

    public void setProjectionTravels(Collection<ProjectionTravel> projectionTravels) {
        this.projectionTravels = projectionTravels;
    }

    public TravelStatus getStatut() {
        return statut;
    }

    public void setStatut(TravelStatus statut) {
        this.statut = statut;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }
    
    

}
