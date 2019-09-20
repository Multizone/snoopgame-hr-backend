package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.AuthenticationRequestDto;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.security.jwt.JwtTokenProvider;
import net.snoopgame.hr.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = {"http://192.168.1.10:8081"}, allowCredentials = "false", maxAge = 4800)
@RestController
@RequestMapping(value = "/api/auth/")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService){
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @CrossOrigin(origins = {"http://192.168.1.10:8081"}, maxAge = 6000)
    @PostMapping("login")
    public ResponseEntity login (@RequestBody AuthenticationRequestDto requestDto){

        try{
            String userEmail = requestDto.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEmail, requestDto.getPassword()));
            User user = userService.findByEmail(userEmail);

            if(user == null)
                throw new UsernameNotFoundException("User with e-mail: " + userEmail + "not found.");

            String token = jwtTokenProvider.createToken(userEmail, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("user_email", userEmail);
            response.put("token", token);
            response.put("userId", user.getId());

            return ResponseEntity.ok(response);
        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid login (e-mail) or password");
        }
    }

    @CrossOrigin
    @GetMapping("test")
    public ResponseEntity test (){
        return ResponseEntity.ok("Okay");
    }
}
