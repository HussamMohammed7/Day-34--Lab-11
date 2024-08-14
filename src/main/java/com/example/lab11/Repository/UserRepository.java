package com.example.lab11.Repository;

import com.example.lab11.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    @Query("select u from User u where u.registration_date > ?1")
    List<User> findUsersByRegistrationDateAfter(LocalDate date);

    @Query("select u from User u where length(u.password) < 8")
    List<User> findUsersByPasswordLengthLessThanEight();

    @Query("select u from User u where u.email like %?1")
    List<User> findUsersByEmailDomain(String domain);
}
