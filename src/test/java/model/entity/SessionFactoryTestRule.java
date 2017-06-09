package model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/**

 */
public class SessionFactoryTestRule implements MethodRule {
    private static final Logger LOGGER = LogManager.getLogger(SessionFactoryTestRule.class);

    private static final String HIBERNATE_CONNECTION_URL = "jdbc:mysql://localhost:3306/sr03_web_project_test?serverTimezone=UTC";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String HIBERNATE_CONNECTION_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String HIBERNATE_CONNECTION_USERNAME = "root";
    private static final String HIBERNATE_CONNECTION_PASSWORD = "root";

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;


    @Override
    public Statement apply(final Statement statement, FrameworkMethod frameworkMethod, Object o) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                sessionFactory = createSessionFactory();
                createSession();
                beginTransaction();
                try {
                    statement.evaluate();
                } finally {
                    shutdown();
                }
            }
        };
    }




    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Console.class)
                .addAnnotatedClass(Game.class)
                .addAnnotatedClass(Orders.class)
                .addAnnotatedClass(PhysicalGame.class)
                .addAnnotatedClass(Publisher.class)
                .addAnnotatedClass(Roles.class)
                .addAnnotatedClass(ShoppingBag.class)
                .addAnnotatedClass(ShoppingBagRow.class)
                .addAnnotatedClass(Users.class)
        ;

        configuration.setProperty("hibernate.connection.url", HIBERNATE_CONNECTION_URL);
        configuration.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        configuration.setProperty("hibernate.connection.driver_class", HIBERNATE_CONNECTION_DRIVER_CLASS);
        configuration.setProperty("hibernate.connection.username", HIBERNATE_CONNECTION_USERNAME);
        configuration.setProperty("hibernate.connection.password", HIBERNATE_CONNECTION_PASSWORD);
        configuration.setProperty("hibernate.show_sql", "true");

        sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

    public Session createSession() {
        session = sessionFactory.openSession();
        return session;
    }

    public void beginTransaction() {
        transaction = session.beginTransaction();
    }

    public void commit() {
        transaction.commit();
    }

    public Session getSession() {
        return session;
    }



    private void shutdown() {
        try {
            try {
                try {
                    LOGGER.info("Try rollback");
                    transaction.rollback();
                    LOGGER.info("Rollback succesful");
                } catch (Exception ex) {
                    LOGGER.error("Rollback impossible");
                    ex.printStackTrace();
                }
                LOGGER.info("Closing session");
                session.close();
                LOGGER.info("Session closed");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            LOGGER.info("Closing session factory");
            sessionFactory.close();
            LOGGER.info("Session factory closed");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
