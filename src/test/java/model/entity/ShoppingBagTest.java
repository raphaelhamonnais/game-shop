package model.entity;

import model.ModelException;
import model.handler.ModelRelationsHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 */
public class ShoppingBagTest {

    private static final Logger LOGGER = LogManager.getLogger(ShoppingBagTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private Users aUser;
    private ShoppingBag aShoppingBag;
    private Address anAddress;
    private Orders anOrder;
    private ShoppingBagRow aShoppingBagRow;
    private Console aConsole;
    private Game aGame;
    private PhysicalGame aPhysicalGame;
    private Publisher aPublisher;

    @Before
    public void setUp() throws Exception {
        aUser = ModelTestingUtilities.createUser();
        aShoppingBag = ModelTestingUtilities.createShoppingBag();
        aShoppingBagRow = ModelTestingUtilities.createShoppingBagRow();
        anAddress = ModelTestingUtilities.createAddress();
        anOrder = ModelTestingUtilities.createOrder();
        aConsole = ModelTestingUtilities.createConsole();
        aGame = ModelTestingUtilities.createGame();
        aPhysicalGame = ModelTestingUtilities.createPhysicalGame();
        aPublisher = ModelTestingUtilities.createPublisher();
    }

    @Test
    public void testShoppingBagCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBag").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aShoppingBag));
    }


    @Test
    public void testManyToOneUser() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBag").getResultList();
        ShoppingBag resultShoppingBag = (ShoppingBag) resultList.iterator().next();
        assertEquals(aUser, resultShoppingBag.getUser());
    }


    @Test
    public void testOneToOneOrder() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aShoppingBag);
        ModelRelationsHandler.mapRelations(anOrder, aUser);
        ModelRelationsHandler.mapRelations(anOrder, anAddress);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, anAddress, anOrder);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBag").getResultList();
        ShoppingBag resultShoppingBag = (ShoppingBag) resultList.iterator().next();
        assertEquals(anOrder, resultShoppingBag.getOrder());
    }

    @Test
    public void testOneToManyShoppingBagRow() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(aShoppingBagRow, aShoppingBag, aPhysicalGame);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, aShoppingBagRow);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBag").getResultList();
        ShoppingBag resultShoppingBag = (ShoppingBag) resultList.iterator().next();
        Set<ShoppingBagRow> shoppingBagRows = resultShoppingBag.getShoppingBagRows();
        assertEquals(1, shoppingBagRows.size());
        assertTrue(shoppingBagRows.contains(aShoppingBagRow));
    }


    @Test
    public void testFailToBindToUserShouldThrowError() throws Exception {
        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(ShoppingBag.ERROR_BINDING_USER_MESSAGE);

        // throw the error
        mapRelationsInTheWrongOrder(aUser, aShoppingBag);
    }


    private void mapRelationsInTheWrongOrder(Users aUser, ShoppingBag aShoppingBag) {
        aUser.addShoppingBag(aShoppingBag);
        aShoppingBag.setUser(aUser);
    }
}