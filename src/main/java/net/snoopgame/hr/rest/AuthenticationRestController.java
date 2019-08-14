package net.snoopgame.hr.rest;

import net.snoopgame.hr.dto.AuthenticationRequestDto;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.security.jwt.JwtTokenProvider;
import net.snoopgame.hr.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    public ResponseEntity login (@RequestBody AuthenticationRequestDto requestDto){

        try{
            String userName = requestDto.getUserName();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, requestDto.getPassword()));
            User user = userService.findByUserName(userName);

            if(user ==null)
                throw new UsernameNotFoundException("User with username: " + userName + "not found.");

            String token = jwtTokenProvider.createToken(userName, user.getRoles());
            Map<Object, Object> response = new HashMap<>();
            response.put("username", userName);
            response.put("token", token);

            return ResponseEntity.ok(response);
        }catch(AuthenticationException e){
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
