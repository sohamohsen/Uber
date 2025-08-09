package com.uber.uber.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status = AccountStatus.ACTIVE;

    @Column(name = "is_online", nullable = false)
    private boolean isOnline = false;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Driver driver;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Rider rider;

    public enum AccountType {
        RIDER, DRIVER, ADMIN
    }

    public enum AccountStatus {
        ACTIVE, INACTIVE, SUSPENDED, DELETED
    }
}
