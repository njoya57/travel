package com.trav.voy.place;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/customer/api")
//@Api(value = "Place Rest Controller: contains all operations for managing customers")
public class PlaceRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(PlaceRestController.class);

    @Autowired
    private PlaceServiceImpl customerService;

    @GetMapping("/allPlaces")
    public ResponseEntity<List<PlaceDTO>> getAllBookPlaces() {
        Page<Place> custumers = customerService.getPaginatedPlacesList(0, 10);
        if (!CollectionUtils.isEmpty(custumers.toList())) {
            //on retire tous les élts null que peut contenir cette liste
//            custumers.getContent().removeAll(Collections.singleton(null));
            List<PlaceDTO> custumerDTOs = custumers.stream().map(custumer -> {
                return mapPlaceToPlaceDTO(custumer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<PlaceDTO>>(custumerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<PlaceDTO>>(HttpStatus.NO_CONTENT);
    }

    private PlaceDTO mapPlaceToPlaceDTO(Place customer) {
        ModelMapper mapper = new ModelMapper();
        PlaceDTO customerDTO = mapper.map(customer, PlaceDTO.class);
        return customerDTO;
    }

    /**
     * Transforme un POJO PlaceDTO en en entity Place
     *
     * @param customerDTO
     * @return
     */
    private Place mapPlaceDTOToPlace(PlaceDTO customerDTO) {
        ModelMapper mapper = new ModelMapper();
        Place customer = mapper.map(customerDTO, Place.class);
        return customer;
    }

}
