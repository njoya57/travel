package com.trav.voy.travel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RestController
public class TravelRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TravelRestController.class);

    @Autowired
    private TravelServiceImpl travelService;

    @GetMapping("/allTravels")
    public ResponseEntity<List<TravelDTO>> getAllTravels() {
        Collection<Travel> travels = travelService.findAllTravels();
        if (!CollectionUtils.isEmpty(travels)) {
//            on retire tous les élts null que peut contenir cette liste
            travels.removeAll(Collections.singleton(null));
            List<TravelDTO> travelDTOs = travels.stream().map(travel -> {
                return mapTravelToTravelDTO(travel);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<TravelDTO>>(travelDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<TravelDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param travelDTORequest
     * @return
     */
    @PostMapping("/addTravel")
    public ResponseEntity<TravelDTO> createNewTravel(@RequestBody TravelDTO travelDTORequest) {
//        System.out.println("PPP "+travelDTORequest.getHeureDepart());
        System.out.println("travelDTORequest.getCategoryTravel() "+travelDTORequest.getCategoryTravel().name());
//        String sdf=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(travelDTORequest.getHeureDepart());
//                System.out.println("PPPTTT "+sdf);
        Travel travelRequest = mapTravelDTOToTravel(travelDTORequest);
        travelRequest.setStatut(TravelStatus.En_cours);
        Travel travelResponse = travelService.saveTravel(travelRequest);
        if (travelResponse != null) {
            TravelDTO travelDTO = mapTravelToTravelDTO(travelResponse);
            System.out.println("travelDTO "+travelDTO.toString());
            return new ResponseEntity<>(travelDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param travelDTORequest
     * @return
     */
//    @PutMapping("/updateTravel")
////    @ApiOperation(value = "Update/Modify an existing travel in the Library", response = TravelDTO.class)
////    @ApiResponses(value = {
////        @ApiResponse(code = 404, message = "Not Found : the travel does not exist"),
////        @ApiResponse(code = 200, message = "Ok: the travel is successfully updated"),
////        @ApiResponse(code = 304, message = "Not Modified: the travel is unsuccessfully updated")})
//    public ResponseEntity<TravelDTO> updateTravel(@RequestBody TravelDTO travelDTORequest) {
//        //, UriComponentsBuilder uriComponentBuilder
//        if (!travelService.checkIfIdexists(travelDTORequest.getId())) {
//            return new ResponseEntity<TravelDTO>(HttpStatus.NOT_FOUND);
//        }
//        Travel travelRequest = mapTravelDTOToTravel(travelDTORequest);
//        Travel travelResponse = travelService.updateTravel(travelRequest);
//        if (travelResponse != null) {
//            TravelDTO travelDTO = mapTravelToTravelDTO(travelResponse);
//            return new ResponseEntity<TravelDTO>(travelDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<TravelDTO>(HttpStatus.NOT_MODIFIED);
//    }

//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param travelId
//	 * @return
//	 */
//    @DeleteMapping("/deleteTravel/{travelId}")
////    @ApiOperation(value = "Delete a travel in the Library, if the travel does not exist, nothing is done", response = String.class)
////    @ApiResponse(code = 204, message = "No Content: travel sucessfully deleted")
//    public ResponseEntity<String> deleteTravel(@PathVariable Integer travelId) {
//        travelService.deleteTravel(travelId);
//        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
//    }
//

//    @GetMapping("/paginatedSearch")
////    @ApiOperation(value = "List travels of the Library in a paginated way", response = List.class)
////    @ApiResponses(value = {
////        @ApiResponse(code = 200, message = "Ok: successfully listed"),
////        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<List<TravelDTO>> searchTravels(@RequestParam("beginPage") int beginPage,
//            @RequestParam("endPage") int endPage) {
//        //, UriComponentsBuilder uriComponentBuilder
//        Page<Travel> travels = travelService.getPaginatedTravelsList(beginPage, endPage);
//        if (travels != null) {
//            List<TravelDTO> travelDTOs = travels.stream().map(travel -> {
//                return mapTravelToTravelDTO(travel);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<TravelDTO>>(travelDTOs, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<TravelDTO>>(HttpStatus.NO_CONTENT);
//    }
//
//	/**
//	 * Retourne le client ayant l'adresse email passé en paramètre.
//	 * @param email
//	 * @return
//	 */

//    @GetMapping("/searchByEmail")
//    @ApiOperation(value = "Search a travel in the Library by its email", response = TravelDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<TravelDTO> searchTravelByEmail(@RequestParam("email") String email) {
//        //, UriComponentsBuilder uriComponentBuilder
//        Travel travel = travelService.findTravelByEmail(email);
//        if (travel != null) {
//            TravelDTO travelDTO = mapTravelToTravelDTO(travel);
//            return new ResponseEntity<TravelDTO>(travelDTO, HttpStatus.OK);
//        }
//        return new ResponseEntity<TravelDTO>(HttpStatus.NO_CONTENT);
//    }
//	
//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */
//    @GetMapping("/searchByLastName")
//    @ApiOperation(value = "Search a travel in the Library by its Last name", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
//    public ResponseEntity<List<TravelDTO>> searchBookByLastName(@RequestParam("lastName") String lastName) {
//        //,	UriComponentsBuilder uriComponentBuilder
//        List<Travel> travels = travelService.findTravelByLastName(lastName);
//        if (travels != null && !CollectionUtils.isEmpty(travels)) {
//            List<TravelDTO> travelDTOs = travels.stream().map(travel -> {
//                return mapTravelToTravelDTO(travel);
//            }).collect(Collectors.toList());
//            return new ResponseEntity<List<TravelDTO>>(travelDTOs, HttpStatus.OK);
//        }
//        return new ResponseEntity<List<TravelDTO>>(HttpStatus.NO_CONTENT);
//    }
    /**
     * Transforme un entity Travel en un POJO TravelDTO
     *
     * @param travel
     * @return
     */
    private TravelDTO mapTravelToTravelDTO(Travel travel) {
        ModelMapper mapper = new ModelMapper();
        TravelDTO travelDTO = mapper.map(travel, TravelDTO.class);
        return travelDTO;
    }

    /**
     * Transforme un POJO TravelDTO en en entity Travel
     *
     * @param travelDTO
     * @return
     */
    private Travel mapTravelDTOToTravel(TravelDTO travelDTO) {
        ModelMapper mapper = new ModelMapper();
        Travel travel = mapper.map(travelDTO, Travel.class);
        return travel;
    }

}
