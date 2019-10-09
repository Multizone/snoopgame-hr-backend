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
@ToString(of = {"dateOfVacation", "type"})
public class VacationDays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "day_id")
    private Long id;

    @Column(name = "date")
    private Date dateOfVacation;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VacationType type;
}
