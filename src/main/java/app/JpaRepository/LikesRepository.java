package app.JpaRepository;

import app.Entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    @Query( "select l from Likes l where l.userid  in :userid" )
    List<Likes> findByUserId(@Param("userid") Integer userid);

    @Query("select l from Likes l where l.userid = :userid")
    PhotoRepository findByUerId(@Param("userid") Integer userid);
}
