package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class UsersRolesTest {

    private static final Logger LOGGER = LogManager.getLogger(UsersRolesTest.class);


    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testManyToManyUsersRoles() throws Exception {
        //TODO
    }
}
