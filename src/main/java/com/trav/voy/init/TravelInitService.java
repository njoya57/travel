/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trav.voy.init;

import com.trav.voy.agency.Agency;
import com.trav.voy.agency.IAgencyDao;
import com.trav.voy.bus.Bus;
import com.trav.voy.bus.IBusDao;
import com.trav.voy.place.Place;
import com.trav.voy.place.PlaceRepository;
import com.trav.voy.projectiontravel.ProjectionTravel;
import com.trav.voy.projectiontravel.ProjectionTravelRepository;
import com.trav.voy.seance.Seance;
import com.trav.voy.seance.SeanceRepository;
import com.trav.voy.ticket.Ticket;
import com.trav.voy.ticket.TicketRepository;
import com.trav.voy.travel.CategoryTravel;
import com.trav.voy.travel.ITravelDao;
import com.trav.voy.travel.Travel;
import com.trav.voy.ville.Ville;
import com.trav.voy.ville.VilleRepository;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.io.IOUtils;
/**
 *
 * @author MEDION
 */
@Service
@Transactional
public class TravelInitService implements ITravelInitService {

    @Autowired
    IAgencyDao agencyRepository;
    @Autowired
    ITravelDao travelRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    ProjectionTravelRepository projectionRepository;
    @Autowired
    IBusDao busRepository;
    @Autowired
    SeanceRepository seanceRepository;
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    VilleRepository villeRepository;

    @Override
    public void initVille() {
        Stream.of("Douala", "Yaoundé", "Bafoussam", "Bamenda", "Ngaoundéré", "Garoua", "Ebolowa", "Edéa",
                "Kribi", "Limbé", "Foumban", "Foumbot", "Bouda").forEach(v -> {
                    Ville ville = new Ville();
                    ville.setName(v);
                    villeRepository.save(ville);
                });
    }

