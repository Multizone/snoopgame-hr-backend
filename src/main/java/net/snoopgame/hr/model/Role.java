package net.snoopgame.hr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "role"})
public class Role{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

    @JsonIgnore
    @JsonIgnoreProperties(value = "users")
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
