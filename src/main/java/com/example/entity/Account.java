package com.example.entity;

import javax.persistence.*;

/**
 * @author yaroslav.frolikov
 */
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long balance = 0L;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Account setName(String name) {
        this.name = name;
        return this;
    }

    public Long getBalance() {
        return balance;
    }

    public Account setBalance(Long balance) {
        this.balance = balance;
        return this;
    }
}
