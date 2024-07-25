package com.example.FirstProject.controller;


import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.dto.LoginRequestDTO;
import com.example.FirstProject.dto.LoginResponseDTO;
import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.service.security.IAuthenticator;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("api/auth")
@Validated
public class AuthController {

    private final IAuthenticator auth;

    @PostMapping("/register")
    public Customer register(@RequestBody CustomerDTO customer) throws CustomerAlreadyExistsException {
        return this.auth.register(customer);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO) throws CustomerAlreadyExistsException{
        return this.auth.login(loginRequestDTO);
    }
}
