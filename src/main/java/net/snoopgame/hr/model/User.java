package net.snoopgame.hr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.mapping.Join;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employees")
@Data
@ToString(of = {"userName", "password", "roles", "userRoles", "department"})
@EqualsAndHashCode(of = {"id"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "e_mail")
    private String email;

    @NotNull
    @Column(name = "user_name")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "birth_date")
    private Date dateOfBirth;

    @Column(name = "working_since")
    private Date startWorkingDate;

    @CreatedDate
    @Column(name = "creationDate")
    private Date created;

    @LastModifiedDate
    @Column(name = "updatedDate")
    private Date updated;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Column(name = "spent_sick_days")
    private double spentSickDaysCount;

    @Column(name = "spent_vacation_days")
    private double spentVacationDaysCount;

    @OneToMany
    @JoinTable(name = "positions",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn( name = "position_id")})
    private List<DepartmentPosition> position;

    @OneToMany
    @JoinTable(name = "vacation_days",
        joinColumns = {@JoinColumn(name = "user_id")},
        inverseJoinColumns = {@JoinColumn( name = "day_id")})
    private List<VacationDays> vacationDays;
}
