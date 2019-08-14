package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import net.snoopgame.hr.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;

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

        return userDto;
    }
}
