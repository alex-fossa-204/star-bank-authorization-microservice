package dev.alexfossa204.starbank.authorization.repository.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

@Entity
@Table(name = "verification_code")
public class VerificationCode implements Serializable {

    @Id
    @Column(name = "id")
    private UUID uuid;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "code")
    private String codeValue;

    @Column(name = "is_expired")
    private Boolean isExpired;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "is_client")
    private Boolean isClient;

}
