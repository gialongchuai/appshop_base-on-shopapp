package com.gialongchuai.shopapp.entities;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "social_accounts")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "provider", nullable = false, columnDefinition = "VARCHAR(20) COLLATE utf8mb4_general_ci")
    String provider;

    @Column(name = "provider_id", nullable = false, columnDefinition = "VARCHAR(50) COLLATE utf8mb4_general_ci")
    String providerId;

    @Column(name = "email", nullable = false, columnDefinition = "VARCHAR(150) COLLATE utf8mb4_general_ci")
    String email;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;
}
