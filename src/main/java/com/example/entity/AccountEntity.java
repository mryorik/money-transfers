package com.example.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author yaroslav.frolikov
 */
@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(nullable = false, updatable = false)
    @NotNull
    Long id;

    @Column(nullable = false)
    @NotNull
    String name;

    @Column(nullable = false)
    String balance = "0.0";

    public AccountEntity() {
        // empty
    }

    public AccountEntity(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AccountEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getBalance() {
        return balance;
    }

    public AccountEntity setBalance(String balance) {
        this.balance = balance;
        return this;
    }
}
