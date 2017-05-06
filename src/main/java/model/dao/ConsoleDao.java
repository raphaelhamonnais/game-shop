package model.dao;

import model.HibernateSessionFactoryHandler;
import model.entity.Console;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ConsoleDao {

    public static final String Q_GET_ALL_CONSOLES = "from Console con " +
            "join fetch con.physicalGames " +  /*if we want to see the console for each game, we will use this line of code and we get ride of group by*/
            "";
    public static final String P_CONSOLE_NAME = "name";
    public static final String Q_GET_CONSOLE_BY_NAME = "from Console con " +
            "join fetch con.physicalGames " +
            "where con.consoleName=:"+ P_CONSOLE_NAME +
            "";

    public List<Console> getAllConsoles() {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        List<Console> consoles = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_ALL_CONSOLES);
            consoles = query.getResultList();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return consoles;
    }

    public Console getConsoleByName(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        Console console = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_CONSOLE_BY_NAME);
            query.setParameter(P_CONSOLE_NAME, name);
            console = (Console) query.uniqueResult();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return console;
    }
}
