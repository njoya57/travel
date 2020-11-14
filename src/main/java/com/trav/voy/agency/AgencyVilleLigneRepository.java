/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.agency;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Latitude
 */
public interface AgencyVilleLigneRepository extends JpaRepository<AgencyVilleLigne, Long> {
    
    @Query("SELECT v FROM AgencyVilleLigne v WHERE v.agency.id=?1 order by v.name")
     public Collection<AgencyVilleLigne> allAgencyVilleLigneByAgencyId(int id);
     
}
