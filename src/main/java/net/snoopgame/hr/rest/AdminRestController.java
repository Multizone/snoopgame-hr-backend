package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.AdminUserDto;
import net.snoopgame.hr.model.HRcalculation.CalculationModel;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id){

        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        AdminUserDto result = AdminUserDto.fromUser(user);
        CalculationModel test = new CalculationModel(user);
        test.calculateUserSickDays();
        test.calculateUserVacationDays();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.register(user);
        return new ResponseEntity<>("User was successfully added", HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<AdminUserDto>> getAllUsers(){
        List<AdminUserDto> result = AdminUserDto.fromUsers(userService.getAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") User user){
        userService.delete(user.getId());
        return new ResponseEntity<>("User was removed", HttpStatus.OK);
    }
}
