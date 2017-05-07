package model.handler;

import model.ModelException;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Stream;

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
        checkQueryInitialized();
        query.setParameter(paramName, paramValue);
        return this;
    }

    public HibernateTransactionHandler setFirstResult(int start) {
        checkQueryInitialized();
        query.setFirstResult(start);
        return this;
    }

    public HibernateTransactionHandler setMaxResults(int max) {
        checkQueryInitialized();
        query.setMaxResults(max);
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

    public Stream<?> stream() {
        return query.stream();
    }

    public ScrollableResults scrollForward() {
        return query.scroll(ScrollMode.FORWARD_ONLY);
    }


    private void checkQueryInitialized() {
        if (query == null)
            throw new ModelException("Query must be created before adding any parameter");
    }


//    @Override
//    protected void finalize() throws Throwable {
//        commit();
//        closeSession();
//        super.finalize();
//    }
}
