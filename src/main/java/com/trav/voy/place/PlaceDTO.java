/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.place;

/**
 *
 * @author Latitude
 */
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(value = "Place Model")
public class PlaceDTO implements Comparable<PlaceDTO> {

//    @ApiModelProperty(value = "Place id")
    private Integer id;

//    @ApiModelProperty(value = "Place numero")
    private int numero;

//    @ApiModelProperty(value = "Place status")
    private boolean status;

    @Override
    public int compareTo(PlaceDTO o) {
//		return this.numero.compareToIgnoreCase(o.getNumero());
        return 1;
    }

}
