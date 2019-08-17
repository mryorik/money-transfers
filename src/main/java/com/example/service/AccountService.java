package com.example.service;

import com.example.entity.AccountEntity;

/**
 * @author yaroslav.frolikov
 */
public interface AccountService {
    AccountEntity find(long id);

    AccountEntity save(AccountEntity account);

    AccountEntity update(AccountEntity account);

    void delete(AccountEntity account);
}
