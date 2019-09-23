package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.snoopgame.hr.model.Department;
import net.snoopgame.hr.model.Status;
import net.snoopgame.hr.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HrUserDto {

    private String userName;
    private String userMiddleName;
    private String userLastName;
    private String email;
    private Date dateOfBirth;
    private Date startWorkingDate;
    private double freeSickDays;
    private double freeVacationDays;
    private Status status;

    public static HrUserDto fromUser(User user){
        HrUserDto hrDto = new HrUserDto();

        hrDto.setUserName(user.getUserName());
        hrDto.setUserMiddleName(user.getMiddleName());
        hrDto.setUserLastName(user.getLastName());
        hrDto.setEmail(user.getEmail());
        hrDto.setDateOfBirth(user.getDateOfBirth());
        hrDto.setStartWorkingDate(user.getStartWorkingDate());
        hrDto.setStatus(user.getStatus());

        return hrDto;
    }

    public static List<HrUserDto> fromUsers(List<User> users){
        List<HrUserDto> usersList = new ArrayList<>();

        for(int i=0; i< users.size(); i++){
            User user = users.get(i);
            usersList.add(fromUser(user));
        }

        return usersList;
    }
}
