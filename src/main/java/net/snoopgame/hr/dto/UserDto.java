package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.snoopgame.hr.model.Department;
import net.snoopgame.hr.model.User;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Date startWorkingDate;
    private Department department;
    private double freeSickDays;

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
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getUserName());
        userDto.setLastName(user.getLastName());
        userDto.setMiddleName(user.getMiddleName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setFreeSickDays(user.getFreeSickDays());
        userDto.setDepartment(user.getDepartment());
        userDto.setStartWorkingDate(user.getStartWorkingDate());

        return userDto;
    }
}
