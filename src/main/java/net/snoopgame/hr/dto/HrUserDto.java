package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.snoopgame.hr.model.Department;
import net.snoopgame.hr.model.Status;
import net.snoopgame.hr.model.User;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HrUserDto {

    private String userName;
    private String userMiddleName;
    private String userLastName;
    private String email;
    private Date startWorkingDate;
    private Department department;
    private double freeSickDays;
    private double freeVacationDays;
    private Status status;

    public static HrUserDto fromUser(User user){
        HrUserDto hrDto = new HrUserDto();

        hrDto.setUserName(user.getUserName());
        hrDto.setUserMiddleName(user.getMiddleName());
        hrDto.setUserLastName(user.getLastName());
        hrDto.setEmail(user.getEmail());
        hrDto.setStartWorkingDate(user.getStartWorkingDate());
        hrDto.setDepartment(user.getDepartment());
        hrDto.setStatus(user.getStatus());

        return hrDto;
    }
}
