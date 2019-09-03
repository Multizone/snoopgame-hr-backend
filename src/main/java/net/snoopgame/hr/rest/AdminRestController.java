package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.AdminUserDto;
import net.snoopgame.hr.dto.UserDto;
import net.snoopgame.hr.model.HRcalculation.CalculationModel;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id){

        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        AdminUserDto result = AdminUserDto.fromUser(user);
        CalculationModel test = new CalculationModel(user);
        test.calculateUserSickdays();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
