package com.gialongchuai.shopapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50) COLLATE utf8mb4_general_ci")
    String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    List<User> users;
}
