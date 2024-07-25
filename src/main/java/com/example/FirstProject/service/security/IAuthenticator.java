package com.example.FirstProject.service.security;


import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.dto.LoginRequestDTO;
import com.example.FirstProject.dto.LoginResponseDTO;
import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.model.Customer;
import org.springframework.stereotype.Service;


@Service
public interface IAuthenticator {
    Customer register(CustomerDTO customer) throws CustomerAlreadyExistsException;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) throws CustomerAlreadyExistsException;

}

