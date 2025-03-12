package com.gialongchuai.shopapp.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {@UniqueConstraint(name = "unique_phone_number", columnNames = "phone_number")})
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "fullname", columnDefinition = "VARCHAR(100) COLLATE utf8mb4_general_ci")
    String fullname;

    @Column(name = "phone_number", columnDefinition = "VARCHAR(15) COLLATE utf8mb4_general_ci")
    String phoneNumber;

    @Column(name = "address", columnDefinition = "VARCHAR(200) COLLATE utf8mb4_general_ci")
    String address;

    @Column(name = "password", nullable = false, columnDefinition = "CHAR(60) COLLATE utf8mb4_general_ci")
    String password;

    @Column(name = "created_at")
    LocalDate createdAt;

    @Column(name = "updated_at")
    LocalDate updatedAt;

    @Column(name = "is_active", columnDefinition = "TINYINT(1) DEFAULT 1")
    Boolean isActive;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "facebook_account_id", columnDefinition = "INT DEFAULT 0")
    Integer facebookAccountId;

    @Column(name = "google_account_id", columnDefinition = "INT DEFAULT 0")
    Integer googleAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<SocialAccount> socialAccounts;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders;
}
