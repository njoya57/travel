/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.ticket;

import com.trav.voy.customer.Customer;
import com.trav.voy.place.Place;
import org.springframework.data.rest.core.config.Projection;

/**
 *
 * @author MEDION
 */
@Projection(name = "ticketProj", types = {Ticket.class})
public interface ProjectionTicket {

    public Long getId();

    public String getNomClient();
    
    public Customer getCustomer();

    public double getPrix();

    public long getCodePayement();

    public boolean getReserve();

    public Place getPlace();

}
