package com.trav.voy.ticket;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trav.voy.customer.Customer;
import com.trav.voy.place.Place;
import com.trav.voy.projectiontravel.ProjectionTravel;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@AssociationOverrides({
//@AssociationOverride(name = "pk.book", joinColumns = @JoinColumn(name = "BOOK_ID")),
//@AssociationOverride(name = "pk.customer", joinColumns = @JoinColumn(name = "CUSTOMER_ID"))
//})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable,Comparable<Ticket> {

    /**
     *
     */
//    @EmbeddedId
//    private TicketId pk = new TicketId();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double prix;
//    @Column(nullable = false)
//    private LocalDateTime ticketDate;
    @Column(unique = false, nullable = true)
    private long codePayement;
    private boolean reserve;
    private LocalDate ticketDate;

//    @Enumerated(EnumType.STRING)
//    private TicketStatus status;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Place place;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private ProjectionTravel projectionTravel;

    public Ticket(boolean reserve) {
        this.reserve = reserve;
    }

    @Override
    public int compareTo(Ticket o) {
        return (this.place.getNumero()-o.getPlace().getNumero());
    }

}
