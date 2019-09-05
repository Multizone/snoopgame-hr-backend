package net.snoopgame.hr.model.EditModels;

import lombok.Data;
import net.snoopgame.hr.model.Department;
import net.snoopgame.hr.model.Status;

import java.util.Date;
import java.util.List;

@Data
public class UserForEdit {

    private Long id;
    private String email;
    private String userName;
    private String userMiddleName;
    private String userLastName;
    private Date dateOfBirth;
    private Department department;
    private Date startWorkingDate;
    private Status status;
    private List<String> roleNames;
    private double spentSickDays;
    private double spentVacationDays;
    private double freeSickDays;
    private double freeVacationDays;
}
