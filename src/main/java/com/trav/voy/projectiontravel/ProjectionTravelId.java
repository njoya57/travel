package com.trav.voy.projectiontravel;

import com.trav.voy.agency.Agency;
import com.trav.voy.bus.Bus;
import com.trav.voy.travel.Travel;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable
//@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectionTravelId implements Serializable {

//    private static final long serialVersionUID = 3912193101593832821L;
    @ManyToOne
    private Travel travel;

    @ManyToOne
//    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Bus bus;

    @ManyToOne
    private Agency agency;

    private LocalDateTime creationDateTime;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((travel == null) ? 0 : travel.hashCode());
        result = prime * result + ((bus == null) ? 0 : bus.hashCode());
//        result = prime * result + ((bus == null) ? 0 : bus.hashCode());
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
        ProjectionTravelId other = (ProjectionTravelId) obj;
        if (travel == null) {
            if (other.travel != null) {
                return false;
            }
        } else if (!travel.equals(other.travel)) {
            return false;
        }
        if (bus == null) {
            if (other.bus != null) {
                return false;
            }
        } else if (!bus.equals(other.bus)) {
            return false;
        }
        if (agency == null) {
            if (other.agency != null) {
                return false;
            }
        } else if (!agency.equals(other.agency)) {
            return false;
        }
        return true;
    }

//     @JsonIgnore
    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Agency getAgency() {
        return agency;
    }

    public void setAgency(Agency agency) {
        this.agency = agency;
    }

}
