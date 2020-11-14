/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.projectiontravel;

import com.trav.voy.ticket.Ticket;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
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
@AssociationOverrides({
    @AssociationOverride(name = "pk.travel", joinColumns = @JoinColumn(name = "TRAVEL_ID")),
    @AssociationOverride(name = "pk.bus", joinColumns = @JoinColumn(name = "BUS_ID"))
////    @AssociationOverride(name = "pk.seance", joinColumns = @JoinColumn(name = "SEANCE_ID"))
})
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"TRAVEL_ID", "BUS_ID", "dateProjectionTravel"})})
public class ProjectionTravel implements Serializable {

    @EmbeddedId
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProjectionTravelId pk = new ProjectionTravelId();
//    @Temporal(TemporalType.DATE)
    private LocalDate dateProjectionTravel;
    private double prix;

    @OneToMany(mappedBy = "projectionTravel")
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;
    @Transient
    private int totalPlaceLibre = 0;
    @Transient
    private int totalPlaceOccupe = 0;
    @Transient
    private int totalPlace = 0;

//     @JsonIgnore
    public ProjectionTravelId getPk() {
        return pk;
    }

    public void setPk(ProjectionTravelId pk) {
        this.pk = pk;
    }

    public LocalDate getDateProjectionTravel() {
        return dateProjectionTravel;
    }

    public void setDateProjectionTravel(LocalDate dateProjectionTravel) {
        this.dateProjectionTravel = dateProjectionTravel;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Collection<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getTotalPlaceLibre() {
        return totalPlaceLibre;
    }

    public void setTotalPlaceLibre(int totalPlaceLibre) {
        this.totalPlaceLibre = totalPlaceLibre;
    }

    public int getTotalPlaceOccupe() {
        return totalPlaceOccupe;
    }

    public void setTotalPlaceOccupe(int totalPlaceOccupe) {
        this.totalPlaceOccupe = totalPlaceOccupe;
    }

    public int getTotalPlace() {
//        int sum = Collections.frequency(tickets, new Ticket(false));
        return tickets.size();
    }

    public void setTotalPlace(int totalPlace) {
        this.totalPlace = totalPlace;
    }

}
