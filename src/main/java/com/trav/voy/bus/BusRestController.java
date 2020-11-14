package com.trav.voy.bus;

import com.trav.voy.agency.AgencyBus;
import com.trav.voy.agency.AgencyBusRepository;
import com.trav.voy.place.IPlaceService;
import com.trav.voy.place.Place;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BusRestController.class);

    @Autowired
    private IBusService busService;
    @Autowired
    private IPlaceService placeService;
    @Autowired
    AgencyBusRepository agencyBusRepository;

    @GetMapping("/allBuss")
    public ResponseEntity<List<BusDTO>> getAllBookBuss() {
        List<Bus> buses = (List<Bus>) busService.getBussList();
        if (!CollectionUtils.isEmpty(busService.getBussList())) {
            List<BusDTO> busDTOs = buses.stream().map(bus -> {
                return mapBusToBusDTO(bus);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(busDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/allBussByAgency")
    public ResponseEntity<List<BusDTO>> getAllBussByAgency(@RequestParam("agencyName") String agencyName) {
        List<Bus> buses = new ArrayList<>();
        List<AgencyBus> agencyBuses = (List<AgencyBus>) busService.findAllAgencyBusByAgencyName(agencyName);
        for (AgencyBus a : agencyBuses) {
            buses.add(a.getBus());
        }
        if (!CollectionUtils.isEmpty(busService.findAllAgencyBusByAgencyName(agencyName))) {
            List<BusDTO> busDTOs = buses.stream().map(bus -> {
                return mapBusToBusDTO(bus);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(busDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param busDTORequest
     * @return
     */
    @PostMapping("/addBus")
    public ResponseEntity<?> createNewBus(@RequestBody BusDTO busDTORequest) {
        Bus busRequest = mapBusDTOToBus(busDTORequest);
        if (busRequest.getPhoto() != null && !busRequest.getPhoto().equals("")) {
            String photoName[] = busRequest.getPhoto().split("\\\\");
            busRequest.setPhoto(photoName[photoName.length - 1]);
        }

        Bus busResponse = null;
        try {
            busResponse = busService.saveBus(busRequest);
            agencyBusRepository.save(new AgencyBus(null, busDTORequest.getAgency(), busRequest));
        } catch (Exception e) {
            return new ResponseEntity<>("Impossible d'ajouter ce bus, immatriculation existante ! "+e.getMessage(), HttpStatus.CONFLICT);
        }

        for (int j = 0; j < busResponse.getNbrePlace(); j++) {
            placeService.savePlace(new Place(null, j + 1, busRequest, null));
        }

        if (busResponse != null) {
            BusDTO busDTO = mapBusToBusDTO(busResponse);
            return new ResponseEntity<>(busDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param customerDTORequestbusDTORequest@return
     */
    @PutMapping("/updateBus")
    public ResponseEntity<BusDTO> updateBus(@RequestBody BusDTO busDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        if (!busService.checkIfIdexists(busDTORequest.getId())) {
            return new ResponseEntity<BusDTO>(HttpStatus.NOT_FOUND);
        }
        Bus busRequest = mapBusDTOToBus(busDTORequest);
        Bus busResponse = busService.updateBus(busRequest);
        if (busResponse != null) {
            BusDTO busDTO = mapBusToBusDTO(busResponse);
            return new ResponseEntity<BusDTO>(busDTO, HttpStatus.OK);
        }
        return new ResponseEntity<BusDTO>(HttpStatus.NOT_MODIFIED);
    }

//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param customerId
//	 * @return
//	 */
    @DeleteMapping("/deleteBus/{customerId}")
//    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
//    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
    public ResponseEntity<String> deleteBus(@PathVariable Integer busId) {
        busService.deleteBus(busId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }
//

    @GetMapping("/searchByEmail")
//    @ApiOperation(value = "Search a customer in the Library by its email", response = BusDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<BusDTO> searchBusByEmail(@RequestParam("email") String email) {
        //, UriComponentsBuilder uriComponentBuilder
//        Bus customer = customerService.findBusByEmail(email);
//        if (customer != null) {
//            BusDTO customerDTO = mapBusToBusDTO(customer);
//            return new ResponseEntity<BusDTO>(customerDTO, HttpStatus.OK);
//        }
        return new ResponseEntity<BusDTO>(HttpStatus.NO_CONTENT);
    }
//	
//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */

    @GetMapping("/searchByLastName")
//    @ApiOperation(value = "Search a customer in the Library by its Last name", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<BusDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
        //,	UriComponentsBuilder uriComponentBuilder
//        List<Bus> customers = customerService.findBusByLastName(lastName);
//        if (customers != null && !CollectionUtils.isEmpty(customers)) {
//            List<BusDTO> customerDTOs = customers.stream().map(customer -> {
//                return mapBusToBusDTO(customer);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<BusDTO>>(customerDTOs, HttpStatus.OK);
//        }
        return new ResponseEntity<List<BusDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Transforme un entity Bus en un POJO BusDTO
     *
     * @param customer
     * @return
     */
    private BusDTO mapBusToBusDTO(Bus customer) {
        ModelMapper mapper = new ModelMapper();
        BusDTO customerDTO = mapper.map(customer, BusDTO.class);
        return customerDTO;
    }

    /**
     * Transforme un POJO BusDTO en en entity Bus
     *
     * @param customerDTO
     * @return
     */
    private Bus mapBusDTOToBus(BusDTO busDTO) {
        ModelMapper mapper = new ModelMapper();
        Bus bus = mapper.map(busDTO, Bus.class);
        return bus;
    }

}
