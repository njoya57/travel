/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.projectiontravel;

/**
 *
 * @author Latitude
 */
import com.trav.voy.ticket.Ticket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectionTravelDTO implements Comparable<ProjectionTravelDTO> {

//    private Long id;
    private ProjectionTravelId pk = new ProjectionTravelId();
    private LocalDate dateProjectionTravel;
    private double prix;

//    private Travel travel=new Travel();
//    private BusDTO busDTO=new BusDTO();
//    private Ticket ticket=new Ticket();
    private Collection<Ticket> tickets=new ArrayList<>();
    
    @Override
    public int compareTo(ProjectionTravelDTO o) {
        return 0;
    }

   
    
}
