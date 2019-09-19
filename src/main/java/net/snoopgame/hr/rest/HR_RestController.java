package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.AdminUserDto;
import net.snoopgame.hr.dto.HrUserDto;
import net.snoopgame.hr.model.HRcalculation.CalculationModel;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/hr")
public class HR_RestController {

    private final UserService userService;

    @Autowired
    public HR_RestController(UserService userService){ this.userService = userService; }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<HrUserDto> getUserById(@PathVariable(name = "id") Long id){
        User user = userService.findById(id);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        HrUserDto result = HrUserDto.fromUser(user);
        CalculationModel model = new CalculationModel(user);
        model.calculateUserVacationDays();
        model.calculateUserSickDays();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.register(user);
        return new ResponseEntity<>("User was successfully added", HttpStatus.OK);
    }

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<HrUserDto>> getAllUsers(){
        List<HrUserDto> result = HrUserDto.fromUsers(userService.getAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}
