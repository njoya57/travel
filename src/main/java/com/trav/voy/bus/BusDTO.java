/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.bus;

/**
 *
 * @author Latitude
 */
import com.trav.voy.agency.Agency;
import com.trav.voy.place.Place;
import java.time.LocalDate;
import java.util.Collection;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(value = "Bus Model")
public class BusDTO implements Comparable<BusDTO> {

    private Integer id;

    private String immatricule;

    private String description;

    private int nbrePlace;

    private String photo;
    
    private Collection<Place> places ;
    
    private Agency agency;
//    private Collection<Agency> agencys;

    @Override
    public int compareTo(BusDTO o) {
        return this.immatricule.compareToIgnoreCase(o.getImmatricule());
    }

}
