package com.trav.voy.place;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IPlaceService {

    public Place savePlace(Place customer);

    public Place updatePlace(Place customer);

    public void deletePlace(Integer customerId);

    public boolean checkIfIdexists(Integer id);

//	public Place findPlaceByEmail(String email);
//	public List<Place> findPlaceByLastName(String lastName);
    public Place findPlaceById(Integer customerId);

    public Page<Place> getPaginatedPlacesList(int begin, int end);

}
