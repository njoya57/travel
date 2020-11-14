package com.trav.voy.ticket;

import com.trav.voy.customer.Customer;
import com.trav.voy.place.Place;
import com.trav.voy.projectiontravel.ProjectionTravel;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketId implements Serializable {

    @ManyToOne
    private Place place;
    @ManyToOne
    private ProjectionTravel projectionTravel;
    @ManyToOne
    private Customer customer;
    @Column(name = "CREATION_DATE_TIME")
    private LocalDateTime creationDateTime;

    public TicketId(Place place, Customer customer, ProjectionTravel projectionTravel) {
        super();
        this.place = place;
        this.customer = customer;
        this.projectionTravel = projectionTravel;
        this.creationDateTime = LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((place == null) ? 0 : place.hashCode());
        result = prime * result + ((customer == null) ? 0 : customer.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        TicketId other = (TicketId) obj;
        if (place == null) {
            if (other.place != null) {
                return false;
            }
        } else if (!place.equals(other.place)) {
            return false;
        }
        if (customer == null) {
            if (other.customer != null) {
                return false;
            }
        } else if (!customer.equals(other.customer)) {
            return false;
        }
        return true;
    }

}
