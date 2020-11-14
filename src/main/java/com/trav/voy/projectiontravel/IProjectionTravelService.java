package com.trav.voy.projectiontravel;

import com.trav.voy.agency.Agency;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;

public interface IProjectionTravelService {

	public ProjectionTravel saveProjectionTravel(ProjectionTravel projectionTravel);

	public Page<ProjectionTravel> getPaginatedProjectionTravelsList(int begin, int end);
        
        public Collection<ProjectionTravel> getProjectionTravelsList();
        
        public Collection<ProjectionTravel> findAllProjectionTravelByDateProjectionTravel(LocalDate dateProjectionTravel);
        
        public Collection<ProjectionTravel> findProjectionTravelsByDateAndAgency(LocalDate dateProjectionTravel, int agencyID);
        
        public ProjectionTravel findProjectionTravelByCriteria(int numero, String immatricule, LocalDate travelDate,int agenceId);
        
         public ProjectionTravel findProjectionTravelByPk(long id);
         
         public List<ProjectionTravel> findProjectionTravelByDateProjectionTravel(LocalDate dateProjectionTravel,int idAgence);
         
         public List<ProjectionTravel> findProjectionTravelByDateAndNumber(LocalDate dateTravel, int numero);
}
