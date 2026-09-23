package com.ecom.auth_service.controller;

import com.ecom.auth_service.dto.UserResponse;
import com.ecom.auth_service.dto.UserUpdateRequest;
import com.ecom.auth_service.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AuthService authService;

    public UserController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getMyProfile(
            @RequestHeader("X-User-Id") Long userId) {

        UserResponse response = authService.getUserById(userId);

        return ResponseEntity.ok(response);
    }
    @PutMapping("/me")
    public ResponseEntity<UserResponse> updateMyProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UserUpdateRequest request) {

        UserResponse response =
                authService.updateUser(userId, request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public String test() {
        return "JWT authentication successful";
    }
}