/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.agency;

/**
 *
 * @author Latitude
 */
import com.trav.voy.ville.Ville;
import java.time.LocalDate;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyDTO implements Comparable<AgencyDTO> {

    private Integer id;
    private String name;
    private LocalDate creationDate;
    private int nbreBus;
    private String description;
    private byte[] photo;
    private Ville ville;
//kkk
    @Override
    public int compareTo(AgencyDTO o) {
        return this.name.compareToIgnoreCase(o.getName());
    }

}
