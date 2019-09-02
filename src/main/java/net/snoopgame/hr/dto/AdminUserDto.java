package net.snoopgame.hr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.snoopgame.hr.model.Department;
import net.snoopgame.hr.model.User;

import java.util.ArrayList;
import java.util.Date;
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
    private List<String> userRoles;
    private Date dateOfBirth;
    private Department department;
    private Date startWorkingDate;
    private Date created;
    private Date updated;

    public static AdminUserDto fromUser(User user){
        AdminUserDto adminUserDto = new AdminUserDto();

        adminUserDto.setId(user.getId());
        adminUserDto.setUserName(user.getUserName());
        adminUserDto.setUserMiddleName(user.getMiddleName());
        adminUserDto.setUserLastName(user.getLastName());
        adminUserDto.setEmail(user.getEmail());
        adminUserDto.setStatus(user.getStatus().name());
        adminUserDto.setDateOfBirth(user.getDateOfBirth());
        adminUserDto.setDepartment(user.getDepartment());
        adminUserDto.setStartWorkingDate(user.getStartWorkingDate());
        adminUserDto.setCreated(user.getCreated());
        adminUserDto.setUpdated(user.getUpdated());

        ArrayList<String> roles = new ArrayList<>();
        for(int i=0; i<user.getRoles().size(); i++)
            roles.add(user.getRoles().get(i).getRole());

        adminUserDto.setUserRoles(roles);

        return adminUserDto;
    }
}
