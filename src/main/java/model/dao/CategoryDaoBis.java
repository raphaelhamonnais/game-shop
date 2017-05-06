package model.dao;

import model.HibernateSessionFactoryHandler;
import model.entity.Category;
import model.entity.Game;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CategoryDaoBis {
    public static final String Q_GET_ALL_CATEGORIES = "from Category cat" +
            "";
    public static final String P_CATEGORY_NAME = "name";

    public static final String Q_GET_CATEGORY_BY_NAME = "from Category cat " +
            "where cat.catName=:"+ P_CATEGORY_NAME +
            "";
    public static final String Q_GET_CATEGORY_GAMES = "from Category cat " +
            "join fetch cat.games g " +
            "join fetch g.publisher " + // a game need the publisher
            "join fetch g.categories " + // a game need to print its gategories (it can have multiple categories)
            "where cat.catName=:"+ P_CATEGORY_NAME +
            "";

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

    public List<Game> getGames(String name) {
        SessionFactory sessionFactory = HibernateSessionFactoryHandler.getSessionFactory();
        Category category = null;
        List<Game> games = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_CATEGORY_GAMES);
            query.setParameter(P_CATEGORY_NAME, name);
            category = (Category) query.uniqueResult();
            games = new ArrayList<>(category.getGames());
        } catch (Exception error) {
            error.printStackTrace();
        }
        return games;
    }
}
