package com.adjt.agendamento.rest.controller;

import com.adjt.agendamento.rest.dto.request.LoginRequest;
import com.adjt.agendamento.rest.security.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    private final AuthenticationService authenticationService;

    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.authenticate(loginRequest.email(), loginRequest.senha());
    }
}
