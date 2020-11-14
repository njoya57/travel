package com.trav.voy.projectiontravel;

import com.trav.voy.agency.Agency;
import com.trav.voy.bus.Bus;
import com.trav.voy.bus.BusDTO;
import com.trav.voy.seance.ISeanceService;
import com.trav.voy.ticket.ITicketService;
import com.trav.voy.ticket.Ticket;
import com.trav.voy.ticket.TicketDTO;
import com.trav.voy.travel.CategoryTravel;
import com.trav.voy.travel.Travel;
import com.trav.voy.travel.TravelDTO;
import com.trav.voy.travel.TravelServiceImpl;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectionTravelRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ProjectionTravelRestController.class);

    @Autowired
    private IProjectionTravelService projectionTravelService;
    @Autowired
    private TravelServiceImpl travelService;
    @Autowired
    private ISeanceService seanceService;
    @Autowired
    private ITicketService ticketService;

    @GetMapping("/projectionTrave")
    public List<ProjectionTravel> getAllBookProjectionTravels1() {
        return (List<ProjectionTravel>) projectionTravelService.getProjectionTravelsList();
    }

    @GetMapping("/allProjectionTravels")
    public ResponseEntity<List<ProjectionTravelDTO>> getAllBookProjectionTravels() {
        List<ProjectionTravel> projectionTraveles = (List<ProjectionTravel>) projectionTravelService.getProjectionTravelsList();
        if (!CollectionUtils.isEmpty(projectionTravelService.getProjectionTravelsList())) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().removeAll(Collections.singleton(null));
            List<ProjectionTravelDTO> projectionTravelDTOs = projectionTraveles.stream().map(projectionTravel -> {
                return mapProjectionTravelToProjectionTravelDTO(projectionTravel);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(projectionTravelDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/projectionByDateTravelAndAgency")
    public ResponseEntity<List<ProjectionTravel>> searchAllProjectionTravelDate(
            @RequestParam("dateProjectionTravel") String dateProjectionTravel,
            @RequestParam("agencyID") String idAgency) {
        int agencyID = 0;
        if (idAgency != null) {
            agencyID = Integer.parseInt(idAgency);
        }
//        LocalDate n = null;n.
                
        System.out.println("dateProjectionTravel " + dateProjectionTravel);
        System.out.println("dateProjectionTravXX " + LocalDate.parse(dateProjectionTravel));
        List<ProjectionTravel> projectionTravels = (List<ProjectionTravel>) projectionTravelService.findProjectionTravelsByDateAndAgency(LocalDate.parse(dateProjectionTravel), agencyID);
//                projectionTravelService.findProjectionTravelsByDateAndAgency(dateProjectionTravel, agencyID);
        // on retire tous les élts null que peut contenir cette liste => pour éviter les NPE par la suite
        projectionTravels.removeAll(Collections.singleton(null));
        List<ProjectionTravelDTO> projectionTravelDTOs = mapProjectionTravelDTOFromProjectionTravel(projectionTravels);
        return new ResponseEntity<>(projectionTravels, HttpStatus.OK);
    }

    @GetMapping("/projectionTravelByPk")
    public ResponseEntity<ProjectionTravel> searchProjectionTravelByPk(@RequestParam("projectionTravelId") long travelId) {
        ProjectionTravel projectionTravels = projectionTravelService.findProjectionTravelByPk(travelId);
        // on retire tous les élts null que peut contenir cette liste => pour éviter les NPE par la suite
        return new ResponseEntity<>(projectionTravels, HttpStatus.OK);
    }

    @GetMapping("/projectionTravels/date")
    public ResponseEntity<List<ProjectionTravelDTO>> projectionTravelDate(@RequestParam("dateTravel") String dateTravel, @RequestParam("idAgence") int idAgence) {
        System.out.println("DATE " + dateTravel);
        LocalDate date = LocalDate.parse(dateTravel);
        List<ProjectionTravel> projectionTraveles = (List<ProjectionTravel>) projectionTravelService.findProjectionTravelByDateProjectionTravel(date, idAgence);
        if (!CollectionUtils.isEmpty(projectionTravelService.findProjectionTravelByDateProjectionTravel(date, idAgence))) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().removeAll(Collections.singleton(null));
            List<ProjectionTravelDTO> projectionTravelDTOs = projectionTraveles.stream().map(projectionTravel -> {
                return mapProjectionTravelToProjectionTravelDTO(projectionTravel);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(projectionTravelDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param projectionForm
     * @return
     */
    @PostMapping("/addProjectionTravel")
    public ResponseEntity<ProjectionTravelDTO> createNewProjectionTravel(@RequestBody ProjectionForm projectionForm) {
        System.out.println("VVVVVVV " + projectionForm.getVilleArrivee());
        ProjectionTravel projectionTravel = projectionTravelService.findProjectionTravelByCriteria(
                projectionForm.getNumero(), projectionForm.getBus().getImmatricule(), projectionForm.getTravelDate(),
                projectionForm.getAgency().getId());

        if (projectionTravel != null) {
            System.out.println("PPPPPPPPPPPPPPPPP " + projectionTravel.getPrix());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
        TravelDTO travelDTO = new TravelDTO();
        travelDTO.setCategoryTravel(projectionForm.getCategoryTravel());
        travelDTO.setHeureDepart(projectionForm.getHeureDepart());
        travelDTO.setId(projectionForm.getId());
        travelDTO.setNumero(projectionForm.getNumero());
        travelDTO.setTravelDate(projectionForm.getTravelDate());
        travelDTO.setVilleArrivee(projectionForm.getVilleArrivee());
        travelDTO.setVilleDepart(projectionForm.getVilleDepart());

        Travel travelRequest = mapTravelDTOToTravel(travelDTO);
        Travel travelResponse = travelService.saveTravel(travelRequest);

        ProjectionTravelDTO ptdto = new ProjectionTravelDTO();
        ptdto.getPk().setBus(projectionForm.getBus());
        ptdto.setDateProjectionTravel(projectionForm.getTravelDate());
        ptdto.setPrix(projectionForm.getPrix());
        ptdto.getPk().setTravel((travelResponse));
//        ptdto.setTicketDTO(mapTicketDTOFromTicket());
        ptdto.getPk().setCreationDateTime(LocalDateTime.now());
        ptdto.getPk().setAgency(projectionForm.getAgency());

//        System.out.println("HHHHHHHHHHHHHHHHH " + ptdto.getTravelDTO().toString());
        ProjectionTravel projectionTravelRequest = mapProjectionTravelDTOToProjectionTravel(ptdto);
        System.out.println("GGGGGGGGGGGGGG " + projectionTravelRequest.toString());
        ProjectionTravel projectionTravelResponse = projectionTravelService.saveProjectionTravel(projectionTravelRequest);
        initTicket(projectionTravelResponse);
        if (projectionTravelResponse != null) {
            ProjectionTravelDTO projectionTravelDTO = mapProjectionTravelToProjectionTravelDTO(projectionTravelResponse);
            return new ResponseEntity<>(projectionTravelDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
//
    }

    public void initTicket(ProjectionTravel projection) {
        projection.getPk().getBus().getPlaces().forEach(place -> {
            Ticket ticket = new Ticket();
            ticket.setPlace(place);
            ticket.setPrix(projection.getPrix());
            ticket.setProjectionTravel(projection);
//            ticket.setTicketDate(null);
            ticket.setReserve(false);
            ticketService.saveTicket(ticket);
        });
    }

    private Travel mapTravelDTOToTravel(TravelDTO TravelDTO) {
        ModelMapper mapper = new ModelMapper();
        Travel travel = mapper.map(TravelDTO, Travel.class);
        return travel;
    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param customerDTORequestprojectionTravelDTORequest@return
     */
////    @PutMapping("/updateProjectionTravel")
//    @ApiOperation(value = "Update/Modify an existing customer in the Library", response = ProjectionTravelDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 404, message = "Not Found : the customer does not exist"),
////        @ApiResponse(code = 200, message = "Ok: the customer is successfully updated"),
////        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated")})
//    public ResponseEntity<ProjectionTravelDTO> updateProjectionTravel(@RequestBody ProjectionTravelDTO projectionTravelDTORequest) {
//        //, UriComponentsBuilder uriComponentBuilder
//        if (!projectionTravelService.checkIfIdexists(projectionTravelDTORequest.getId())) {
//            return new ResponseEntity<ProjectionTravelDTO>(HttpStatus.NOT_FOUND);
//        }
//        ProjectionTravel projectionTravelRequest = mapProjectionTravelDTOToProjectionTravel(projectionTravelDTORequest);
//        ProjectionTravel projectionTravelResponse = projectionTravelService.updateProjectionTravel(projectionTravelRequest);
//        if (projectionTravelResponse != null) {
//            ProjectionTravelDTO projectionTravelDTO = mapProjectionTravelToProjectionTravelDTO(projectionTravelResponse);
//            return new ResponseEntity<ProjectionTravelDTO>(projectionTravelDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<ProjectionTravelDTO>(HttpStatus.NOT_MODIFIED);
//    }
//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param customerId
//	 * @return
//	 */
////    @GetMapping("/paginatedSearch")
//////    @ApiOperation(value = "List customers of the Library in a paginated way", response = List.class)
//////    @ApiResponses(value = {
//////        @ApiResponse(code = 200, message = "Ok: successfully listed"),
//////        @ApiResponse(code = 204, message = "No Content: no result founded"),})
////    public ResponseEntity<List<ProjectionTravelDTO>> searchProjectionTravels(@RequestParam("beginPage") int beginPage,
////            @RequestParam("endPage") int endPage) {
////        //, UriComponentsBuilder uriComponentBuilder
////        Page<ProjectionTravel> projectionTraveles = projectionTravelService.getPaginatedProjectionTravelsList(beginPage, endPage);
////        if (projectionTraveles != null) {
////            List<ProjectionTravelDTO> projectionTravelDTOs = projectionTraveles.stream().map(projectionTravel -> {
////                return mapProjectionTravelToProjectionTravelDTO(projectionTravel);
////            }).collect(Collectors.toList());
////            return new ResponseEntity<List<ProjectionTravelDTO>>(projectionTravelDTOs, HttpStatus.OK);
////        }
////        return new ResponseEntity<List<ProjectionTravelDTO>>(HttpStatus.NO_CONTENT);
////    }
//////
//////	/**
//	 * Retourne le client ayant l'adresse email passé en paramètre.
//	 * @param email
//	 * @return
//	 */
//    @GetMapping("/searchByEmail")
//    @ApiOperation(value = "Search a customer in the Library by its email", response = ProjectionTravelDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<ProjectionTravelDTO> searchProjectionTravelByEmail(@RequestParam("email") String email) {
    //, UriComponentsBuilder uriComponentBuilder
//        ProjectionTravel customer = customerService.findProjectionTravelByEmail(email);
//        if (customer != null) {
//            ProjectionTravelDTO customerDTO = mapProjectionTravelToProjectionTravelDTO(customer);
//            return new ResponseEntity<ProjectionTravelDTO>(customerDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<ProjectionTravelDTO>(HttpStatus.NO_CONTENT);
//    }
//	
//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */
//    @GetMapping("/searchByLastName")
//    @ApiOperation(value = "Search a customer in the Library by its Last name", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<List<ProjectionTravelDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
    //,	UriComponentsBuilder uriComponentBuilder
//        List<ProjectionTravel> customers = customerService.findProjectionTravelByLastName(lastName);
//        if (customers != null && !CollectionUtils.isEmpty(customers)) {
//            List<ProjectionTravelDTO> customerDTOs = customers.stream().map(customer -> {
//                return mapProjectionTravelToProjectionTravelDTO(customer);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<ProjectionTravelDTO>>(customerDTOs, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<ProjectionTravelDTO>>(HttpStatus.NO_CONTENT);
//    }
    /**
     * Transforme un entity ProjectionTravel en un POJO ProjectionTravelDTO
     *
     * @paramprojectionTravelcustomer
     * @return
     */
    private ProjectionTravelDTO mapProjectionTravelToProjectionTravelDTO(ProjectionTravel projectionTravel) {
        ModelMapper mapper = new ModelMapper();
        ProjectionTravelDTO projectionTravelDTO = mapper.map(projectionTravel, ProjectionTravelDTO.class);
        return projectionTravelDTO;
    }

    /**
     * Transforme un POJO ProjectionTravelDTO en en entity ProjectionTravel
     *
     * @param projectionTravelDTO
     * @return
     */
    private ProjectionTravel mapProjectionTravelDTOToProjectionTravel(ProjectionTravelDTO projectionTravelDTO) {
        ProjectionTravel projectionTravel = new ProjectionTravel();
        Travel travel = new Travel();
        Bus bus = new Bus();
        Agency agency = new Agency();

        travel.setId(projectionTravelDTO.getPk().getTravel().getId());
        bus.setId(projectionTravelDTO.getPk().getBus().getId());
        bus.setPlaces(projectionTravelDTO.getPk().getBus().getPlaces());
        agency.setId(projectionTravelDTO.getPk().getAgency().getId());

        ProjectionTravelId projectionTravelId = new ProjectionTravelId(travel, bus, agency, LocalDateTime.now());
        projectionTravel.setPk(projectionTravelId);
        projectionTravel.setDateProjectionTravel(projectionTravelDTO.getDateProjectionTravel());
        projectionTravel.setPrix(projectionTravelDTO.getPrix());
//        projectionTravel.setTickets((Collection<Ticket>) projectionTravelDTO.getTicketDTO());

        return projectionTravel;
    }

    private List<ProjectionTravelDTO> mapProjectionTravelDTOFromProjectionTravel(List<ProjectionTravel> pt) {

        Function<ProjectionTravel, ProjectionTravelDTO> mapperFunction = (pts) -> {
            ProjectionTravelDTO ptdto = new ProjectionTravelDTO();
//            ptdto.getTravelDTO().setId(pts.getPk().getTravel().getId());
//            ptdto.getTravelDTO().setCategoryTravel(pts.getPk().getTravel().getCategoryTravel());
//            ptdto.getTravelDTO().setNumero(pts.getPk().getTravel().getNumero());
//            tO.getTravelDTO().setHeureDepart(pts.getPk().getTravel().getHeureDepart());
//            tO.getTravelDTO().setTravelDate(pts.getPk().getTravel().getTravelDate());

//            ptdto.getBusDTO().setId(pts.getPk().getBus().getId());
//            ptdto.getBusDTO().setImmatricule(pts.getPk().getBus().getImmatricule());
//            ptdto.getBusDTO().setDescription(pts.getPk().getBus().getDescription());
//            ptdto.getBusDTO().setNbrePlace(pts.getPk().getBus().getNbrePlace());
//            ptdto.getBusDTO().setPhoto(pts.getPk().getBus().getPhoto());
            ptdto.setPrix(pts.getPrix());
//            ptdto.setTicketDTO((TicketDTO) pts.getTickets());

            return ptdto;
        };

        if (!CollectionUtils.isEmpty(pt)) {
            return pt.stream().map(mapperFunction).sorted().collect(Collectors.toList());
        }
        return null;
    }

    private TravelDTO mapTravelToTravelDTO(Travel travel) {
        ModelMapper mapper = new ModelMapper();
        TravelDTO travelDTO = mapper.map(travel, TravelDTO.class);
        return travelDTO;
    }

//    private SeanceDTO mapSeanceToSeanceDTO(Seance seance) {
//        ModelMapper mapper = new ModelMapper();
//        SeanceDTO seanceDTO = mapper.map(seance, SeanceDTO.class);
//        return seanceDTO;
//    }
    private BusDTO mapBusToBusDTO(Bus bus) {
        ModelMapper mapper = new ModelMapper();
        BusDTO busDTO = mapper.map(bus, BusDTO.class);
        return busDTO;
    }

    private List<TicketDTO> mapTicketDTOFromTicket(List<Ticket> tickets) {

        Function<Ticket, TicketDTO> mapperFunction = (pts) -> {
            TicketDTO ticDTO = new TicketDTO();
            ticDTO.setCustomer(pts.getCustomer());
            ticDTO.setPlace(pts.getPlace());
            ticDTO.setCodePayement(pts.getCodePayement());
            ticDTO.setPrix(pts.getPrix());
            ticDTO.setPlace(pts.getPlace());
            ticDTO.setReserve(pts.isReserve());
            ticDTO.setTicketDate(pts.getTicketDate());
            return ticDTO;
        };

        if (!CollectionUtils.isEmpty(tickets)) {
            return tickets.stream().map(mapperFunction).sorted().collect(Collectors.toList());
        }
        return null;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProjectionForm {

    private long id;
    private int numero;
    private LocalDate travelDate;
//    @Temporal(TemporalType.TIME)
    private LocalTime heureDepart;
    private String villeArrivee;
    private String villeDepart;
    @Enumerated(EnumType.STRING)
    private CategoryTravel categoryTravel;

    private LocalDate dateProjectionTravel;
    private double prix;
    private TravelDTO travelDTO;
    private Bus bus;
//    private Collection<Ticket> tickets;
    private Agency agency;
}
