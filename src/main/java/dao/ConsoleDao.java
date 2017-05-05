package dao;

import model.Console;
import model.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ConsoleDao {

    public static final String Q_GET_ALL_CONSOLES = "from Console con " +
            "join fetch con.physicalGames " +
            "";
    public static final String P_CONSOLE_ID = "id";
    public static final String Q_GET_CONSOLE_BY_ID = "from Console con " +
            "join fetch con.physicalGames " +
            "where con.consoleId=:"+ P_CONSOLE_ID +
            "";

    public List<Console> getAllConsoles() {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        List<Console> consoles = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_ALL_CONSOLES);
            consoles = query.getResultList();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return consoles;
    }

    public Console getConsoleId(int id) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        Console console = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_CONSOLE_BY_ID);
            query.setParameter(P_CONSOLE_ID, id);
            console = (Console) query.uniqueResult();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return console;
    }
}
