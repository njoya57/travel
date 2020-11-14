package com.trav.voy.customer;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/travel/rest/customer/api")
//@Api(value = "Customer Rest Controller: contains all operations for managing customers")
public class CustomerRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(CustomerRestController.class);

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping("/allCustomers")
    public ResponseEntity<List<CustomerDTO>> getAllBookCustomers() {
        Page<Customer> custumers = customerService.getPaginatedCustomersList(0, 10);
        if (!CollectionUtils.isEmpty(custumers.toList())) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().removeAll(Collections.singleton(null));
            List<CustomerDTO> custumerDTOs = custumers.stream().map(custumer -> {
                return mapCustomerToCustomerDTO(custumer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<CustomerDTO>>(custumerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param customerDTORequest
     * @return
     */
    @PostMapping("/addCustomer")
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        Customer existingCustomer = customerService.findCustomerByEmail(customerDTORequest.getEmail());
        if (existingCustomer != null) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.CONFLICT);
        }
        Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);
//        customerRequest.setCreationDate(LocalDate.now());
        Customer customerResponse = customerService.saveCustomer(customerRequest);
        if (customerResponse != null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customerResponse);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param customerDTORequest
     * @return
     */
    @PutMapping("/updateCustomer")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        if (!customerService.checkIfIdexists(customerDTORequest.getId())) {
            return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_FOUND);
        }
        Customer customerRequest = mapCustomerDTOToCustomer(customerDTORequest);
        Customer customerResponse = customerService.updateCustomer(customerRequest);
        if (customerResponse != null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customerResponse);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NOT_MODIFIED);
    }

//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param customerId
//	 * @return
//	 */
    @DeleteMapping("/deleteCustomer/{customerId}")
//    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
//    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
//

    @GetMapping("/paginatedSearch")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(@RequestParam("beginPage") int beginPage,
            @RequestParam("endPage") int endPage) {
        //, UriComponentsBuilder uriComponentBuilder
        Page<Customer> customers = customerService.getPaginatedCustomersList(beginPage, endPage);
        if (customers != null) {
            List<CustomerDTO> customerDTOs = customers.stream().map(customer -> {
                return mapCustomerToCustomerDTO(customer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<CustomerDTO>>(customerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
    }
//
//	/**
//	 * Retourne le client ayant l'adresse email passé en paramètre.
//	 * @param email
//	 * @return
//	 */

    @GetMapping("/searchByEmail")
    public ResponseEntity<CustomerDTO> searchCustomerByEmail(@RequestParam("email") String email) {
        //, UriComponentsBuilder uriComponentBuilder
        Customer customer = customerService.findCustomerByEmail(email);
        if (customer != null) {
            CustomerDTO customerDTO = mapCustomerToCustomerDTO(customer);
            return new ResponseEntity<CustomerDTO>(customerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<CustomerDTO>(HttpStatus.NO_CONTENT);
    }
//	
//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */

    @GetMapping("/searchByLastName")
    public ResponseEntity<List<CustomerDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
        //,	UriComponentsBuilder uriComponentBuilder
        List<Customer> customers = customerService.findCustomerByLastName(lastName);
        if (customers != null && !CollectionUtils.isEmpty(customers)) {
            List<CustomerDTO> customerDTOs = customers.stream().map(customer -> {
                return mapCustomerToCustomerDTO(customer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<CustomerDTO>>(customerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<CustomerDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Transforme un entity Customer en un POJO CustomerDTO
     *
     * @param customer
     * @return
     */
    private CustomerDTO mapCustomerToCustomerDTO(Customer customer) {
        ModelMapper mapper = new ModelMapper();
        CustomerDTO customerDTO = mapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    /**
     * Transforme un POJO CustomerDTO en en entity Customer
     *
     * @param customerDTO
     * @return
     */
    private Customer mapCustomerDTOToCustomer(CustomerDTO customerDTO) {
        ModelMapper mapper = new ModelMapper();
        Customer customer = mapper.map(customerDTO, Customer.class);
        return customer;
    }

}
