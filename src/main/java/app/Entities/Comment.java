package app.Entities;

import lombok.Data;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String comment;

    @Column
    private Integer publisherid;

    @Column
    private Integer photoid;

    @Column
    private Timestamp createdAt;
}
