package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.AdminUserDto;
import net.snoopgame.hr.model.EditModels.UserForEdit;
import net.snoopgame.hr.model.HRcalculation.CalculationModel;
import net.snoopgame.hr.model.Role;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.repository.PositionsRepository;
import net.snoopgame.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"*"}, allowCredentials = "false")
@RestController
@RequestMapping(value = "/api/admin")
public class AdminRestController {

    private final UserService userService;
    private final PositionsRepository pRepository;

    @Autowired
    public AdminRestController(UserService userService, PositionsRepository pRepository){
        this.userService = userService;
        this.pRepository = pRepository;
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

    @CrossOrigin
    @PostMapping(path = "/add")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.register(user);
        System.out.println(user);
        System.out.println(user.getPosition());
        return new ResponseEntity<>("User was successfully added", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<AdminUserDto>> getAllUsers(){
        List<AdminUserDto> result = AdminUserDto.fromUsers(userService.getAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @CrossOrigin
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") User user){
        userService.delete(user.getId());
        return new ResponseEntity<>("User was removed", HttpStatus.OK);
    }

    @CrossOrigin
    @PutMapping("/edit/{id}")
    public ResponseEntity<User> editUserRoles(@PathVariable("id") User user, @RequestBody UserForEdit newUser){
        return new ResponseEntity<>(userService.editUser(user, newUser), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/test")
    public ResponseEntity<String> testMethod(){
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/testget")
    public ResponseEntity<String> testGetMethod(){
        return  new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
