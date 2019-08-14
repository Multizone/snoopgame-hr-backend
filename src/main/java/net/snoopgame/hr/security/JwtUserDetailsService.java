package net.snoopgame.hr.security;

import lombok.extern.slf4j.Slf4j;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.security.jwt.JwtUser;
import net.snoopgame.hr.security.jwt.JwtUserFactory;
import net.snoopgame.hr.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailsService(UserService userService){
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if(user == null )
            throw new UsernameNotFoundException("In method loadUserByUserName (Overrided method), user with e-mail: " + email + " not found");

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("In loadByUserName - user with e-mail: {} successfully loaded", email);

        return jwtUser;
    }
}
