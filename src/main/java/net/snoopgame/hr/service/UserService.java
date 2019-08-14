package net.snoopgame.hr.service;

import net.snoopgame.hr.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUserName(String userName);

    User findByEmail(String email);

    User findById(Long id);

    void delete(Long id);
}
