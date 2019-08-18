package com.example.entity;

import javax.persistence.*;

/**
 * @author yaroslav.frolikov
 */
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long balance = 0L;

    @Version
    private Long version = 0L;

    public Long getId() {
        return id;
    }

    public Account setId(Long id) {
        this.id = id;
        return this;
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

    public Long getVersion() {
        return version;
    }
}
