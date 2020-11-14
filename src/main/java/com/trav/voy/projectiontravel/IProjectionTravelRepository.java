package com.trav.voy.projectiontravel;

import com.trav.voy.agency.Agency;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
@Transactional
public interface IProjectionTravelRepository extends JpaRepository<ProjectionTravel, Long> {

//    public Collection<ProjectionTravel> findAllProjectionTravelByDateProjectionTravel(@Param("dateProjectionTravel") LocalDate dateProjectionTravel);
    @Query("select p "
            + "     from ProjectionTravel p "
            + "     where p.dateProjectionTravel=?1 "
            + "     and p.pk.agency.id=?2")
    public Collection<ProjectionTravel> findProjectionTravelsByDateAndAgency(LocalDate dateProjectionTravel, int agencyID);
//    public Collection<ProjectionTravel> findProjectionTravelByDateProjectionTravel(LocalDate dateProjectionTravel);

    <T> T findByPrix(String prix, Class<T> type);

    @Query("select p "
            + "from ProjectionTravel p "
            + "where p.pk.travel.numero=?1 "
            + "     and p.pk.bus.immatricule=?2 "
            + "     and p.pk.travel.travelDate=?3"
            + "     and p.pk.agency.id=?4")
    public ProjectionTravel findProjectionTravelByCriteria(int numero, String immatricule, LocalDate travelDate, int agenceId);

    public ProjectionTravel findProjectionTravelByPkTravelId(@Param("id") long travelId);

    @Query("select u "
            + "     from ProjectionTravel u "
            + "     where u.dateProjectionTravel=?1"
            + "     and u.pk.agency.id=?2")
    public List<ProjectionTravel> findProjectionTravelByDateProjectionTravel(LocalDate dateProjectionTravel, int idAgence);

    @Query("select u "
            + "     from ProjectionTravel u "
            + "     where u.dateProjectionTravel=?1 "
            + "     and u.pk.travel.numero=?2")
    public List<ProjectionTravel> findProjectionTravelByDateAndNumber(LocalDate dateTravel, int numero);
}
