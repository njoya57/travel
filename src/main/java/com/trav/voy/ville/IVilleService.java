package com.trav.voy.ville;

import java.util.List;

import org.springframework.data.domain.Page;

public interface IVilleService {

    public Ville saveVille(Ville ville);

    public Ville updateVille(Ville ville);

    public void deleteVille(Integer villeId);

    public boolean checkIfIdexists(Integer id);

    public Ville findVilleByName(String name);
    
    public List<Ville> findVilleByNameLike(String name);
    
    public List<Ville> findAllVilleByName(String name);

    public Ville findVilleById(Integer villeId);

    public Page<Ville> getPaginatedVillesList(int begin, int end);

    public List<Ville> getAllVilles();
}
