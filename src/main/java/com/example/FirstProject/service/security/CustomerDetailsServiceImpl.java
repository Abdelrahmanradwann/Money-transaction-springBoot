package com.example.FirstProject.service.security;

import com.example.FirstProject.model.Customer;
import com.example.FirstProject.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@AllArgsConstructor
@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
    private final CustomerRepository customerRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Customer customer = customerRepository.findUserByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("Customer not found"));
        return CustomerDetailsImpl
                .builder()
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }
}
