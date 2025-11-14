package com.carrental.controller.command;

import com.carrental.dto.UserRequest;
import com.carrental.dto.UserResponse;
import com.carrental.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/commands")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@Valid @RequestBody UserRequest request) {
        return userService.addUser(request);
    }

    @PutMapping("/update")
    public UserResponse updateUser(@Valid @RequestBody UserRequest request) {
        return userService.updateUser(request);
    }

    @DeleteMapping("/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disableUser(@RequestParam String email) {
        userService.disableUser(email);
    }
}