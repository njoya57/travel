/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.ticket;

import com.trav.voy.bus.Bus;
import com.trav.voy.bus.IBusDao;
import com.trav.voy.customer.Customer;
import com.trav.voy.customer.ICustomerService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Latitude
 */
@RestController
@CrossOrigin("*")
public class TicketController {

    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    IBusDao busDao;
    @Autowired
    ICustomerService customerService;

    @PostMapping("payerTickets")
    @Transactional
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketFrom) {
//        System.out.println("VOICI " + ticketFrom.toString());
        List<Ticket> tickets = new ArrayList<>();
        ticketFrom.getTickets().forEach(id -> {
//            System.out.println("NUM " + id);
            Ticket ticket = ticketRepository.findTicketById(id);
//            ticket.setNomClient(ticketFrom.getNomClient());
            ticket.setReserve(true);
            ticket.setCodePayement(ticketFrom.getCodePayement());
//            ticket.setTicketDate(LocalDateTime.now());
//            ticket.setCustomer(ticketFrom.getCustomer());

            Customer customer = new Customer();
            customer.setCni(ticketFrom.getCni());
            customer.setEmail(ticketFrom.getEmail());
            customer.setFirstName(ticketFrom.getName());
            customer.setTel(ticketFrom.getTel());
            customer = customerService.saveCustomer(customer);
            ticket.setCustomer(customer);

            ticketRepository.save(ticket);
            tickets.add(ticket);
        });
        return tickets;
    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") int id) throws IOException {
        Bus bus = busDao.findById(id).get();
        String photoName = bus.getPhoto();
        File file = new File(System.getProperty("user.home") + "/travel/images/" + photoName);
        Path path = Paths.get(file.toURI());
        return Files.readAllBytes(path);
    }

}

@Data
class TicketForm {

    private int codePayement;
//    private String nomClient;
    private List<Long> tickets = new ArrayList<>();
//    private Customer customer;

    private String cni;

    private String name;

    private String tel;

    private String email;
}