    private byte[] b(String photoName) {
        File file = new File(System.getProperty("user.home") + "/travel/images/" + photoName + ".jpg");
        Path path = Paths.get(file.toURI());
        try {
            return Files.readAllBytes(path);
        } catch (IOException ex) {
            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

 

    private byte[] getByteArrayFromFile(final File handledDocument) throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
// Ici c'est un objet Document, mais on peut y mettre un File (voir Javadoc pour FileInputStream).
        final InputStream in = new FileInputStream(handledDocument);
        final byte[] buffer = new byte[500];
        int read = -1;
        while ((read = in.read(buffer)) > 0) {
            baos.write(buffer, 0, read);
        }
        in.close();
// Retour un tableau de byte (byte[]).
        return baos.toByteArray();
    }

    @Override
    public void initAgency() {
        String agenDla[] = new String[]{"Buca voyage", "Princess Voyage", "Finexs express", "Touristique voyage", "Global express", "Butsis voyage", "Tresor voyage"};
        String agenYde[] = new String[]{"Garanti express", "Buca voyage", "Touristique voyage", "Global express"};
        String agenBsam[] = new String[]{"Menoua express", "Confort voyage", "Butsis voyage", "Tresor voyage"};
        String agenKbi[] = new String[]{"Kribienne express", "Global express"};
        String agenFban[] = new String[]{"Avenir du noun", "Butsis voyage", "Tresor voyage"};
        String photos[] = new String[]{"avenir.jpg", "buca.jpg", "finexs1.jpg", "general.jpg", "touristique.jpg"};
        villeRepository.findAll().forEach(ville -> {
            Agency agency = new Agency();
//            int k = new Random().nextInt(agen.length);

            if (ville.getName().equalsIgnoreCase("Douala")) {

                for (int i = 0; i < agenDla.length; i++) {
                    agency = new Agency();
                    agency.setVille(ville);
                    agency.setNbreBus((int) (1 + (Math.random() * 5)));
                    agency.setName(agenDla[i]);

                    if (agency.getName().startsWith("Buca")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\buca.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Ave")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\avenir.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Fine")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\finexs1.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Gener")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\general.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Touri")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\touristique.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
//                    Agency a = agencyRepository.findAgencyByNameIgnoreCase(agenDla[i]);
//                    if (a == null) {
                    agencyRepository.save(agency);
//                    }
                }
            }
            if (ville.getName().equalsIgnoreCase("Yaoundé")) {
                for (int i = 0; i < agenYde.length; i++) {
                    agency = new Agency();
                    agency.setVille(ville);
                    agency.setNbreBus((int) (1 + (Math.random() * 5)));
                    agency.setName(agenYde[i]);
                    if (agency.getName().startsWith("Buca")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\buca.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Ave")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\avenir.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Fine")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\finexs1.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Gener")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\general.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Touri")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\touristique.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
//                    Agency a = agencyRepository.findAgencyByNameIgnoreCase(agenYde[i]);
//                    if (a == null) {
                    agencyRepository.save(agency);
//                    }
                }
            }
            if (ville.getName().equalsIgnoreCase("Bafoussam")) {
                for (int i = 0; i < agenBsam.length; i++) {
                    agency = new Agency();
                    agency.setVille(ville);
                    agency.setNbreBus((int) (1 + (Math.random() * 5)));
                    agency.setName(agenBsam[i]);
                    if (agency.getName().startsWith("Buca")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\buca.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Ave")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\avenir.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Fine")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\finexs1.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Gener")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\general.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Touri")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\touristique.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
//                    Agency a = agencyRepository.findAgencyByNameIgnoreCase(agenBsam[i]);
//                    if (a == null) {
                    agencyRepository.save(agency);
//                    }
                }
            }
            if (ville.getName().equalsIgnoreCase("Foumban")) {
                for (int i = 0; i < agenFban.length; i++) {
                    agency = new Agency();
                    agency.setVille(ville);
                    agency.setNbreBus((int) (1 + (Math.random() * 5)));
                    agency.setName(agenFban[i]);
                    if (agency.getName().startsWith("Buca")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\buca.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Ave")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\avenir.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
  
                            agency.setPhoto(IOUtils.toByteArray(new FileInputStream(monImage)));
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Fine")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\finexs1.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Gener")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\general.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Touri")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\touristique.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
//                    Agency a = agencyRepository.findAgencyByNameIgnoreCase(agenFban[i]);
//                    if (a == null) {
                    agencyRepository.save(agency);
//                    }
                }
            }
            if (ville.getName().equalsIgnoreCase("Kribi")) {
                for (int i = 0; i < agenKbi.length; i++) {
                    agency = new Agency();
                    agency.setVille(ville);
                    agency.setNbreBus((int) (1 + (Math.random() * 5)));
                    agency.setName(agenKbi[i]);
                    if (agency.getName().startsWith("Buca")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\buca.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Ave")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\avenir.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Fine")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\finexs1.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Gener")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\general.jpg");
                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    if (agency.getName().startsWith("Touri")) {
                        File monImage = new File("C:\\Users\\Latitude\\travel\\images\\touristique.jpg");

                        FileInputStream istreamImage = null;
                        try {
                            istreamImage = new FileInputStream(monImage);
//                            agency.setPhoto(istreamImage.readAllBytes());
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
//                    Agency a = agencyRepository.findAgencyByNameIgnoreCase(agenKbi[i]);
//                    if (a == null) {
                    agencyRepository.save(agency);
//                    }
                }
            }

        });
    }

    int j = 0;

    @Override
    public void initBus() {
        String photos[] = new String[]{"bus1.png", "bus6.png", "bus7.png", "bus8.png", "bus9.png", "bus5.jpg"};
        String immat[] = new String[]{"K", "P", "Y", "D", "A", "C", "N", "B", "X", "Q"};
        agencyRepository.findAll().forEach(ag -> {
            for (int i = 0; i < ag.getNbreBus(); i++) {
                Bus bus = new Bus();
                bus.setImmatricule("LT 02" + j + " " + immat[new Random().nextInt(immat.length)] + "M");
////                bus.setAgency(ag);
                bus.setNbrePlace((int) (4 + (Math.random() * 10)));
                bus.setPhoto(photos[new Random().nextInt(photos.length)]);
                busRepository.save(bus);
                j++;
            }
        });
    }

    @Override
    public void initPlace() {
        busRepository.findAll().forEach(bus -> {
            for (int i = 0; i < bus.getNbrePlace(); i++) {
                Place place = new Place();
                place.setNumero(i + 1);
                place.setBus(bus);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeance() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "14:30", "16:00", "18:00", "19:45").forEach(s -> {
            Seance seance = new Seance();
//            try {
////                seance.setHeureDepart(dateFormat.parse(s));
//                seanceRepository.save(seance);
//            } catch (ParseException ex) {
//                Logger.getLogger(TravelInitService.class.getName()).log(Level.SEVERE, null, ex);
//            }
        });
    }

//    @Override
//    public void initCategorie() {
//        Stream.of("Horeur", "Action", "Fiction", "Amour", "Kung Fu").forEach(cat -> {
//            Categorie categorie = new Categorie();
//            categorie.setName(cat);
//            categorieRepository.save(categorie);
//        });
//    }
    @Override
    public void initTravel() {
        int[] num = new int[]{1, 2, 3, 4, 5};
//        List<Categorie> categories = categorieRepository.findAll();
        Stream.of(1, 2, 3, 4, 5).forEach(f -> {
            Travel travel = new Travel();
            travel.setNumero(f);
//            travel.setHeureDepart(LocalDate.now());
//            travel.setHeureDepart(new Date());
//                    travel.setHeureDepart(duree[new Random().nextInt(duree.length)]);
//            travel.setTravelDate(LocalDate.now());
            travel.setCategoryTravel(CategoryTravel.CLASSIQUE);
            travelRepository.save(travel);
        });
    }

    @Override
    public void initProjection() {
        double[] prices = new double[]{1500, 1000, 2000, 3000, 500};
        List<Travel> travels = travelRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getAgencys().forEach(agence -> {
//                agence.getBuses().forEach(bus -> {
//                    int index = new Random().nextInt(travels.size());
//                    Travel travel = travels.get(index);
//                    seanceRepository.findAll().forEach(seance -> {
//                        ProjectionTravel projectionTravel = new ProjectionTravel();
//                        projectionTravel.setDateProjectionTravel(LocalDate.now());
//                        projectionTravel.getPk().setTravel(travel);
//                        System.out.println("PRIX TICKET " + new Random().nextInt(prices.length));
//                        projectionTravel.setPrix(prices[new Random().nextInt(prices.length)]);
//                        projectionTravel.getPk().setBus(bus);
//                        projectionRepository.save(projectionTravel);
//                    });
//                });
            });
        });
    }

    @Override
    public void initTicket() {
        projectionRepository.findAll().forEach(projection -> {
            projection.getPk().getBus().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrix(projection.getPrix());
                ticket.setProjectionTravel(projection);
//                ticket.setTicketDate(LocalDateTime.now());
                ticket.setReserve(false);
                ticketRepository.save(ticket);

            });
        });
    }

}
