package dao;

import model.Category;
import model.HibernateSessionFactory;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.util.List;

public class CategoryDao {
    public static final String Q_GET_ALL_CATEGORIES = "from Category cat " +
            "join fetch cat.games" +
           //"join fetch cat.physicalGame" +
            "";
    public static final String P_CATEGORY_ID = "id";
    public static final String Q_GET_CATEGORY_BY_ID = "from Category cat " +
            "join fetch cat.games " +
           // "join fetch cat.physicalGame " +
            "where cat.catId=:"+ P_CATEGORY_ID +
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

    public Category getCategoryId(int id) {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        Category category = null;

        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(Q_GET_CATEGORY_BY_ID);
            query.setParameter(P_CATEGORY_ID, id);
            category = (Category) query.uniqueResult();
        } catch (Exception error) {
            error.printStackTrace();
        }
        return category;
    }
}
