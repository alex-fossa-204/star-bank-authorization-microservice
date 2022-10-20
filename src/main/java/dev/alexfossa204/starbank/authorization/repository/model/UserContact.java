package dev.alexfossa204.starbank.authorization.repository.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data

@Entity
@Table(name = "api_user_contact")
public class UserContact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "email")
    private String email;

    @Column(name = "skype")
    private String skype;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
