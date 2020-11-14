package com.trav.voy.agency;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

//@Repository
@RepositoryRestResource
@Transactional
public interface IAgencyDao extends JpaRepository<Agency, Integer> {

    public Agency findAgencyByNameIgnoreCase(String name);
    
    public Collection<Agency> findAllAgencyByVilleId(int villeID);

}
