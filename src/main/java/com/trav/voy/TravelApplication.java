package com.trav.voy;

import com.trav.voy.agency.Agency;
import com.trav.voy.agency.AgencyBus;
import com.trav.voy.agency.AgencyBusRepository;
import com.trav.voy.agency.AgencyVilleLigne;
import com.trav.voy.agency.IAgencyDao;
import com.trav.voy.agency.IAgencyService;
import com.trav.voy.bus.Bus;
import com.trav.voy.bus.IBusDao;
import com.trav.voy.bus.IBusService;
import com.trav.voy.customer.ICustomerService;
import com.trav.voy.init.ITravelInitService;
import com.trav.voy.place.IPlaceService;
import com.trav.voy.projectiontravel.IProjectionTravelRepository;
import com.trav.voy.projectiontravel.ProjectionTravel;
import com.trav.voy.security.dao.TaskRepository;
import com.trav.voy.security.entities.Roles;
import com.trav.voy.security.entities.Task;
import com.trav.voy.security.entities.Users;
import com.trav.voy.security.service.AccountService;
import com.trav.voy.ticket.Ticket;
import com.trav.voy.travel.ITravelService;
import com.trav.voy.travel.Travel;
import com.trav.voy.ville.IVilleService;
import com.trav.voy.ville.Ville;
import com.trav.voy.ville.VilleDTO;
import com.trav.voy.ville.VilleServiceImpl;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class TravelApplication implements CommandLineRunner {

    private static Logger log = LogManager.getLogger(TravelApplication.class);
    @Autowired
    ITravelInitService cinemaInitService;
    @Autowired
    IVilleService villeService;
    @Autowired
    IAgencyService agencyService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IBusService busService;
    @Autowired
    ITravelService travelService;
    @Autowired
    IPlaceService placeService;
    @Autowired
    private IProjectionTravelRepository iProjectionTravelRepository;
    @Autowired
    private RepositoryRestConfiguration restConfiguration;
    @Autowired
    AccountService accountService;
//    @Autowired
//    UsersRepository appUserRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    IAgencyDao agencyDao;
    @Autowired
    IBusDao busDao;
    @Autowired
    AgencyBusRepository agencyBusRepository;

    public static void main(String[] args) {
        log.info("JAR IS STARTING...");
        SpringApplication.run(TravelApplication.class, args);
        log.info("JAR IS END START...");
    }

    @Override
    public void run(String... args) throws Exception {
        restConfiguration.exposeIdsFor(Travel.class, Ticket.class, Bus.class, Ville.class, Agency.class);

//        List<ProjectionTravel> list = (List<ProjectionTravel>) iProjectionTravelRepository.findAllProjectionTravelByDateProjectionTravel(LocalDate.now());
//        System.out.println("XXXXXXXXXXXXXXX  " + list.size());
//        for (ProjectionTravel p : list) {
//            System.out.println("XXXXXXXXXXXXXXX  " + p.getDateProjectionTravel());
//        }
        List<Ville> villes = villeService.getAllVilles();
        if (villes.isEmpty() || villes == null) {
            cinemaInitService.initVille();
            cinemaInitService.initAgency();
        }
        //        cinemaInitService.initBus();
//        cinemaInitService.initPlace();
//        cinemaInitService.initSeance();
//        cinemaInitService.initTravel();
//        cinemaInitService.initProjection();
//        cinemaInitService.initTicket();
//        Bus bus1 = new Bus(null, "LT752", "", 2, "", null, null);
//        bus1= busDao.save(bus1);
//        Bus bus1 = busDao.findById(179).get();
//        Optional<Agency> agency = agencyDao.findById(2);
//        Agency a = agency.get();
//         a.getBuses().add(bus1);
//         agencyDao.save(a);
//         a.addBus(bus1);
//        agencyBusRepository.save(new AgencyBus(null, a, bus1));
       
    }

    @Autowired
    VilleServiceImpl villeServiceImpl;

   

//    @Bean
//    public FilterRegistrationBean simpleCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        // *** URL below needs to match the Vue client URL and port ***
//        config.setAllowedOrigins(Collections.singletonList("*"));
//        config.setAllowedMethods(Collections.singletonList("*"));
//        config.setAllowedHeaders(Collections.singletonList("*"));
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//        return bean;
//    }
    
     @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    
    @Bean
    public void account() {
//        taskRepository.save(new Task(null, "Marchand"));
//        taskRepository.save(new Task(null, "commerçant"));
//        taskRepository.save(new Task(null, "autres"));
        Roles roles = accountService.findRole("ADMIN");
        if (roles == null) {
            accountService.saveRole(new Roles(null, "USER"));
            accountService.saveRole(new Roles(null, "ADMIN"));

            accountService.saveUser(new Users("user", "12345"));
            accountService.saveUser(new Users("admin", "p@ssenger2018"));

            accountService.addRoleToUser("user", "USER");
            accountService.addRoleToUser("admin", "USER");
            accountService.addRoleToUser("admin", "ADMIN");
        }

    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
