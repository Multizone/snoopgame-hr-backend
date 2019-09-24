package net.snoopgame.hr.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vacation_days")
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"dateOfVacation", "type", "user"})
public class VacationDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date dateOfVacation;

    @Column(name = "type")
    private VacationType type;

    /*@OneToOne(mappedBy = "type", fetch = FetchType.LAZY)
    private User user;*/
}
