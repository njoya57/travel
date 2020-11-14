package com.trav.voy.agency;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("agencyService")
@Transactional
public class AgencyServiceImpl implements IAgencyService {

    @Autowired
    private IAgencyDao agencyDao;
    @Autowired
    private AgencyVilleLigneRepository agencyVilleLigneRepository;

    @Override
    public Agency saveAgency(Agency agency) {
        return agencyDao.save(agency);
    }

    @Override
    public Agency updateAgency(Agency agency) {
        return agencyDao.save(agency);
    }

    @Override
    public void deleteAgency(Integer agencyId) {
        agencyDao.deleteById(agencyId);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return agencyDao.existsById(id);
    }

    @Override
    public Agency findAgencyById(Integer agencyId) {
        return agencyDao.getOne(agencyId);
    }

    @Override
    public Page<Agency> getPaginatedAgencysList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return agencyDao.findAll(page);
    }

    @Override
    public List<Agency> getAgencysList() {
        return agencyDao.findAll();
    }
    
    @Override
    public Agency findAgencyByName(String name) {
        return agencyDao.findAgencyByNameIgnoreCase(name);
    }

    @Override
    public Collection<Agency> findAllAgencyByVilleId(int villeID) {
        return agencyDao.findAllAgencyByVilleId(villeID);
    }
    
    @Override
     public void saveAgencyVilleLignes(AgencyVilleLigne agencyVilleLigne){
         agencyVilleLigneRepository.save(agencyVilleLigne);
     }

     @Override
     public Collection<AgencyVilleLigne> findItineraireAgence(int idAgence){
         return agencyVilleLigneRepository.allAgencyVilleLigneByAgencyId(idAgence);
     }
}
