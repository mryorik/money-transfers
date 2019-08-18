package com.example.service;

import com.example.entity.Account;
import com.example.entity.Transfer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * @author yaroslav.frolikov
 */
public class TransferServiceImpl implements TransferService {
    @Inject
    AccountService accountService;

    @Override
    public Transfer makeTransfer(Transfer transfer) {
        if (transfer.getAmount() <= 0) {
            throw new ClientErrorException("Amount should be a positive value", Response.Status.BAD_REQUEST);
        }

        if (transfer.getFromAccountId().equals(transfer.getToAccountId())) {
            throw new ClientErrorException("Accounts must differ", Response.Status.BAD_REQUEST);
        }

        EntityManager entityManager = TransactionManager.getEntityManager();

        Account fromAccount = accountService.find(transfer.getFromAccountId());
        entityManager.lock(fromAccount, LockModeType.READ);

        if (fromAccount.getBalance() >= transfer.getAmount()) {
            Account toAccount = accountService.find(transfer.getToAccountId());
            entityManager.lock(toAccount, LockModeType.READ);

            fromAccount.setBalance(fromAccount.getBalance() - transfer.getAmount());
            toAccount.setBalance(toAccount.getBalance() + transfer.getAmount());
            accountService.update(fromAccount);
            accountService.update(toAccount);
        } else {
            throw new ClientErrorException("Insufficient funds", Response.Status.BAD_REQUEST);
        }

        entityManager.persist(transfer);
        entityManager.flush();
        entityManager.refresh(transfer);
        return transfer;
    }
}
