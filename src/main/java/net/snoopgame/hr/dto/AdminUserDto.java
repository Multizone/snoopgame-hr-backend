package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.snoopgame.hr.model.Role;
import net.snoopgame.hr.model.Status;
import net.snoopgame.hr.model.User;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {

    private Long id;
    private String userName;
    private String userMiddleName;
    private String userLastName;
    private String email;
    private String status;
    private List<Role> userRoles;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUserName(userName);
        user.setMiddleName(userMiddleName);
        user.setLastName(userLastName);
        user.setEmail(email);
        user.setStatus(Status.valueOf(status));
        user.setRoles(userRoles);
        return user;
    }

    public static AdminUserDto fromUser(User user){
        AdminUserDto adminUserDto = new AdminUserDto();

        adminUserDto.setId(user.getId());
        adminUserDto.setUserName(user.getUserName());
        adminUserDto.setUserMiddleName(user.getMiddleName());
        adminUserDto.setUserLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setStatus(user.getStatus().name());
        adminUserDto.setUserRoles(user.getRoles());

        return adminUserDto;
    }
}
