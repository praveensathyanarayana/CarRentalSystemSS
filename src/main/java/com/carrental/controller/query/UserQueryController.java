package com.carrental.controller.query;

import com.carrental.dto.UserResponse;
import com.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users/queries")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserService userService;

    @GetMapping("/list")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/by-email")
    public UserResponse getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }
}