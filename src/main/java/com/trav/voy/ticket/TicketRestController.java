package com.trav.voy.ticket;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/loan/api")
//@Api(value = "Ticket Rest Controller: contains all operations for managing loans")
public class TicketRestController {

	public static final Logger LOGGER = LoggerFactory.getLogger(TicketRestController.class);

	@Autowired
	private TicketServiceImpl loanService;

	/**
	 * Retourne l'historique des prêts en cours dans la bibliothèque jusqu'à une certaine date maximale. 
	 * @param maxEndDateStr
	 * @return
	 */
	@GetMapping("/maxEndDate")
//	@ApiOperation(value="List loans realized before the indicated date", response = List.class)
//	@ApiResponse(code = 200, message = "Ok: successfully listed")
	public ResponseEntity<List<TicketDTO>> searchAllBooksTicketBeforeThisDate(@RequestParam("date") String  maxEndDateStr) {
//		List<Ticket> loans = loanService.findAllTicketsByEndDateBefore(LocalDate.parse(maxEndDateStr));
//		// on retire tous les élts null que peut contenir cette liste => pour éviter les NPE par la suite
//		loans.removeAll(Collections.singleton(null));
//		List<TicketDTO> loanInfosDtos = mapTicketDtosFromTickets(loans);
//		return new ResponseEntity<List<TicketDTO>>(loanInfosDtos, HttpStatus.OK);
                return null;
	}
	
	/**
	 * Retourne la liste des prêts en cours d'un client. 
	 * @param email
	 * @return
	 */
//	@GetMapping("/customerTickets")
//	@ApiOperation(value="List loans realized before the indicated date", response = List.class)
//	@ApiResponse(code = 200, message = "Ok: successfully listed")
//	public ResponseEntity<List<TicketDTO>> searchAllOpenedTicketsOfThisCustomer(@RequestParam("email") String email) {
//		List<Ticket> loans = loanService.getAllOpenTicketsOfThisCustomer(email, TicketStatus.NON_VENDU);
//		// on retire tous les élts null que peut contenir cette liste => pour éviter les NPE par la suite
//		loans.removeAll(Collections.singleton(null));
//		List<TicketDTO> loanInfosDtos = mapTicketDtosFromTickets(loans);
//		return new ResponseEntity<List<TicketDTO>>(loanInfosDtos, HttpStatus.OK);
//	}
	
	/**
	 * Ajoute un nouveau prêt dans la base de données H2.
	 * @param simpleTicketDTORequest
	 * @param uriComponentBuilder
	 * @return
	 */
	@PostMapping("/addTicket")
//	@ApiOperation(value = "Add a new Ticket in the Library", response = TicketDTO.class)
//	@ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the loan already exist"),
//			@ApiResponse(code = 201, message = "Created: the loan is successfully inserted"),
//			@ApiResponse(code = 304, message = "Not Modified: the loan is unsuccessfully inserted") })
	public ResponseEntity<Boolean> createNewTicket(@RequestBody SimpleTicketDTO simpleTicketDTORequest,
			UriComponentsBuilder uriComponentBuilder) {
//		boolean isTicketExists = loanService.checkIfTicketExists(simpleTicketDTORequest);
//		if (isTicketExists) {
//			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
//		}
		Ticket TicketRequest = mapSimpleTicketDTOToTicket(simpleTicketDTORequest);
		Ticket loan = loanService.saveTicket(TicketRequest);
		if (loan != null) {
			return new ResponseEntity<>(true, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);

	}
	
	/**
	 * Cloture le prêt de livre d'un client.
	 * @param simpleTicketDTORequest
	 * @param uriComponentBuilder
	 * @return
	 */
	@PostMapping("/closeTicket")
//	@ApiOperation(value = "Marks as close a Ticket in the Library", response = Boolean.class)
//	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content: no loan founded"),
//			@ApiResponse(code = 200, message = "Ok: the loan is successfully closed"),
//			@ApiResponse(code = 304, message = "Not Modified: the loan is unsuccessfully closed") })
	public ResponseEntity<Boolean> closeTicket(@RequestBody SimpleTicketDTO simpleTicketDTORequest,
			UriComponentsBuilder uriComponentBuilder) {
//		Ticket existingTicket = loanService.getOpenedTicket(simpleTicketDTORequest);
//		if (existingTicket == null) {
//			return new ResponseEntity<Boolean>(false, HttpStatus.NO_CONTENT);
//		}
//		existingTicket.setStatus(TicketStatus.CLOSE);
//		Ticket loan = loanService.saveTicket(existingTicket);
//		if (loan != null) {
//			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
//		}
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

	}

	/**
	 * Transforme a Ticket List to TicketDTO List.
	 * 
	 * @param tickets
	 * @return
	 */
	private List<TicketDTO> mapTicketDtosFromTickets(List<Ticket> tickets) {

		Function<Ticket, TicketDTO> mapperFunction = (ticket) -> {
			// dans loanDTO on ajoute que les données nécessaires
			TicketDTO ticketDTO = new TicketDTO();
//			ticketDTO.getCustomerDTO().setId(ticket.getCustomer().getId());
//			ticketDTO.getCustomerDTO().setFirstName(ticket.getCustomer().getFirstName());
//			ticketDTO.getCustomerDTO().setLastName(ticket.getCustomer().getLastName());
//                        ticketDTO.getCustomerDTO().setCni(ticket.getCustomer().getCni());
//
//			ticketDTO.getPlace().setId(ticket.getPlace().getId());
//                        ticketDTO.getPlace().setNumero(ticket.getPlace().getNumero());
//                        
//			ticketDTO.getProjectionTravel().setId(ticket.getProjectionTravel().getId());
//                        ticketDTO.getProjectionTravel().setDateTravel(ticket.getProjectionTravel().getDateTravel());
//			ticketDTO.getProjectionTravel().setPrix(ticket.getProjectionTravel().getPrix());

			ticketDTO.setCodePayement(ticket.getCodePayement());
			ticketDTO.setPrix(ticket.getPrix());
//                        loanDTO.setReserve(ticket.getPrix());
			return ticketDTO;
		};

		if (!CollectionUtils.isEmpty(tickets)) {
			return tickets.stream().map(mapperFunction).sorted().collect(Collectors.toList());
		}
		return null;
	}
	
	/**
	 * Transforme un SimpleTicketDTO en Ticket avec les données minimalistes nécessaires
	 * 
	 * @param loanDTORequest
	 * @return
	 */
	private Ticket mapSimpleTicketDTOToTicket(SimpleTicketDTO simpleTicketDTO) {
		Ticket loan = new Ticket();
//		Book book = new Book();
//		book.setId(simpleTicketDTO.getBookId());
//		Customer customer = new Customer();
//		customer.setId(simpleTicketDTO.getCustomerId());
//		TicketId loanId = new TicketId(book, customer);
//		loan.setPk(loanId);
//		loan.setBeginDate(simpleTicketDTO.getBeginDate());
//		loan.setEndDate(simpleTicketDTO.getEndDate());
//		loan.setStatus(TicketStatus.OPEN);
		return loan;
	}

}
