package com.example.FirstProject;


import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.service.CustomerService;
import com.example.FirstProject.service.security.IAuthenticator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    // MockBean for dummy object that 'll be created
    @MockBean
    private IAuthenticator auth;

    // MockMvc is a class used for testing, and it is configured
    // that when we write @SpringBootTest and @AutoConfi.... it generates all the methods that we could need to test,
    // so we don't need to put @Component or @Service on top of class MockMvc
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testValidRegisterCustomer() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n    \"email\": \"hh@yahoo.com\",\r\n    \"password\": \"123456\",\r\n  " +
                        "  \"fname\": \"John\",\r\n    \"lname\": \"Doe\",\r\n   " +
                        " \"DOB\": \"1990-01-01\",\r\n    \"phoneNumber\": \"01011799212\",\r\n " +
                        "   \"nationality\": \"American\",\r\n   " +
                        " \"nationalId\": \"123456\",\r\n    " +
                        "\"address\": \"123 Main St, Springfield, IL\",\r\n  " +
                        "  \"gender\": \"MALE\"\r\n}")
                )
                .andExpect(status().isOk());
    }

    @Test
    void testInvalidRegisterCustomer() throws Exception {
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\r\n    \"email\": \"hh@yahoo.com\",\r\n    \"password\": \"123456\",\r\n ")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCustomerAlredyExists() throws  Exception {
        Mockito.when(auth.register(any(CustomerDTO.class)))
                .thenThrow(new CustomerAlreadyExistsException("User already exixts"));
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\r\n    \"email\": \"hh@yahoo.com\",\r\n    \"password\": \"123456\",\r\n "))
                .andExpect(status().isBadRequest());
    }
}
