package model.entity;

import model.ModelRelationsHandler;
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
public class UsersTest {

    private static final Logger LOGGER = LogManager.getLogger(UsersTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    private Users aUser;
    private ShoppingBag aShoppingBag;
    private Address anAddress;
    private Orders anOrder;


    @Before
    public void setUp() throws Exception {
        aUser = ModelTestingUtilities.createUser();
        aShoppingBag = ModelTestingUtilities.createShoppingBag();
        anAddress = ModelTestingUtilities.createAddress();
        anOrder = ModelTestingUtilities.createOrder();
    }


    @Test
    public void testUserCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        session.save(aUser);
        session.flush();

        List resultList = session.createQuery("from Users").list();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aUser));
    }


    @Test
    public void testOneToManyShoppingBags() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag);

        List resultList = session.createQuery("from Users").list();
        Users resultUser = (Users) resultList.iterator().next();
        assertEquals(1, resultUser.getShoppingBags().size());
        assertTrue(resultUser.getShoppingBags().contains(aShoppingBag));
    }

    @Test
    public void testOneToManyOrders() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aUser);
        ModelRelationsHandler.mapRelations(anOrder, anAddress);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, anAddress, anOrder);

        List resultList = session.createQuery("from Users ").getResultList();
        Users resultUser = (Users) resultList.iterator().next();
        assertEquals(1, resultUser.getOrders().size());
        assertTrue(resultUser.getOrders().contains(anOrder));
    }
}