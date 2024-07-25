package com.example.FirstProject;

import com.example.FirstProject.controller.AuthController;
import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.exception.custom.CustomerAlreadyExistsException;
import com.example.FirstProject.model.Customer;
import com.example.FirstProject.model.enums.Gender;
import com.example.FirstProject.service.security.IAuthenticator;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

    @Mock
    private IAuthenticator auth;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    public AuthControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    public void testRegister() throws Exception, CustomerAlreadyExistsException {
        // Create and set up a CustomerDTO object with test data
        CustomerDTO customerDTO = CustomerDTO.builder()
                .email("test@example.com")
                .password("password123")
                .fname("John")
                .lname("Doe")
                .DOB(LocalDate.of(1990, 1, 1)) // Example date of birth
                .nationality("American")
                .nationalId("123456789")
                .address("123 Main St, Springfield")
                .gender(Gender.MALE)
                .build();

        // Create a Customer object to return from the mocked service method
        Customer customer = Customer.builder()
                .id(1L)
                .email(customerDTO.getEmail())
                .password(customerDTO.getPassword())
                .fname(customerDTO.getFname())
                .lname(customerDTO.getLname())
                .DOB(customerDTO.getDOB())
                .nationality(customerDTO.getNationality())
                .nationalId(customerDTO.getNationalId())
                .address(customerDTO.getAddress())
                .gender(customerDTO.getGender())
                .build();

        // Mock the behavior of the auth.register method
        when(auth.register(customerDTO)).thenReturn(customer);

        // Perform the POST request and verify the response
        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"password\":\"password123\",\"fname\":\"John\",\"lname\":\"Doe\",\"DOB\":\"1990-01-01\",\"nationality\":\"American\",\"nationalId\":\"123456789\",\"address\":\"123 Main St, Springfield\",\"gender\":\"MALE\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
