package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static model.ModelTestingUtilities.MAPPING_RELATIONS;
import static model.ModelTestingUtilities.SAVING_TO_DATABASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**

 */
public class ConsoleTest {

    private static final Logger LOGGER = LogManager.getLogger(ConsoleTest.class);


    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

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
    public void testConsoleCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(SAVING_TO_DATABASE);
        session.save(aConsole);
        session.flush();

        List resultList = session.createQuery("from Console").list();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aConsole));
    }


    @Test
    public void testOneToManyPhysicalGame() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aConsole, aPhysicalGame);


        LOGGER.info(SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aConsole, aGame, aPhysicalGame);

        // Getting the elements back from the database
        List resultList = session.createQuery("from Console ").getResultList();
        Console resultConsole = (Console) resultList.iterator().next();

        assertEquals(1, resultConsole.getPhysicalGames().size());
        assertTrue(resultConsole.getPhysicalGames().contains(aPhysicalGame));
    }
}