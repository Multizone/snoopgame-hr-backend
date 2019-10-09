package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.snoopgame.hr.model.Department;
import net.snoopgame.hr.model.DepartmentPosition;
import net.snoopgame.hr.model.HRcalculation.CalculationModel;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.model.VacationDays;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Date startWorkingDate;
    private double freeSickDays;
    private double freeVacationDays;
    private Date birthDate;
    private List<DepartmentPosition> positions;
    private List<VacationDays> vacationDays;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUserName(firstName);
        user.setLastName(lastName);
        user.setMiddleName(middleName);
        user.setEmail(email);

        return user;
    }

    public static UserDto fromUser(User user){
        CalculationModel model = new CalculationModel(user);
        UserDto userDto = new UserDto();

        userDto.setFirstName(user.getUserName());
        userDto.setLastName(user.getLastName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setFreeSickDays(model.calculateUserSickDays());
        userDto.setFreeVacationDays(model.calculateUserVacationDays());
        userDto.setStartWorkingDate(user.getStartWorkingDate());
        userDto.setBirthDate(user.getDateOfBirth());
        userDto.setPositions(user.getPosition());
        userDto.setVacationDays(user.getVacationDays());

        return userDto;
    }
}
