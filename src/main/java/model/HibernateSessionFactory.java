package model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {

    private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            createHibernateSessionFactory();
        return sessionFactory;
    }

    private static void createHibernateSessionFactory() {
        Configuration hibernateConf = new Configuration();
        hibernateConf.configure(HIBERNATE_CFG_XML);
        sessionFactory = hibernateConf.buildSessionFactory();
    }

    @Override
    protected void finalize() throws Throwable {
        sessionFactory.close();
        super.finalize();
    }
}
