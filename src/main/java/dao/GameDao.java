package dao;

import model.Game;
import model.HibernateSessionFactory;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by lenovo on 2017/5/4.
 */
public class GameDao {

    /*obtenir tous les jeu*/
    public List<Game> getAllGame() {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        Session s = null;
        Transaction t = null;
        List<Game> games = null;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            String hql = "select * from game";
            Query query = s.createNamedQuery(hql, Game.class);
            query.setCacheable(true);
            games = query.list();
            t.commit();
        }catch (Exception error) {
            t.rollback();
            error.printStackTrace();
        } finally {
            s.close();
        }
        return games;
    }

    /*obtenir le jeu selon l'identifiant*/
    public Game getGameById(int id) {
      SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
      Session s = null;
      Transaction t = null;
      Game game = null;
      try {
          s = sessionFactory.openSession();
          t = s.beginTransaction();
          String hql = "from Game where gameId="+id;
          Query query = s.createQuery(hql);
          game = (Game) query.uniqueResult();
          t.commit();
      }catch (Exception error) {
          t.rollback();
          error.printStackTrace();
      } finally {
          s.close();
      }
      return game;
  }

  /*creer un jeu*/
  public boolean createGame(Game game) {
      SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
      Session s = null;
      Transaction t = null;
      boolean flag = false; /*si la creation est success, flag = TRUE*/
      try {
          s = sessionFactory.openSession();
          t = s.beginTransaction();
          s.save(game);
          t.commit();
          flag = true;
      }catch (Exception error) {
          t.rollback();
          error.printStackTrace();
      } finally {
          s.close();
      }
      return flag;
  }

  /*mis a jour le jeu*/
    public boolean updateGame(Game game) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        Session s = null;
        Transaction t = null;
        boolean flag = false;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            s.update(game);
            t.commit();
            flag = true;
        }catch (Exception error) {
            t.rollback();
            error.printStackTrace();
        } finally {
            s.close();
        }
        return flag;
    }

    /*supprimer le jeu*/
    public boolean deleteGameById(int id) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        Session s = null;
        Transaction t = null;
        boolean flag = false;
        try {
            s = sessionFactory.openSession();
            t = s.beginTransaction();
            Game game = new Game();
            game.setGameId(id);
            s.delete(game);
            t.commit();
            flag = true;
        }catch (Exception error) {
            t.rollback();
            error.printStackTrace();
        } finally {
            s.close();
        }
        return flag;
    }


}
