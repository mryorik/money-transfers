package com.example.service;

import com.example.entity.Account;

import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

/**
 * @author yaroslav.frolikov
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public Account find(long id) {
        return Optional
                .ofNullable(TransactionManager.getEntityManager().find(Account.class, id))
                .orElseThrow(() -> new NotFoundException(String.format("Account %d is not found", id)));
    }

    @Override
    public Account save(Account account) {
        EntityManager entityManager = TransactionManager.getEntityManager();
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }
}
