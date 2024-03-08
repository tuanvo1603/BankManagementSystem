package com.example.authservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`user`")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String createdAt;

    @OneToMany(mappedBy = "sessionId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Session> sessions;

    @OneToMany(mappedBy = "logId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserActivityLog> userActivityLogs;
}
