package com.trav.voy.travel;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("travelService")
@Transactional
public class TravelServiceImpl implements ITravelService {
	
	@Autowired
	private ITravelDao travelDao;

//	@Override
//	public List<Travel> findAllTravelsByEndDateBefore(LocalDate maxEndDate) {
//		return loanDao.findByEndDateBefore(maxEndDate);
//	}
	
//	@Override
//	public List<Travel> getAllOpenTravelsOfThisCustomer(String email, TravelStatus status) {
//		return loanDao.getAllOpenTravelsOfThisCustomer(email, status);
//	}
//	
//	@Override
//	public Travel getOpenedTravel(SimpleTravelDTO simpleTravelDTO) {
//		return loanDao.getTravelByCriteria(simpleTravelDTO.getBookId(), simpleTravelDTO.getCustomerId(), TravelStatus.VENDU);
//	}
//	
//	@Override
//	public boolean checkIfTravelExists(SimpleTravelDTO simpleTravelDTO) {
//		Travel loan = loanDao.getTravelByCriteria(simpleTravelDTO.getBookId(), simpleTravelDTO.getCustomerId(), TravelStatus.VENDU);
//		if(loan != null) {
//			return true;
//		}
//		return false;
//	}
//	
	@Override
	public Travel saveTravel(Travel travel) {
		return travelDao.save(travel);
	}
	
	/**
	 * On fera de la suppression logique car le statut de l'objet Travel est positionné à CLOSE.
	 */
	@Override
	public void closeTravel(Travel travel) {
		travelDao.save(travel);
	}

    @Override
    public Collection<Travel> findAllTravels() {
        return travelDao.findAll();
    }

}
