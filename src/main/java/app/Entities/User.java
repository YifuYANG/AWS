package app.Entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private Integer type;

    @Column
    private String name;

    @Column
    private Integer sex;

    @Column
    private String phone;

    @Column
    private String Email;

    @Column
    private Timestamp createdAt;

    @Column
    private Timestamp updatedAt;


}
