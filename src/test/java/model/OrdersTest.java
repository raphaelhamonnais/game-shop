package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static model.ModelTestingUtilities.MAPPING_RELATIONS;
import static model.ModelTestingUtilities.SAVING_TO_DATABASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 */
public class OrdersTest {

    private static final Logger LOGGER = LogManager.getLogger(OrdersTest.class);


    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

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
    public void testOrderCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aUser);
        ModelRelationsHandler.mapRelations(anOrder, anAddress);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, anAddress, anOrder);

        List resultList = session.createQuery("from Orders").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(anOrder));
    }


    @Test
    public void testOneToOneShoppingBag() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aUser);
        ModelRelationsHandler.mapRelations(anOrder, anAddress);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, anAddress, anOrder);

        List resultList = session.createQuery("from Orders").getResultList();
        Orders resultShoppingBagRow = (Orders) resultList.iterator().next();
        assertEquals(aShoppingBag, resultShoppingBagRow.getShoppingBag());
    }


    @Test
    public void testManyToOneUser() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aUser);
        ModelRelationsHandler.mapRelations(anOrder, anAddress);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, anAddress, anOrder);

        List resultList = session.createQuery("from Orders").getResultList();
        Orders resultShoppingBagRow = (Orders) resultList.iterator().next();
        assertEquals(aUser, resultShoppingBagRow.getUser());
    }

    @Test
    public void testManyToOneAddress() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aUser);
        ModelRelationsHandler.mapRelations(anOrder, anAddress);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, anAddress, anOrder);

        List resultList = session.createQuery("from Orders").getResultList();
        Orders resultShoppingBagRow = (Orders) resultList.iterator().next();
        assertEquals(anAddress, resultShoppingBagRow.getAddress());
    }

    @Test
    public void testFailToBindToShoppingBagShouldThrowError() throws Exception {
        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(Orders.ERROR_BINDING_TO_SHOPPING_BAG_MESSAGE);

        // throw the error
        aUser.addOrder(anOrder);
    }
}