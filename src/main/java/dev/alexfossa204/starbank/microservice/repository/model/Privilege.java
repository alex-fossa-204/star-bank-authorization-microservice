package dev.alexfossa204.starbank.microservice.repository.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"id", "roles"})
@EqualsAndHashCode(exclude = {"id", "roles"})

@Entity
@Table(name = "privilege")
public class Privilege implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "privilege")
    private String privilegeName;

    @Column(name = "public_uuid", unique = true)
    private UUID publicUuid;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
