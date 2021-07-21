package app.JpaRepository;

import app.Entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @Query("select p from Photo p where p.name = :name")
    PhotoRepository findByName(@Param("name") String name);

    @Query( "select p from Photo p where p.ownerid  in :ownerid" )
    List<Photo> findByOwnerId(@Param("ownerid") Integer ownerid);
}