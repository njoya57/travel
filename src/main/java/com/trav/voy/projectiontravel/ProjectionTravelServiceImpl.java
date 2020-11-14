package com.trav.voy.projectiontravel;

import com.trav.voy.ticket.ITicketService;
import com.trav.voy.ticket.Ticket;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("projectionTravelService")
@Transactional
public class ProjectionTravelServiceImpl implements IProjectionTravelService {

    @Autowired
    private IProjectionTravelRepository projectionTravelDao;
    @Autowired
    ITicketService iTicketService;

    @Override
    public ProjectionTravel saveProjectionTravel(ProjectionTravel projectionTravel) {
        return projectionTravelDao.save(projectionTravel);
    }

    @Override
    public Page<ProjectionTravel> getPaginatedProjectionTravelsList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return projectionTravelDao.findAll(page);
    }

//    @Override
//    public ProjectionTravel findProjectionTravelByImmatricule(String immatricule) {
//        return projectionTravelDao.findProjectionTravelByImmatriculeIgnoreCase(immatricule);
//    }
    @Override
    public Collection<ProjectionTravel> getProjectionTravelsList() {
        return projectionTravelDao.findAll();
    }

    @Override
    public Collection<ProjectionTravel> findAllProjectionTravelByDateProjectionTravel(LocalDate dateProjectionTravel) {
////        return projectionTravelDao.findAllProjectionTravelByDateProjectionTravel(dateProjectionTravel);
        return null;
    }

    @Override
    public Collection<ProjectionTravel> findProjectionTravelsByDateAndAgency(LocalDate dateProjectionTravel, int agencyID) {
        return projectionTravelDao.findProjectionTravelsByDateAndAgency(dateProjectionTravel, agencyID);    
    }

    @Override
    public ProjectionTravel findProjectionTravelByCriteria(int numero, String immatricule, LocalDate travelDate,int agenceId) {
        return projectionTravelDao.findProjectionTravelByCriteria(numero, immatricule, travelDate, agenceId);
    }

    @Override
    public ProjectionTravel findProjectionTravelByPk(long id) {
        ProjectionTravel projectionTravel = projectionTravelDao.findProjectionTravelByPkTravelId(id);
        int totalPlaceLibre = iTicketService.ticketByReserveAndProjectionTravelPkCount(false, projectionTravel.getPk());
        projectionTravel.setTotalPlaceLibre(totalPlaceLibre);
        projectionTravel.setTotalPlaceOccupe(projectionTravel.getTotalPlace() - totalPlaceLibre);
        List<Ticket> tickets = (List<Ticket>) projectionTravel.getTickets();
        Collections.sort(tickets);
        projectionTravel.setTickets(tickets);
        return projectionTravel;
    }

    @Override
    public List<ProjectionTravel> findProjectionTravelByDateProjectionTravel(LocalDate dateProjectionTravel, int idAgence) {
        return projectionTravelDao.findProjectionTravelByDateProjectionTravel(dateProjectionTravel,idAgence);
//        return null;
    }

    @Override
    public List<ProjectionTravel> findProjectionTravelByDateAndNumber(LocalDate dateTravel, int numero) {
        return projectionTravelDao.findProjectionTravelByDateAndNumber(dateTravel, numero);
    }

}
