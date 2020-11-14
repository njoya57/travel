package com.trav.voy.place;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
//@RepositoryRestResource
public interface PlaceRepository extends JpaRepository<Place, Integer> {

//	public Place findPlaceByEmailIgnoreCase(String email);
//	
//	public List<Place> findPlaceByLastNameIgnoreCase(String lastName);
		
}
