package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.UserDto;
import net.snoopgame.hr.model.HRcalculation.CalculationModel;
import net.snoopgame.hr.model.Role;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value="/api/user/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        CalculationModel model = new CalculationModel(user);
        model.calculateUserSickDays();
        UserDto result = UserDto.fromUser(user);
        System.out.println(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/getRoles/{id}")
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        return new ResponseEntity<>(user.getRoles(), HttpStatus.OK);
    }
}
