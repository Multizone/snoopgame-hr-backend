package net.snoopgame.hr.service.implementation;

import lombok.extern.slf4j.Slf4j;
import net.snoopgame.hr.model.Role;
import net.snoopgame.hr.model.Status;
import net.snoopgame.hr.model.User;
import net.snoopgame.hr.repository.RoleRepository;
import net.snoopgame.hr.repository.UserRepository;
import net.snoopgame.hr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository uRepository;
    private final RoleRepository rRepository;

    @Autowired
    public UserServiceImpl(UserRepository uRepository, RoleRepository rRepository) {
        this.uRepository = uRepository;
        this.rRepository = rRepository;
    }

    @Override
    public User register(User user) {
        Role uRole = rRepository.findByRole("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(uRole);

        user.setPassword(user.getPassword());
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = uRepository.save(user);
        log.info("In register method user - {} was created successfully", registeredUser);

        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> users = uRepository.findAll();
        log.info("In getAll method - {} users found", users.size());
        return users;
    }

    @Override
    public User findByUserName(String userName) {
        User result = uRepository.findByUserName(userName);

        if(result == null)
            log.warn("In findByUserName no users found");
        else
            log.info("In findByUserName - {} user was found by username - {} ", result, userName);

        return result;
    }

    @Override
    public User findById(Long id) {
        User result = uRepository.findById(id).orElse(null);


        if(result == null)
            log.warn("In findByUserId no users found with id: {}", id);
        else
            log.info("In findByUserId method was found a {}, with id - {}", result.getUserName(), id);

        return result;
    }

    @Override
    public void delete(Long id) {
        User deletePromise = uRepository.findById(id).orElse(null);
        if(deletePromise != null) {
            uRepository.deleteById(id);
            log.warn("In method deleteUser, user: {}  with id: {} was successfully deleted!", deletePromise.getUserName(), id);
        }
    }
}
