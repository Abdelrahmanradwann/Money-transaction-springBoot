package com.example.FirstProject.controller;


import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.dto.LoginRequestDTO;
import com.example.FirstProject.dto.LoginResponseDTO;
import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.service.security.IAuthenticator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.parameters.P;
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
    @Operation(
            summary = "Registration",
            parameters = @Parameter(name = "customer",description = "Attributes of registered customer"),
            responses = {
                    @ApiResponse(responseCode = "200",description = "Successfully registered",
                            content = @Content(schema = @Schema(implementation = Customer.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Customer register(@RequestBody @Valid CustomerDTO customer) throws CustomerAlreadyExistsException {
        return this.auth.register(customer);
    }

    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Authenticates a user with provided credentials and returns a login response with tokens.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Login successful"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) throws CustomerAlreadyExistsException{
        return this.auth.login(loginRequestDTO);
    }
}
