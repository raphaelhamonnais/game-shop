package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.Set;

import static model.ModelTestingUtilities.MAPPING_RELATIONS;
import static model.ModelTestingUtilities.SAVING_TO_DATABASE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//TODO
public class PublisherTest {

    private static final Logger LOGGER = LogManager.getLogger(PublisherTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();


    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testPublisherCreation() throws Exception {
        Session session = sf.getSession();

    }


    @Test
    public void testOneToManyGame() throws Exception {

        Session session = sf.getSession();

    }

}
