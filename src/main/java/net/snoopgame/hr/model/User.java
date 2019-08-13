package net.snoopgame.hr.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
public class User extends BaseEntity{

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "lastname")
    private String lastName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "birth date")
    private Date dateOfBirth;

    @Column(name = "department")
    private Department department;

    @Column(name = "workingSince")
    private Date startWorkingDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;
}
