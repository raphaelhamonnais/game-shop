package model.entity;

import model.handler.ModelRelationsHandler;
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
public class CategoryGameTest {

    private static final Logger LOGGER = LogManager.getLogger(CategoryGameTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();
    private Game aGame;
    private Category aCategory;
    private Publisher aPublisher;

    @Before
    public void setUp() throws Exception {
        aGame = ModelTestingUtilities.createGame();
        aCategory = ModelTestingUtilities.createCategory();
        aPublisher = ModelTestingUtilities.createPublisher();
    }

    @Test
    public void testManyToManyCategoryGame() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aGame, aPublisher);
        ModelRelationsHandler.mapRelations(aGame, aCategory);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aPublisher);
        ModelTestingUtilities.saveToSession(session, aGame, aCategory);

        List catList = session.createQuery("from Category").list();
        List gameList = session.createQuery("from Game").list();
        assertEquals(1, catList.size());
        assertEquals(1, gameList.size());
        assertTrue(catList.contains(aCategory));
        assertTrue(gameList.contains(aGame));

        Category resultCat = (Category) catList.iterator().next();
        Game resultGame = (Game) gameList.iterator().next();
        // category has one game that is the game we just inserted into the DB
        assertEquals(1, resultCat.getGames().size());
        assertTrue(resultCat.getGames().contains(aGame));
        // game has one category that is the category we just inserted into the DB
        assertEquals(1, resultGame.getCategories().size());
        assertTrue(resultGame.getCategories().contains(aCategory));
    }
}
