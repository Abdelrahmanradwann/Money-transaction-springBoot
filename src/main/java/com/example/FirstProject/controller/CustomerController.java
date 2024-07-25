package com.example.FirstProject.controller;

import com.example.FirstProject.exception.custom.CustomerNotFoundException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.dto.UpdateCustomerDTO;
import com.example.FirstProject.service.CustomerData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
@Validated
public class CustomerController {
    @Autowired
    private CustomerData customerData;
    @GetMapping("/customer/{id}")
    @Operation(
            summary = "Retrieve a customer by ID",
            description = "Fetch a customer's details by their unique ID.",
            parameters = {
                    @Parameter(name = "id", description = "The unique ID of the customer", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved customer",
                        content = @Content (schema = @Schema(implementation = RetrieveCustomerDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Customer not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public RetrieveCustomerDTO getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        return this.customerData.getCustById(id);
    }
    @GetMapping("/customer/email/{email}")
    @Operation(
            summary = "Retrieve a user by email",
            description = "Fetch a user's details by their email address.",
            parameters = {
                    @Parameter(name = "email", description = "The email address of the user", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user",
                            content = @Content(schema = @Schema(implementation = RetrieveCustomerDTO.class))),
                    @ApiResponse(responseCode = "404", description = "User not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public RetrieveCustomerDTO getCustomerByEmail(@PathVariable String email) throws CustomerNotFoundException{
        return this.customerData.getCustomerByEmail(email);
    }
    @PutMapping("/customer/{id}")
    @Operation(
            summary = "Update customer details",
            description = "Update the details of an existing customer by their ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Customer details to be updated",
                    required = true,
                    content = @Content(schema = @Schema(implementation = UpdateCustomerDTO.class))
            ),
            parameters = {
                    @Parameter(name = "id", description = "The unique customer id", required = true),
                    @Parameter(name = "customer", description = "The updated customer data",required = true)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated customer",
                    content = @Content(schema = @Schema(implementation = RetrieveCustomerDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public RetrieveCustomerDTO updateCustomer(@PathVariable Long id,
                                              @RequestBody UpdateCustomerDTO customer) throws CustomerNotFoundException{
        return this.customerData.updateCust(id,customer);
    }
    @DeleteMapping("/customer/{id}")
    @Operation(
            summary = "Delete a customer",
            description = "Delete an existing customer by their ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Customer successfully deleted"),
                    @ApiResponse(responseCode = "404", description = "Customer not found"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public Boolean deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException{
        return this.customerData.deleteCustomer(id);
    }
    @GetMapping("/customer/latest")
    public Customer getLatestCustomer(){
        return this.customerData.getLatestCust();
    }



}
