package com.trav.voy.ville;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;

//@Repository
@RepositoryRestResource
@Transactional
public interface VilleRepository extends JpaRepository<Ville, Integer> {

    public List<Ville>findByOrderByNameAsc();
    
    public Ville findVilleByNameIgnoreCase(String name);

    @Query("SELECT v FROM Ville v WHERE lower(v.name) Like %:name% order by v.name")
    @RestResource(path = "/villelike")
    public List<Ville> findAllByNameLikes(@Param("name") String name);
    
    public List<Ville> findAllByNameLike(@Param("name") String name);

//    @Query("select k from Klausur k where k.id=:x")
//    public Klausur findOne(@Param("x") long id);
//    @Query("SELECT b "
//            + "FROM Book b "
//            + "INNER JOIN b.category cat "
//            + "WHERE cat.code = :code"
//    )
}
