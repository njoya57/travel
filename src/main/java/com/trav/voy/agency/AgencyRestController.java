package com.trav.voy.agency;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trav.voy.ville.Ville;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
public class AgencyRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AgencyRestController.class);

    @Autowired
    private AgencyServiceImpl agencyService;

    @GetMapping("/allAgencys")
    public ResponseEntity<List<AgencyDTO>> getAllAgencys() {
        List<Agency> agencys = agencyService.getAgencysList();
//        if (!CollectionUtils.isEmpty(agencys.toList())) {
        if (!agencys.isEmpty()) {
            List<AgencyDTO> custumerDTOs = agencys.stream().map(custumer -> {
                return mapAgencyToAgencyDTO(custumer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<AgencyDTO>>(custumerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<AgencyDTO>>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/allAgencyByVille")
    public ResponseEntity<List<AgencyDTO>> getAllAgencyByVille(@RequestParam("villeID") String villeID) {
        int idVille = 0;
        if (villeID != null) {
            idVille = Integer.parseInt(villeID);
        }
        List<Agency> agencys = (List<Agency>) agencyService.findAllAgencyByVilleId(idVille);
        if (!CollectionUtils.isEmpty(agencyService.findAllAgencyByVilleId(idVille))) {
            List<AgencyDTO> agencyDTOs = agencys.stream().map(agency -> {
                return mapAgencyToAgencyDTO(agency);
            }).collect(Collectors.toList());
            return new ResponseEntity<>(agencyDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Ajoute un nouveau client dans la base de donnée H2.Si le client existe
 déjà, on retourne un code indiquant que la création n'a pas abouti.
     *
     * @param agency
     * @param file
     * @return
     * @throws java.io.IOException
     */
    @PostMapping("/addAgency")
    public ResponseEntity<AgencyDTO> createNewAgency(@RequestParam("agence") String agency, 
            @RequestParam(value = "data",
            required = false) MultipartFile file) throws IOException {
//        System.out.println("yyyyyyyyyy "+agency);
//        System.out.println("nnnnnnnnnn "+file);
        AgencyDTO agencyDTORequest = new ObjectMapper().readValue(agency, AgencyDTO.class);
        Agency existingAgency = agencyService.findAgencyByName(agencyDTORequest.getName());
        if (existingAgency != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Agency agencyRequest = mapAgencyDTOToAgency(agencyDTORequest);
        if (file != null || !file.isEmpty()) {
            agencyRequest.setPhoto(file.getBytes());
        }
        
        agencyRequest.setCreationDate(LocalDate.now());
        Agency customerResponse = agencyService.saveAgency(agencyRequest);
        if (customerResponse != null) {
            AgencyDTO customerDTO = mapAgencyToAgencyDTO(customerResponse);
            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2.Si le
 client n'est pas retrouvé, on retourne un code indiquant que la mise à
 jour n'a pas abouti.
     *
     * @param agency
     * @param file
     * @return
     * @throws com.fasterxml.jackson.core.JsonProcessingException
     */
    @PutMapping("/updateAgency")
    public ResponseEntity<AgencyDTO> updateAgency(@RequestParam("agence") String agency, 
            @RequestParam(value = "data",
            required = false) MultipartFile file) throws JsonProcessingException, IOException {
//        System.out.println("xxxxxxxxxxxxxxxxxxxxxxx");
//        System.out.println(agency);
        
        AgencyDTO agencyDTORequest = new ObjectMapper().readValue(agency, AgencyDTO.class);
        if (!agencyService.checkIfIdexists(agencyDTORequest.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        System.out.println(agencyDTORequest.getName());
//        System.out.println(Arrays.toString(agencyDTORequest.getPhoto()));
        Agency agencyRequest = mapAgencyDTOToAgency(agencyDTORequest);
        if (file != null || !file.isEmpty()) {
            agencyRequest.setPhoto(file.getBytes());
        }
        Agency agencyResponse = agencyService.updateAgency(agencyRequest);
        if (agencyResponse != null) {
            AgencyDTO agencyDTO = mapAgencyToAgencyDTO(agencyResponse);
            return new ResponseEntity<>(agencyDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FileInformation> uploadFile(
            @RequestParam(name = "data") MultipartFile multipartFile
    ) throws Exception {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new Exception();
        }
//        System.out.println(multipartFile.getOriginalFilename());
//        System.out.println(Arrays.toString(multipartFile.getInputStream().readAllBytes()));
//        System.out.println(multipartFile.getBytes());
//        System.out.println(multipartFile);
        return new ResponseEntity<>(new FileInformation(multipartFile.getOriginalFilename(), multipartFile.getSize()), HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteAgency/{agencyId}")
    public ResponseEntity<String> deleteAgency(@PathVariable Integer agencyId) {
        agencyService.deleteAgency(agencyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//

    @GetMapping("/paginatedSearch")
    public ResponseEntity<List<AgencyDTO>> searchAgencys(@RequestParam("beginPage") int beginPage,
            @RequestParam("endPage") int endPage) {
        //, UriComponentsBuilder uriComponentBuilder
        Page<Agency> customers = agencyService.getPaginatedAgencysList(beginPage, endPage);
        if (customers != null) {
            List<AgencyDTO> customerDTOs = customers.stream().map(customer -> {
                return mapAgencyToAgencyDTO(customer);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<AgencyDTO>>(customerDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<AgencyDTO>>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/saveAgencyVilleLignes")
    public ResponseEntity<Ville> saveAgencyVilleLignes(@RequestBody ItineraireForm itineraireForm) {
//        System.out.println("HHHHHHHHHHHHH " + itineraireForm);
        if (itineraireForm != null) {
            try {
//                List<Agency>agencys=(List<Agency>) agencyService.findAgencyByName(itineraireForm.getAgency().getName());
                itineraireForm.getVilleNames().forEach(name -> {
                    agencyService.saveAgencyVilleLignes(new AgencyVilleLigne(null, name, itineraireForm.getAgency()));
                });
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @GetMapping("/itineraires")
    public ResponseEntity<List<AgencyVilleLigne>> itineraires(@RequestParam("agencyId") String agencyId) {
        System.out.println("MMMMMMMMMMMMMMMMMMMMM " + agencyId);
        int id = 0;
        if (agencyId != null) {
            id = Integer.parseInt(agencyId);
            List<AgencyVilleLigne> agencyVilleLignes = (List<AgencyVilleLigne>) agencyService.findItineraireAgence(id);
//            System.out.println("QQQQQQQQQQQQQQQQQQQQQ "+agencyVilleLignes);
            if (!CollectionUtils.isEmpty(agencyVilleLignes)) {
                agencyVilleLignes.removeAll(Collections.singleton(null));
                return new ResponseEntity<>(agencyVilleLignes, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private AgencyDTO mapAgencyToAgencyDTO(Agency customer) {
        ModelMapper mapper = new ModelMapper();
        AgencyDTO agencyDTO = mapper.map(customer, AgencyDTO.class);
        return agencyDTO;
    }

    /**
     * Transforme un POJO AgencyDTO en en entity Agency
     *
     * @param agencyDTO
     * @return
     */
    private Agency mapAgencyDTOToAgency(AgencyDTO agencyDTO) {
        ModelMapper mapper = new ModelMapper();
        Agency agency = mapper.map(agencyDTO, Agency.class);
        return agency;
    }

    @GetMapping(value = "/images/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "name") String name) throws IOException {
//        Bus bus = busDao.findById(id).get();
//        String photoName = bus.getPhoto();
        File file = new File(System.getProperty("user.home") + "/travel/images/" + name);
        Path path = Paths.get(file.toURI());
//        return Files.readAllBytes(path);
return null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class FileInformation {

        String originalFilename;
        long size;

    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ItineraireForm {

    private List<String> villeNames = new ArrayList<>();
    private Agency agency;
}
