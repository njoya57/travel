/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.agency;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Latitude
 */
public interface AgencyBusRepository extends JpaRepository<AgencyBus, Long> {
    
     public Collection<AgencyBus> findAllAgencyBusByAgencyName(String agencyName);
     
}
