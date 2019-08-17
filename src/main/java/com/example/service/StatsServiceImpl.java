package com.example.service;

import com.example.feature.TransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * @author yaroslav.frolikov
 */
public class StatsServiceImpl implements StatsService {
    @Override
    public Long getOverallBalance() {
        EntityManager entityManager = TransactionManager.getEntityManager();

        Query query = entityManager.createQuery("SELECT SUM(a.balance) FROM Account a", Long.class);
        return (Long) query.getSingleResult();
    }
}
