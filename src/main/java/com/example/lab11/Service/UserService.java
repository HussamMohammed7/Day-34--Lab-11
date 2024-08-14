package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {


        userRepository.save(user);

    }

    public void updateUser(Integer id, User user) {
        User updatedUser = userRepository.findUserById(id);
        if (updatedUser == null) {
            throw new ApiException("user not found");
        }

        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setRegistration_date(LocalDate.now());

        userRepository.save(updatedUser);
    }

    public void removeUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new ApiException("user not found");
        }

        userRepository.deleteById(id);
    }

    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ApiException("User not found with email: " + email);
        }
        return user;
    }

    public User getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new ApiException("User not found with username: " + username);
        }
        return user;
    }

    public List<User> getUsersByRegistrationDateAfter(LocalDate date) {
        List users = userRepository.findUsersByRegistrationDateAfter(date);
         if (users == null) {
             throw new ApiException("no user have register after this date");
         }
         return users;
    }

    public List<User> getUsersByPasswordLengthLessThanEight() {
        List users = userRepository.findUsersByPasswordLengthLessThanEight();

        if (users == null) {
            throw new ApiException("no user have password length less than eight");
        }
        return users;
    }
    public List<User> getUsersByEmailDomain(String domain) {
        return userRepository.findUsersByEmailDomain(domain);
    }


}
