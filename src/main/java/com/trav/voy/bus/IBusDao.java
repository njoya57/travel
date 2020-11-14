package com.trav.voy.bus;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
@Transactional
public interface IBusDao extends JpaRepository<Bus, Integer> {

//    public Collection<Bus> findAllAgencyBusByAgencyName(String agencyName);
//    public Collection<Bus> findAllBusByAgencyName(String agencyName);
    
//////    public Collection<Bus> findAllBusByAgencyId(int agencyID);
    
//    
    
	public Bus findBusByImmatriculeIgnoreCase(String immatricule);
		
}
