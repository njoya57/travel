/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.ville;

/**
 *
 * @author Latitude
 */

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ApiModel(value = "Ville Model")
public class VilleDTO implements Comparable<VilleDTO> {

//    @ApiModelProperty(value = "Ville id")
    private Integer id;

//    @ApiModelProperty(value = "Ville name")
    private String name;

    @Override
    public int compareTo(VilleDTO o) {
        return this.name.compareToIgnoreCase(o.getName());
    }

}
