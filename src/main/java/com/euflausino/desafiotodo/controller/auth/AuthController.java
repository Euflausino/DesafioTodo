package com.euflausino.desafiotodo.controller.auth;

import com.euflausino.desafiotodo.dto.user.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {


    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<Void> fazerLogin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        var token = new UsernamePasswordAuthenticationToken(userRequestDTO.login(), userRequestDTO.senha());
        var auth = authenticationManager.authenticate(token);
        return null;
    }
}
