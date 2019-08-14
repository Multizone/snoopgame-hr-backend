package net.snoopgame.hr.repository;

import net.snoopgame.hr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String name);
    User findByEmail(String email);
}
