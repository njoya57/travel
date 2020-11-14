/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.customer;

import com.trav.voy.ticket.Ticket;
import java.util.Set;
import org.springframework.data.rest.core.config.Projection;

/**
 *
 * @author Latitude
 */
@Projection(name = "p2", types = {Customer.class,Ticket.class})
public interface CustomerView {

    public String getFirstName();

    public String getLastName();

    public String getCni();
    public Set<Ticket> getTickets();
//    public double getPrix();
    
}
