package com.trav.voy.ticket;

import com.trav.voy.projectiontravel.ProjectionTravelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("ticketService")
@Transactional
public class TicketServiceImpl implements ITicketService {
	
	@Autowired
	private TicketRepository ticketDao;

//	@Override
//	public List<Ticket> findAllTicketsByEndDateBefore(LocalDate maxEndDate) {
//		return loanDao.findByEndDateBefore(maxEndDate);
//	}
	
//	@Override
//	public List<Ticket> getAllOpenTicketsOfThisCustomer(String email, TicketStatus status) {
//		return loanDao.getAllOpenTicketsOfThisCustomer(email, status);
//	}
//	
//	@Override
//	public Ticket getOpenedTicket(SimpleTicketDTO simpleTicketDTO) {
//		return loanDao.getTicketByCriteria(simpleTicketDTO.getBookId(), simpleTicketDTO.getCustomerId(), TicketStatus.VENDU);
//	}
//	
//	@Override
//	public boolean checkIfTicketExists(SimpleTicketDTO simpleTicketDTO) {
//		Ticket loan = loanDao.getTicketByCriteria(simpleTicketDTO.getBookId(), simpleTicketDTO.getCustomerId(), TicketStatus.VENDU);
//		if(loan != null) {
//			return true;
//		}
//		return false;
//	}
//	
	@Override
	public Ticket saveTicket(Ticket ticket) {
		return ticketDao.save(ticket);
	}
	
	/**
	 * On fera de la suppression logique car le statut de l'objet Ticket est positionné à CLOSE.
	 */
	@Override
	public void closeTicket(Ticket ticket) {
		ticketDao.save(ticket);
	}

//    @Override
//    public List<Ticket> findAllTicketsByTicketDateBefore(LocalDate maxEndDate) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

        @Override
        public int ticketByReserveAndProjectionTravelPkCount(boolean reserve,ProjectionTravelId pk){
            return ticketDao.countByReserveAndProjectionTravelPk(reserve, pk);
        }
}
