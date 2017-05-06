package model.entity;

import model.ModelRelationsHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersRolesTest {

    private static final Logger LOGGER = LogManager.getLogger(UsersRolesTest.class);

    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();

    private Users aUser;
    private Roles aRole;

    @Before
    public void setUp() throws Exception {
        aUser = ModelTestingUtilities.createUser();
        aRole = ModelTestingUtilities.createRole();
    }


    @Test
    public void testManyToManyUsersRoles() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, aRole);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        session.saveOrUpdate(aRole);
        session.saveOrUpdate(aUser);
        session.flush();

        List usersList = session.createQuery("from Users ").getResultList();
        List rolesList = session.createQuery("from Roles ").getResultList();
        assertEquals(1, usersList.size());
        assertEquals(1, rolesList.size());
        assertTrue(usersList.contains(aUser));
        assertTrue(rolesList.contains(aRole));

        Users resultUser = (Users) usersList.iterator().next();
        assertEquals(1, resultUser.getRoles().size());
        assertTrue(resultUser.getRoles().contains(aRole));

        Roles resultRole = (Roles) rolesList.iterator().next();
        assertEquals(1, resultRole.getUsers().size());
        assertTrue(resultRole.getUsers().contains(aUser));
    }
}
