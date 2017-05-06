package model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RolesTest {

    private static final Logger LOGGER = LogManager.getLogger(RolesTest.class);


    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();
    private Roles aRole;


    @Before
    public void setUp() throws Exception {
        aRole = ModelTestingUtilities.createRole();
    }

    @Test
    public void testRoleCreation() throws Exception {
        Session session = sf.getSession();

        LOGGER.info("Saving to session");
        session.save(aRole);
        session.flush();

        List resultList = session.createQuery("from Roles ").getResultList();
        assertEquals(1, resultList.size());
        assertTrue(resultList.contains(aRole));
    }
}
