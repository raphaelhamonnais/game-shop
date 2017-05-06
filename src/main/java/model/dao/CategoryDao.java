package model.dao;

import model.handler.HibernateSessionFactoryHandler;
import model.entity.Category;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDao {
    public static final String Q_GET_ALL_CATEGORIES = "from Category cat " +
            "join fetch cat.games " +  /*if we want to see the category for each game, we will use this line of code and we get ride of group by*/
            "";
    public static final String P_CATEGORY_NAME = "name";
    public static final String Q_GET_CATEGORY_BY_NAME = "from Category cat " +
            "join fetch cat.games " +
            "where cat.catName=:"+ P_CATEGORY_NAME +
            "";
   /* public static final String Q_GET_GAME_FROM_CATEGORY = "from Category cat " +
            "join fetch cat.games " +
            "where cat.catName=:"+ P_CATEGORY_NAME +
            "";*/

    public List<Category> getAllCategories() {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        List<Category> categories = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_ALL_CATEGORIES);
            categories = query.getResultList();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return categories;
    }

    public Category getCategoryByName(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        Category category = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_CATEGORY_BY_NAME);
            query.setParameter(P_CATEGORY_NAME, name);
            category = (Category) query.uniqueResult();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return category;
    }

/*    public List<Game> getGamesFromCategory() {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        List<Game> category_games = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_GAME_FROM_CATEGORY);
            category_games = query.getResultList();
        } catch (Exception error) {
            error.printStackTrace();
        }

        return category_games;
    }*/
}
