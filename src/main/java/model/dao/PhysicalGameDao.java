package model.dao;

import model.handler.HibernateSessionFactoryHandler;
import model.entity.PhysicalGame;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class PhysicalGameDao {

    public static final String Q_GET_ALL_PHYSICAL_GAMES = "from PhysicalGame pg " +
            "join fetch pg.game " +
            "join fetch pg.console " +
            "";
    public static final String P_PHYSICAL_GAME_ID = "id";
    public static final String Q_GET_PHYSICAL_GAME_BY_ID = "from PhysicalGame pg " +
            "join fetch pg.game " +
            "join fetch pg.console " +
            "where pg.physicalGameId=:"+ P_PHYSICAL_GAME_ID +
            "";

    public List<PhysicalGame> getAllPhysicalGames() {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        List<PhysicalGame> physical_games = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_ALL_PHYSICAL_GAMES);
            physical_games = query.getResultList();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return physical_games;
    }

    public PhysicalGame getPhysicalGameById(int id) {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        PhysicalGame physical_game = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_PHYSICAL_GAME_BY_ID);
            query.setParameter(P_PHYSICAL_GAME_ID, id);
            physical_game = (PhysicalGame) query.uniqueResult();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return physical_game;
    }
}
