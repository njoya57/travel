package com.trav.voy.seance;

import java.util.Collection;

public interface ISeanceService {

    public Seance saveSeance(Seance seance);

    public Collection<Seance> findAllSeances();

}
