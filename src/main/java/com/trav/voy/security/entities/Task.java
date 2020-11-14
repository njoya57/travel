/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.security.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author MEDION
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String taskName;
}
