package com.example.authservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "risk")
public class Risk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long riskId;
    private String description;
    @Enumerated(EnumType.STRING)
    private ERiskCategory category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "risk_security_measure",
                joinColumns = @JoinColumn(name = "risk_id"),
                inverseJoinColumns = @JoinColumn(name = "security_measure_id"))
    private Set<SecurityMeasure> securityMeasures;

}
