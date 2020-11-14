package com.trav.voy.ticket;

import com.trav.voy.customer.Customer;
import com.trav.voy.place.Place;
import com.trav.voy.projectiontravel.ProjectionTravel;
import java.time.LocalDate;

import lombok.Data;

@Data
public class TicketDTO implements Comparable<TicketDTO> {

//    private CustomerDTO customerDTO = new CustomerDTO();

    private LocalDate ticketDate;

    private double prix;

    private long codePayement;

    private boolean reserve;

    private Customer customer;

    private Place place;

    private ProjectionTravel projectionTravel;

    @Override
    public int compareTo(TicketDTO o) {
        // ordre decroissant
        return 1;
    }

}
