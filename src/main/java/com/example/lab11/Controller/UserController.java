package com.example.lab11.Controller;


import com.example.lab11.Model.User;
import com.example.lab11.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor

public class UserController {


    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {


        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsers()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        userService.addUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(
                "User added successfully"
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(
                "User updated successfully"
        );

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        userService.removeUser(id);

        return ResponseEntity.status(HttpStatus.OK).body(

                "User deleted successfully");
    }
    @GetMapping("/get-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUserByEmail(email)
        );
    }

    @GetMapping("/get-username/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUserByUsername(username)
        );
    }
    @GetMapping("/get-after-date/{date}")
    public ResponseEntity getUsersByRegistrationDateAfter(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsersByRegistrationDateAfter(localDate)
        );
    }
    @GetMapping("/password-less")
    public ResponseEntity getUsersByPasswordLengthLessThanEight() {

        return ResponseEntity.status(HttpStatus.OK).body(
                userService.getUsersByPasswordLengthLessThanEight()
        );
    }
    @GetMapping("/get-email-domain/{domain}")
    public ResponseEntity getUsersByEmailDomain(@PathVariable String domain) {
        List<User> users = userService.getUsersByEmailDomain(domain);
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}
