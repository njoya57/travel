package com.trav.voy.ticket;

import com.trav.voy.projectiontravel.ProjectionTravelId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("select t from Ticket t where t.id = :x")
    public Ticket findTicketById(@Param("x") long id);

    public int countByReserveAndProjectionTravelPk(boolean reserve,ProjectionTravelId projectTravelID);
}
