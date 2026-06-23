package com.control_activos.sks.control_activos.controller;

import com.control_activos.sks.control_activos.config.AuthorizationService;
import com.control_activos.sks.control_activos.models.dto.LoginRequest;
import com.control_activos.sks.control_activos.models.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor()
public class LoginController {

    private final AuthorizationService authorizationService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authorizationService.login(loginRequest.username(), loginRequest.password());
        return ResponseEntity.ok().body(loginResponse);
    }
}
