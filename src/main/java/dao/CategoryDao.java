package dao;

import model.Category;
import model.HibernateSessionFactory;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDao {
    public static final String Q_GET_ALL_CATEGORIES = "from Category cat group by cat.catId " +
           // "join fetch cat.games" +  /*if we want to see the category for each game, we will use this line of code and we get ride of group by*/
            "";
    public static final String P_CATEGORY_NAME = "name";
    public static final String Q_GET_CATEGORY_BY_NAME = "from Category cat " +
            "join fetch cat.games " +
            "where cat.catName=:"+ P_CATEGORY_NAME +
            "";

    public List<Category> getAllCategories() {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
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
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
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
}
