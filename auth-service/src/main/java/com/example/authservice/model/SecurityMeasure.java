package com.example.authservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "security_measure")
public class SecurityMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measureId;
    private String description;
    @Enumerated(EnumType.STRING)
    private EMeasuresCategory category;

    @ManyToMany(mappedBy = "securityMeasures")
    private Set<Risk> risks;

}
