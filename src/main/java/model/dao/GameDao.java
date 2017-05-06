package model.dao;

import model.HibernateSessionFactoryHandler;
import model.entity.Game;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;

public class GameDao {

    public static final String Q_GET_ALL_GAMES = "from Game g " +
            "join fetch g.publisher " +
            "join fetch g.categories " +
            "";

    public static final String P_GAME_ID = "id";
    public static final String Q_GET_GAME_BY_ID = "from Game g " +
            "join fetch g.publisher " +
            "join fetch g.categories " +
            "where g.gameId=:" + P_GAME_ID +
            "";


    public List<Game> getAllGames() {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
//        Transaction transaction = null; // Utiliser des noms de variable qui ont du sens => "transaction" au lieu de "t"
        // pas besoin de transaction pour un GET (= select)
        List<Game> games = null;
        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
            /**
             * - Si les "join fetch" ne sont pas présents, alors deux possibilités
             *      - si les attributs sont en FetchType.LAZY
             *          - erreur de type "failed to lazily initialize a collection of role",
             *          c'est à dire qu'il est impossible de charger les relations définies
             *          en FetchType.LAZY
             *      - si les attributs sont en FetchType.EAGER
             *          - les relation définies en FetchType.EAGER vont se charger... mais une
             *          par une...
             *          C'est à dire qu'on sélectionne tous les jeux puis qu'après, pour chaque
             *          jeu, on va aller chercher les relations avec un ou plusieurs select : s'il
             *          y a 1000 jeux, on fera 1000 select pour avoir les 1000 Publishers
             * - Les joins fetch permettent de faire tout cela en une seule requête, c'est à dire
             * une requête SQL classique avec des joins. Le "fetch" est là pour forcer les relations
             * LASY à se charger.
             * - Les relations sont définies en LASY pour éviter le cas où on fait 1000 select sur la
             * base de données parce qu'on a oublié de joindre les entités dont on a besoin : si on oublie,
             * il y aura une erreur.
             */
            Query query = session.createQuery(Q_GET_ALL_GAMES);
//            query.setCacheable(true); // pas de cache, pas demandé et besoin de mieux connaître, peut être plus tard
            games = query.getResultList(); // utilisation de noms de méthodes qui ont du sens (list() a moins de sens que getResultList())
//            transaction.commit();
        } catch (Exception error) {
//            transaction.rollback();
            error.printStackTrace();
        }
        //SESSION IS CLOSED WHEN WE EXIT THE "TRY WITH RESOURCE" => NO NEED FOR FINALLY
        return games;
    }

    public Game getGameById(int id) {
      SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
//      Transaction transaction = null;
      Game game = null;
        try (Session session = sessionFactory.openSession()) {
//            transaction = session.beginTransaction();
//          String hql = "from Game where gameId="+id; // NE JAMAIS FAIRE DE CONCATENATION AVEC "where id = "+id, IL FAUT UTILISER query.setParameter
            Query query = session.createQuery(Q_GET_GAME_BY_ID);
            query.setParameter(P_GAME_ID, id);
            game = (Game) query.uniqueResult();
//            transaction.commit();
        } catch (Exception error) {
//            transaction.rollback();
            error.printStackTrace();
        }
        //SESSION IS CLOSED WHEN WE EXIT THE "TRY WITH RESOURCE" => NO NEED FOR FINALLY
      return game;
    }

    public boolean createGame(/* TODO define parameters */) {
      SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
      Transaction transaction = null;
      boolean flag = false; /*si la creation est success, flag = TRUE*/
      try (Session session = sessionFactory.openSession()) {
          //TODO create game from parameters
      }catch (Exception error) {
          transaction.rollback();
          error.printStackTrace();
      }
      return flag;
    }

    public boolean updateGame(/* TODO define parameters */) {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        Transaction transaction = null;
        boolean flag = false;
        try (Session session = sessionFactory.openSession()) {
            //TODO create game from parameters
        }catch (Exception error) {
            transaction.rollback();
            error.printStackTrace();
        }
        return flag;
    }


}
