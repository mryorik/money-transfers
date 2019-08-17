package com.example.feature;

import org.glassfish.jersey.server.model.ResourceMethod;
import org.glassfish.jersey.server.monitoring.ApplicationEvent;
import org.glassfish.jersey.server.monitoring.ApplicationEventListener;
import org.glassfish.jersey.server.monitoring.RequestEvent;
import org.glassfish.jersey.server.monitoring.RequestEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author yaroslav.frolikov
 */
public class TransactionManager implements ApplicationEventListener {
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("h2");

    private static final ThreadLocal<EntityManager> entityManager =
            ThreadLocal.withInitial(entityManagerFactory::createEntityManager);

    public static EntityManager getEntityManager() {
        return entityManager.get();
    }

    private Logger logger = LoggerFactory.getLogger(TransactionManager.class);

    private RequestEventListener requestEventListener = new RequestEventListener() {
        @Override
        public void onEvent(RequestEvent event) {
            EntityManager em = getEntityManager();

            ResourceMethod resourceMethod = event.getUriInfo().getMatchedResourceMethod();
            EntityTransaction tx = em.getTransaction();

            switch (event.getType()) {
                case RESOURCE_METHOD_START:
                    try {
                        if (tx.isActive()) {
                            logger.debug("Transaction is active for " + resourceMethod);
                        } else {
                            tx.begin();
                            logger.debug("Started a transaction for " + resourceMethod);
                        }
                    } catch (Exception e) {
                        if (tx.isActive()) {
                            logger.debug("Rolling back the transaction for " + resourceMethod);
                            tx.rollback();
                        }
                        throw e;
                    }
                    break;

                case RESOURCE_METHOD_FINISHED:
                    try {
                        if (tx.isActive()) {
                            tx.commit();
                            logger.debug("Committed the transaction for " + resourceMethod);
                        }
                    } catch (Exception e) {
                        if (tx.isActive()) {
                            logger.debug("Rolling back the transaction for " + resourceMethod);
                            tx.rollback();
                        }
                        throw e;
                    } finally {
                        em.close();
                    }
                    break;
            }
        }
    };

    @Override
    public void onEvent(ApplicationEvent event) {

    }

    @Override
    public RequestEventListener onRequest(RequestEvent requestEvent) {
        return requestEventListener;
    }
}
