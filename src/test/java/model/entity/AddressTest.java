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
public class AddressTest {

    private static final Logger LOGGER = LogManager.getLogger(AddressTest.class);

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
    public void testAddressCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        session.save(anAddress);
        session.flush();

        List resultList = session.createQuery("from Address").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(anAddress));
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

        List resultList = session.createQuery("from Address").getResultList();
        Address resultAddress = (Address) resultList.iterator().next();
        assertTrue(resultAddress.getOrders().contains(anOrder));
    }
}