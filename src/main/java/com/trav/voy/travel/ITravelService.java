package com.trav.voy.travel;

import java.util.Collection;

public interface ITravelService {
	
//	public List<Travel> findAllTicketsByTicketDateBefore(LocalDate maxEndDate);
//	
//	public List<Travel> getAllOpenTicketsOfThisCustomer(String email, TicketStatus status);
//	
//	public Travel getOpenedTicket(SimpleTicketDTO simpleTicketDTO);
//	
//	public boolean checkIfTicketExists(SimpleTicketDTO simpleTicketDTO);
	
	public Travel saveTravel(Travel travel);
	
	public void closeTravel(Travel travel);
        
        public Collection<Travel> findAllTravels();

}
