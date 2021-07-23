package app.Entities;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private Integer ownerid;

    @Column
    private String description;

    @Column
    private Integer likes;

    @Column
    private Timestamp createdAt;

    @Column
    private String imageurl;
}
