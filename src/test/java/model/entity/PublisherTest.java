package model.entity;

import model.ModelRelationsHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PublisherTest {

    private static final Logger LOGGER = LogManager.getLogger(PublisherTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();
    private Publisher aPublisher;
    private Game aGame;


    @Before
    public void setUp() throws Exception {
        aPublisher = ModelTestingUtilities.createPublisher();
        aGame = ModelTestingUtilities.createGame();
    }

    @Test
    public void testPublisherCreation() throws Exception {
        Session session = sf.getSession();

        session.save(aPublisher);
        session.flush();

        List resultList = session.createQuery("from Publisher ").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aPublisher));
    }


    @Test
    public void testOneToManyGame() throws Exception {

        Session session = sf.getSession();

        LOGGER.info("Mapping relations");
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        LOGGER.info("Saving to session");
        session.saveOrUpdate(aPublisher);
        session.saveOrUpdate(aGame);
        session.flush();

        List resultList = session.createQuery("from Publisher ").getResultList();
        Publisher publisher = (Publisher) resultList.iterator().next();
        Set<Game> games = publisher.getGames();

        assertEquals(1, games.size());
        assertTrue(games.contains(aGame));
    }

}
