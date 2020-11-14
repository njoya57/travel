package com.trav.voy.seance;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("seanceService")
@Transactional
public class SeanceServiceImpl implements ISeanceService {

    @Autowired
    private SeanceRepository seanceDao;

    @Override
    public Seance saveSeance(Seance seance) {
        return seanceDao.save(seance);
    }

    @Override
    public Collection<Seance> findAllSeances() {
        return seanceDao.findAll();
    }

}
