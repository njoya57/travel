package com.trav.voy.place;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("placeService")
@Transactional
public class PlaceServiceImpl implements IPlaceService {

    @Autowired
    private PlaceRepository customerDao;

    @Override
    public Place savePlace(Place customer) {
        return customerDao.save(customer);
    }

    @Override
    public Place updatePlace(Place customer) {
        return customerDao.save(customer);
    }

    @Override
    public void deletePlace(Integer customerId) {
        customerDao.deleteById(customerId);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return customerDao.existsById(id);
    }

//	@Override
//	public Place findPlaceByEmail(String email) {
//		return customerDao.findPlaceByEmailIgnoreCase(email);
//	}
    public Place findPlaceById(Integer customerId) {
        return customerDao.getOne(customerId);
    }

    @Override
    public Page<Place> getPaginatedPlacesList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return customerDao.findAll(page);
    }

//	@Override
//	public List<Place> findPlaceByLastName(String lastName){
//		return customerDao.findPlaceByLastNameIgnoreCase(lastName);
//	}
}
