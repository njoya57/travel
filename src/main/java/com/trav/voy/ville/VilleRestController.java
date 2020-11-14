package com.trav.voy.ville;

import com.trav.voy.agency.AgencyServiceImpl;
import com.trav.voy.agency.AgencyVilleLigne;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RestController
//@RequestMapping("/travel/rest/agency/api")
//@Api(value = "Ville Rest Controller: contains all operations for managing Ville")
public class VilleRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(VilleRestController.class);

    @Autowired
    private VilleServiceImpl villeServiceImpl;
    @Autowired
    private AgencyServiceImpl agencyServiceImpl;

    @GetMapping("/allVilles")
    public ResponseEntity<List<?>> getAllAgencyVilles() {
        List<Ville> villes = villeServiceImpl.getAllVilles();
        if (!CollectionUtils.isEmpty(villes)) {
            //on retire tous les élts null que peut contenir cette liste
            villes.removeAll(Collections.singleton(null));
            List<VilleDTO> villeDTOs = villes.stream().map(ville -> {
                return mapVilleToVilleDTO(ville);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(villeDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/select_villes")
    public ResponseEntity<List<?>> ville_itineraire(@RequestParam String id) {
        int ids = Integer.valueOf(id);
        List<AgencyVilleLigne> agencyVilleLignes = (List<AgencyVilleLigne>) agencyServiceImpl.findItineraireAgence(ids);
        List<Ville> villes = villeServiceImpl.getAllVilles();

        List<Ville> villes1 = new ArrayList<>();

        if (agencyVilleLignes.isEmpty() || agencyVilleLignes == null) {
            return new ResponseEntity<>(villes, HttpStatus.OK);
        }
        for (int i = 0; i < villes.size(); i++) {
            boolean val = exist(agencyVilleLignes, villes.get(i).getName());
            if (!val) {
                villes1.add(villes.get(i));
            }

        }
        return new ResponseEntity<>(villes1, HttpStatus.OK);
    }

    private boolean exist(List<AgencyVilleLigne> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    @GetMapping("/villename")
    public ResponseEntity<List<?>> getVilleName(@RequestParam String name) {
        List<Ville> villes = villeServiceImpl.findAllVilleByName(name);
        if (!CollectionUtils.isEmpty(villes)) {
            //on retire tous les élts null que peut contenir cette liste
            villes.removeAll(Collections.singleton(null));
            List<VilleDTO> villeDTOs = villes.stream().map(ville -> {
                return mapVilleToVilleDTO(ville);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(villeDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe
     * déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param agencyDTORequest
     * @return
     */
    @PostMapping("/addVille")
//    @ApiOperation(value = "Add a new Ville in the Library", response = VilleDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 409, message = "Conflict: the customer already exist"),
//        @ApiResponse(code = 201, message = "Created: the customer is successfully inserted"),
//        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted")})
    public ResponseEntity<VilleDTO> createNewVille(@RequestBody VilleDTO agencyDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        Ville existingVille = villeServiceImpl.findVilleByName(agencyDTORequest.getName());
        if (existingVille != null) {
            return new ResponseEntity<VilleDTO>(HttpStatus.CONFLICT);
        }
        Ville villeRequest = mapVilleDTOToVille(agencyDTORequest);
        Ville villeResponse = villeServiceImpl.saveVille(villeRequest);
        if (villeResponse != null) {
            VilleDTO villeDTO = mapVilleToVilleDTO(villeResponse);
            return new ResponseEntity<VilleDTO>(villeDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<VilleDTO>(HttpStatus.NOT_MODIFIED);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le
     * client n'est pas retrouvé, on retourne un code indiquant que la mise à
     * jour n'a pas abouti.
     *
     * @param villeDTORequest
     * @return
     */
    @PutMapping("/updateVille")
//    @ApiOperation(value = "Update/Modify an existing customer in the Library", response = VilleDTO.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 404, message = "Not Found : the customer does not exist"),
//        @ApiResponse(code = 200, message = "Ok: the customer is successfully updated"),
//        @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully updated")})
    public ResponseEntity<VilleDTO> updateVille(@RequestBody VilleDTO villeDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        if (!villeServiceImpl.checkIfIdexists(villeDTORequest.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Ville villeRequest = mapVilleDTOToVille(villeDTORequest);
        Ville villeResponse = villeServiceImpl.updateVille(villeRequest);
        if (villeResponse != null) {
            VilleDTO villeDTO = mapVilleToVilleDTO(villeResponse);
            return new ResponseEntity<VilleDTO>(villeDTO, HttpStatus.OK);
        }
        return new ResponseEntity<VilleDTO>(HttpStatus.NOT_MODIFIED);
    }

//	/**
//	 * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
//	 * @param customerId
//	 * @return
//	 */
    @DeleteMapping("/deleteVille/{customerId}")
//    @ApiOperation(value = "Delete a customer in the Library, if the customer does not exist, nothing is done", response = String.class)
//    @ApiResponse(code = 204, message = "No Content: customer sucessfully deleted")
    public ResponseEntity<String> deleteVille(@PathVariable Integer villeId) {
        villeServiceImpl.deleteVille(villeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//	/**
//	 * Retourne la liste des clients ayant le nom passé en paramètre.
//	 * @param lastName
//	 * @return
//	 */
    @GetMapping("/searchVilleByName")
//    @ApiOperation(value = "Search a ville ", response = List.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "Ok: successfull research"),
//        @ApiResponse(code = 204, message = "No Content: no result founded"),})
    public ResponseEntity<List<VilleDTO>> searchVilleByName(@RequestParam("name") String name) {
        //,	UriComponentsBuilder uriComponentBuilder
        List<Ville> villes = villeServiceImpl.findAllVilleByName(name);
        System.out.println("vvvvvvvvvvvvvvvv " + name);
        System.out.println("XXXXXXXXXXXXXXXXX " + villes.toString());
        if (villes != null && !CollectionUtils.isEmpty(villes)) {
            List<VilleDTO> villeDTOs = villes.stream().map(ville -> {
                return mapVilleToVilleDTO(ville);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<VilleDTO>>(villeDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<VilleDTO>>(HttpStatus.NO_CONTENT);
    }

    /**
     * Transforme un entity Ville en un POJO VilleDTO
     *
     * @param ville
     * @return
     */
    private VilleDTO mapVilleToVilleDTO(Ville ville) {
        ModelMapper mapper = new ModelMapper();
        VilleDTO villeDTO = mapper.map(ville, VilleDTO.class);
        return villeDTO;
    }

    /**
     * Transforme un POJO VilleDTO en en entity Ville
     *
     * @param villeDTO
     * @return
     */
    private Ville mapVilleDTOToVille(VilleDTO villeDTO) {
        ModelMapper mapper = new ModelMapper();
        Ville ville = mapper.map(villeDTO, Ville.class);
        return ville;
    }

}
