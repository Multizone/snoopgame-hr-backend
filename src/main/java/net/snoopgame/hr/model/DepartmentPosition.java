package net.snoopgame.hr.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "positions")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"position", "department"})
public class DepartmentPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "position_id")
    private Long id;

    @Column(name = "position")
    private String position;

    /*@JsonIgnore
    @OneToMany(mappedBy = "positions")
    private List<User> users;*/

    @Enumerated(EnumType.STRING)
    @Column(name = "department")
    private Department department;

    @Column(name = "startWorkingInThisPositionDate")
    private Date startWorkingInThisPositionDate;

    @Column(name ="position_salary")
    private int salary;
}
