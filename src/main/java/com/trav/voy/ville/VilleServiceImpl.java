package com.trav.voy.ville;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("villeService")
@Transactional
public class VilleServiceImpl implements IVilleService {

    @Autowired
    private VilleRepository villeDao;

    @Override
    public Ville saveVille(Ville agency) {
        return villeDao.save(agency);
    }

    @Override
    public Ville updateVille(Ville agency) {
        return villeDao.save(agency);
    }

    @Override
    public void deleteVille(Integer id) {
        villeDao.deleteById(id);
    }

    @Override
    public boolean checkIfIdexists(Integer id) {
        return villeDao.existsById(id);
    }

    @Override
    public Ville findVilleById(Integer id) {
        return villeDao.getOne(id);
    }

    @Override
    public Page<Ville> getPaginatedVillesList(int begin, int end) {
        Pageable page = PageRequest.of(begin, end);
        return villeDao.findAll(page);
    }

    @Override
    public Ville findVilleByName(String name) {
        return villeDao.findVilleByNameIgnoreCase(name);
    }

    @Override
    public List<Ville> findVilleByNameLike(String name) {
        return villeDao.findAllByNameLikes(name);
    }
    
    @Override
    public List<Ville> findAllVilleByName(String name) {
        return villeDao.findAllByNameLike("%" + name + "%");
    }

    @Override
    public List<Ville> getAllVilles() {
        return villeDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

}
