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
public class GameTest {

    private static final Logger LOGGER = LogManager.getLogger(GameTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private Console aConsole;
    private Game aGame;
    private PhysicalGame aPhysicalGame;
    private Publisher aPublisher;


    @Before
    public void setUp() throws Exception {
        // Creating test objects
        aConsole = ModelTestingUtilities.createConsole();
        aGame = ModelTestingUtilities.createGame();
        aPhysicalGame = ModelTestingUtilities.createPhysicalGame();
        aPublisher = ModelTestingUtilities.createPublisher();
    }

    @Test
    public void testGameCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        session.save(aGame);
        session.flush();

        List resultList = session.createQuery("from Game").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aGame));
    }


    @Test
    public void testOneToManyPhysicalGame() throws Exception {

        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);

        // Getting the elements back from the database
        List resultList = session.createQuery("from Game ").getResultList();
        Game resultGame = (Game) resultList.iterator().next();

        Set<PhysicalGame> physicalGames = resultGame.getPhysicalGames();
        assertEquals(1, physicalGames.size());
        assertTrue(physicalGames.contains(aPhysicalGame));
    }

    @Test
    public void failToBindToPublisherShouldThrowException() throws Exception {
        expectedEx.expect(ModelException.class);
        expectedEx.expectMessage(Game.ERROR_BINDING_GAME_TO_PUBLISHER);
        aPublisher.addGame(aGame);
    }
}
