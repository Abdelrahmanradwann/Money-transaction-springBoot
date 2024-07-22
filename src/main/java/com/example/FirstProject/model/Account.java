package com.example.FirstProject.model;

import com.example.FirstProject.dto.AccountDTO;
import com.example.FirstProject.dto.CustomerDTO;
import com.example.FirstProject.model.enums.AccountType;
import com.example.FirstProject.model.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String accountNumber;
    @Column(nullable = false)
    private String accountName;
    @Column(nullable = false)
    private Double balance;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(nullable = false)
    private String accountDescription;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Boolean active;
    @OneToOne(mappedBy = "account")
    private Customer customer;
    public AccountDTO toDTO() {
        return AccountDTO
                .builder()
                .id(this.getId())
                .accountType(this.getAccountType())
                .accountName(this.getAccountName())
                .balance(this.getBalance())
                .currency(this.getCurrency())
                .accountType(this.getAccountType())
                .updatedAt(this.getUpdatedAt())
                .createdAt(this.getCreatedAt())
                .accountDescription(this.getAccountDescription())
                .active(this.getActive())
                .build();
       }


}
