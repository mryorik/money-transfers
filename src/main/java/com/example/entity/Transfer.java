package com.example.entity;

import javax.persistence.*;

/**
 * @author yaroslav.frolikov
 */
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, updatable = false)
    private Long fromAccountId;

    @Column(nullable = false, updatable = false)
    private Long toAccountId;

    @Column(nullable = false, updatable = false)
    private Long amount;

    public Long getId() {
        return id;
    }

    public Transfer setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public Transfer setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
        return this;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public Transfer setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
        return this;
    }

    public Long getAmount() {
        return amount;
    }

    public Transfer setAmount(Long amount) {
        this.amount = amount;
        return this;
    }
}
