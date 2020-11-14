/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.travel;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Latitude
 */
//@ApiModel(value = "Ticket Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelDTO implements Comparable<TravelDTO> {

    private long id;
    private int numero;
    private LocalDate travelDate;
//    @Temporal(TemporalType.TIME)
    private LocalTime heureDepart;
    private String villeDepart;
    private String villeArrivee;
    @Enumerated(EnumType.STRING)
    private TravelStatus statut;
    @Enumerated(EnumType.STRING)
    private CategoryTravel categoryTravel;

    @Override
    public int compareTo(TravelDTO o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
