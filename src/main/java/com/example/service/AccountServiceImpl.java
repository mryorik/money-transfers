package com.example.service;

import com.example.entity.AccountEntity;
import com.example.feature.TransactionManager;

import javax.persistence.EntityManager;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import java.util.Optional;

/**
 * @author yaroslav.frolikov
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public AccountEntity find(long id) {
        return Optional
                .ofNullable(TransactionManager.getEntityManager().find(AccountEntity.class, id))
                .orElseThrow(() -> new NotFoundException(String.format("Account %d not found", id)));
    }

    @Override
    public AccountEntity save(AccountEntity account) {
        EntityManager entityManager = TransactionManager.getEntityManager();
        account.setBalance("0.0");
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public AccountEntity update(AccountEntity account) {
        throw new InternalServerErrorException("Not implemented");
    }

    @Override
    public void delete(AccountEntity account) {
        throw new InternalServerErrorException("Not implemented");
    }
}
