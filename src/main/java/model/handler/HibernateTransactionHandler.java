package model.handler;

import model.ModelException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateTransactionHandler {

    private SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
    private Session session;
    private Query query;
    private Transaction transaction;


    public HibernateTransactionHandler openSession() {
        if (session == null)
            session = sessionFactory.openSession();
        return this;
    }

    public HibernateTransactionHandler beginTransaction() {
        if ( transaction == null || !transaction.isActive() )
            transaction = session.beginTransaction();
        return this;
    }

    public HibernateTransactionHandler commit() {
        if (transaction != null && transaction.isActive())
            transaction.commit();
        return this;
    }

    public HibernateTransactionHandler closeSession() {
        if (session != null && session.isOpen())
            session.close();
        return this;
    }

    public HibernateTransactionHandler createQuery(String q) {
        query = session.createQuery(q);
        return this;
    }

    public HibernateTransactionHandler addParameter(String paramName, Object paramValue) {
        if (query == null)
            throw new ModelException("Query must be created before adding any parameter");
        query.setParameter(paramName, paramValue);
        return this;
    }

    public Object getUniqueResultAndClose() {
        Object result = query.uniqueResult();
        closeSession();
        return result;
    }

    public List getResultListAndClose() {
        List result = query.getResultList();
        closeSession();
        return result;
    }

    public HibernateTransactionHandler save(Object o) {
        session.save(o);
        return this;
    }

    public HibernateTransactionHandler saveOrUpdate(Object o) {
        session.saveOrUpdate(o);
        return this;
    }

    public HibernateTransactionHandler delete(Object o) {
        session.delete(o);
        return this;
    }

    public HibernateTransactionHandler executeUpdate() {
        query.executeUpdate();
        return this;
    }
}
