package com.example.FirstProject.model;


import com.example.FirstProject.dto.RetrieveCustomerDTO;
import com.example.FirstProject.model.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    @Size(min=5)
    private String password;
    @Column(nullable = false)
    private  String fname;
    @Column(nullable = false)
    private  String lname;
    @Column(nullable = false)
    private LocalDate DOB;
    @Column(nullable = false,unique = true)
    private String phoneNumber;
    @Column(nullable = false)
    private String nationality;
    @Column(nullable = false,unique = true)
    private String nationalId;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime register ;

    @OneToOne
    @JoinColumn(name = "accountId")
    private Account account;

    public RetrieveCustomerDTO toDTO() {

        return RetrieveCustomerDTO
                .builder()
                .id(this.getId())
                .fname(this.getFname())
                .lname(this.getLname())
                .DOB(this.getDOB())
                .nationality(this.getNationality())
                .nationalId(this.getNationalId())
                .address(this.getAddress())
                .gender(this.getGender())
                .account(this.getAccount().toDTO())
                .build();
    }
}
