package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Set;

import static model.ModelTestingUtilities.MAPPING_RELATIONS;
import static model.ModelTestingUtilities.SAVING_TO_DATABASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 */
public class PhysicalGameTest {



    private static final Logger LOGGER = LogManager.getLogger(PhysicalGameTest.class);

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
        // Creating test objects
        aConsole = ModelTestingUtilities.createConsole();
        aGame = ModelTestingUtilities.createGame();
        aPhysicalGame = ModelTestingUtilities.createPhysicalGame();
        aShoppingBag = ModelTestingUtilities.createShoppingBag();
        aUser = ModelTestingUtilities.createUser();
        aShoppingBagRow = ModelTestingUtilities.createShoppingBagRow();
        aPublisher = ModelTestingUtilities.createPublisher();
    }

    @Test
    public void testPhysicalGameCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);

        List resultList = session.createQuery("from PhysicalGame ").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aPhysicalGame));
    }


    @Test
    public void testManyToOneGame() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);

        List resultList = session.createQuery("from PhysicalGame ").getResultList();
        PhysicalGame resultPhysicalGame = (PhysicalGame) resultList.iterator().next();
        assertEquals(aGame, resultPhysicalGame.getGame());
    }


    @Test
    public void testManyToOneConsole() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);

        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);

        List resultList = session.createQuery("from PhysicalGame ").getResultList();
        PhysicalGame resultPhysicalGame = (PhysicalGame) resultList.iterator().next();
        assertEquals(aConsole, resultPhysicalGame.getConsole());
    }

    @Test
    public void testOneToManyShoppingBagRows() throws Exception {
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
        List resultList = session.createQuery("from PhysicalGame ").getResultList();
        PhysicalGame resultPhysicalGame = (PhysicalGame) resultList.iterator().next();
        Set<ShoppingBagRow> shoppingBagRows = resultPhysicalGame.getShoppingBagRows();
        assertEquals(1, shoppingBagRows.size());
        assertTrue(shoppingBagRows.contains(aShoppingBagRow));
    }


    @Test
    public void failToBindToGameShouldThrowError() throws Exception {
        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(PhysicalGame.ERROR_BINDING_GAME_MESSAGE);

        // throw the error
        aGame.addPhysicalGame(aPhysicalGame);
    }


    @Test
    public void failToBindToConsoleShouldThrowError() throws Exception {
        aPhysicalGame.setGame(aGame);

        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(PhysicalGame.ERROR_BINDING_CONSOLE_MESSAGE);

        // throw the error
        aConsole.addPhysicalGame(aPhysicalGame);
    }
}