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
public class ShoppingBagRowTest {

    private static final Logger LOGGER = LogManager.getLogger(ShoppingBagRowTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private Console aConsole;
    private Game aGame;
    private PhysicalGame aPhysicalGame;
    private Users aUser;
    private ShoppingBag aShoppingBag;
    private ShoppingBagRow aShoppingBagRow;
    private Publisher aPublisher;


    @Before
    public void setUp() throws Exception {
        aConsole = ModelTestingUtilities.createConsole();
        aGame = ModelTestingUtilities.createGame();
        aPhysicalGame = ModelTestingUtilities.createPhysicalGame();
        aUser = ModelTestingUtilities.createUser();
        aShoppingBag = ModelTestingUtilities.createShoppingBag();
        aShoppingBagRow = ModelTestingUtilities.createShoppingBagRow();
        aPublisher = ModelTestingUtilities.createPublisher();
    }


    @Test
    public void testShoppingBagRowCreation() {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(aShoppingBagRow, aShoppingBag, aPhysicalGame);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, aShoppingBagRow);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBagRow ").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aShoppingBagRow));
    }

    @Test
    public void testManyToOneShoppingBag() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(aShoppingBagRow, aShoppingBag, aPhysicalGame);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, aShoppingBagRow);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBagRow ").getResultList();
        ShoppingBagRow resultShoppingBagRow = (ShoppingBagRow) resultList.iterator().next();
        assertEquals(aShoppingBag, resultShoppingBagRow.getShoppingBag());
    }

    @Test
    public void testManyToOnePhysicalGame() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);
        ModelRelationsHandler.mapRelations(aUser, aShoppingBag);
        ModelRelationsHandler.mapRelations(aShoppingBagRow, aShoppingBag, aPhysicalGame);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);
        ModelTestingUtilities.saveToSession(session, aUser, aShoppingBag, aShoppingBagRow);

        // Getting the elements back from the database
        List resultList = session.createQuery("from ShoppingBagRow ").getResultList();
        ShoppingBagRow resultShoppingBagRow = (ShoppingBagRow) resultList.iterator().next();
        assertEquals(aPhysicalGame, resultShoppingBagRow.getPhysicalGame());
    }

    @Test
    public void testFailToBindToShoppingBagShouldThrowError() throws Exception {
        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(ShoppingBagRow.ERROR_BINDING_TO_SHOPPING_BAG_MESSAGE);

        // throw the error
        aShoppingBag.addShoppingBagRow(aShoppingBagRow);
    }

    @Test
    public void testFailToBindToPhysicalGameShouldThrowError() throws Exception {
        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(ShoppingBagRow.ERROR_BINDING_TO_PHYSICAL_GAME_MESSAGE);
        aShoppingBagRow.setShoppingBag(aShoppingBag);

        // throw the error
        aPhysicalGame.addShoppingBagRow(aShoppingBagRow);
    }
}