/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.projectiontravel;

import com.trav.voy.bus.Bus;
import com.trav.voy.seance.Seance;
import com.trav.voy.ticket.Ticket;
import com.trav.voy.travel.Travel;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 *
 * @author MEDION
 */
//projection utilisé pour envoyé une fois un ensemple de requete
@Projection(name = "p1", types = {ProjectionTravel.class})
public interface ProjectTravel {

    public Long getId();

    public double getPrix();

//    @Value("#{target.dateProjectionTravel.toString()}")
    public LocalDate getDateProjectionTravel();

    public Bus getBus();

    public Travel getTravel();

//    public Seance getSeance();

    public Collection<Ticket> getTickets();

}
