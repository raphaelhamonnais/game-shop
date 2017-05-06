package model.entity;

import model.handler.ModelRelationsHandler;
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

/**

 */
public class UsersAddressesTest {

    private static final Logger LOGGER = LogManager.getLogger(UsersAddressesTest.class);


    @Rule
    public final SessionFactoryTestRule sf = new SessionFactoryTestRule();
    private Address anAddress;
    private Users aUser;


    @Before
    public void setUp() throws Exception {
        anAddress = ModelTestingUtilities.createAddress();
        aUser = ModelTestingUtilities.createUser();
    }

    @Test
    public void testManyToManyUsersAddress() throws Exception {
        Session session = sf.getSession();

        LOGGER.info(ModelTestingUtilities.MAPPING_RELATIONS);
        ModelRelationsHandler.mapRelations(aUser, anAddress);

        LOGGER.info(ModelTestingUtilities.SAVING_TO_DATABASE);
        ModelTestingUtilities.saveToSession(session, aUser, anAddress);


        // Getting the elements back from the database
        List resultAddresses = session.createQuery("from Address").getResultList();
        assertEquals(1, resultAddresses.size());
        assertTrue(resultAddresses.contains(anAddress));

        List resultUsers = session.createQuery("from Users").getResultList();
        assertEquals(1, resultUsers.size());
        assertTrue(resultUsers.contains(aUser));

        Address adr = (Address) resultAddresses.iterator().next();
        Users usr = (Users) resultUsers.iterator().next();

        Set<Address> addressesForUser = usr.getAddresses();
        assertEquals(1, addressesForUser.size());
        assertTrue(addressesForUser.contains(anAddress));

        Set<Users> usersForAddress = adr.getUsers();
        assertEquals(1, usersForAddress.size());
        assertTrue(usersForAddress.contains(aUser));
    }
}