package app.JpaRepository;

import app.Entities.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    @Query("select p from Photo p where p.name = :name")
    PhotoRepository findByName(@Param("name") String name);
}