package com.trav.voy.travel;

import com.trav.voy.bus.Bus;
import com.trav.voy.customer.Customer;
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
public class TravelId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3912193101593832821L;

        @ManyToOne
	private Bus bus;
	@ManyToOne
	private Customer customer;
	@Column(name = "CREATION_DATE_TIME")
	private LocalDateTime creationDateTime;
	
//	public TicketId() {
//		super();
//	}

	public TravelId(Bus bus, Customer customer) {
		super();
		this.bus = bus;
		this.customer = customer;
		this.creationDateTime = LocalDateTime.now();
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bus == null) ? 0 : bus.hashCode());
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TravelId other = (TravelId) obj;
		if (bus == null) {
			if (other.bus != null)
				return false;
		} else if (!bus.equals(other.bus))
			return false;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		return true;
	}
	
}
