package com.insurance.policy.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String policyNumber;

    private String policyType; // LIFE, HEALTH, VEHICLE

    private Double premiumAmount;

    private Double coverageAmount;

    private LocalDate startDate;

    private LocalDate endDate;

    private String status; // ACTIVE, EXPIRED

    private Long userId; // from Auth Service
}