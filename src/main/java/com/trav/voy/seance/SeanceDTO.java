/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.travel;

import com.trav.voy.customer.CustomerDTO;
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
public class SeanceDTO implements Comparable<SeanceDTO> {

    private long id;
     @Temporal(TemporalType.TIME)
    private Date heureDepart;

    @Override
    public int compareTo(SeanceDTO o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
