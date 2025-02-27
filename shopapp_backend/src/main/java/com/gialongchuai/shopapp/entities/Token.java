package com.gialongchuai.shopapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "tokens", uniqueConstraints = {
        @UniqueConstraint(name = "unique_token", columnNames = "token")
})
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "token", nullable = false, columnDefinition = "VARCHAR(255) COLLATE utf8mb4_general_ci")
    String token;

    @Column(name = "token_type", nullable = false, columnDefinition = "VARCHAR(50) COLLATE utf8mb4_general_ci")
    String tokenType;

    @Column(name = "expiration_date")
    LocalDateTime expirationDate;

    @Column(name = "revoked", nullable = false, columnDefinition = "TINYINT(1)")
    Boolean revoked;

    @Column(name = "expired", nullable = false, columnDefinition = "TINYINT(1)")
    Boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @Column(name = "is_mobile", columnDefinition = "TINYINT(1) DEFAULT 0")
    Boolean isMobile;

    @Column(name = "refresh_token", columnDefinition = "VARCHAR(255) COLLATE utf8mb4_general_ci DEFAULT ''")
    String refreshToken;

    @Column(name = "refresh_expiration_date")
    LocalDateTime refreshExpirationDate;
}