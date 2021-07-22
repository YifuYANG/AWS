package app.Entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Likes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private Integer userid;

    @Column
    private Integer likedphotoid;

}
