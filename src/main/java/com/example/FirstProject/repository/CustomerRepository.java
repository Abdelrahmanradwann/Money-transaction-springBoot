package com.example.FirstProject.repository;

import com.example.FirstProject.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByemailEquals(String email);
    Optional<Customer> findTopByOrderByRegisterDesc();
    boolean existsByEmail(String email);
    boolean existsByNationalId(String nationalId);
    boolean existsByPhoneNumber(String number);

    Optional<Customer> findUserByEmail(String email);

    @Query("SELECT c FROM Customer c WHERE c.account.accountNumber = :accountNumber")
    Optional<Customer> findByAccountNumber(@Param("accountNumber") String accountNumber);
}
