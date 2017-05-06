package model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 */
public class CategoryTest {


    private static final Logger LOGGER = LogManager.getLogger(CategoryTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();
    private Category category;


    @Before
    public void setUp() throws Exception {
        category = ModelTestingUtilities.createCategory();
    }

    @Test
    public void testCategoryCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        session.save(category);
        session.flush();

        List resultList = session.createQuery("from Category").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(category));
    }
}