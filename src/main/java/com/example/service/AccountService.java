package com.example.service;

import com.example.entity.Account;

/**
 * @author yaroslav.frolikov
 */
public interface AccountService {
    Account find(long id);

    Account save(Account account);

    Account update(Account account);
}
