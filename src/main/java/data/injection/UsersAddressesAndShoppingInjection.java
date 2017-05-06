package data.injection;

import model.*;
import model.entity.*;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class UsersAddressesAndShoppingInjection extends DataInjection {

    private static final Logger LOGGER = LogManager.getLogger(UsersAddressesAndShoppingInjection.class);
    private static final String USERS_AND_ADDRESS_FULL_DATA_SET = "data-sets/users-data-set.csv";


    private static final String USERNAME_CSV_HEADER = "Username";
    private static final String EMAIL_ADDRESS_CSV_HEADER = "EmailAddress";
    private static final String PASSWORD_CSV_HEADER = "Password";
    private static final String FIRST_NAME_NEW_CSV_HEADER = "FirstName";
    private static final String LAST_NEW_CSV_HEADER = "LastName";
    private static final String TELEPHONE_NUMBER_CSV_HEADER = "TelephoneNumber";
    private static final String STREET_ADDRESS_CSV_HEADER = "StreetAddress";
    private static final String ZIP_CODE_CSV_HEADER = "ZipCode";
    private static final String CITY_CSV_HEADER = "City";
    private static final String COUNTRY_FULL_NEW_CSV_HEADER = "Country";

    private static final int MAX_ORDERS = 4;
    private static final int MAX_ITEMS = 15;
    private static final int MAX_UNITS_BY_ITEM = 4;
    private static final int MAX_SHOPPING_BAG = 1;


    private Map<Integer, Users> usersMap = new HashMap<>();
    private Map<Integer, Address> addressMap = new HashMap<>();
    private Map<Integer, Orders> ordersMap = new HashMap<>();
    private Map<Integer, ShoppingBag> shoppingBagMap = new HashMap<>();


    public UsersAddressesAndShoppingInjection() {
        super();
    }

    @Override
    protected String getDataFilePaht() {
        return this.getClass().getClassLoader().getResource(USERS_AND_ADDRESS_FULL_DATA_SET).getFile();
    }

    @Override
    protected void createDataFromCsv() {
        createUsersAndAddresses();
        createOrders();
    }

    private void createUsersAndAddresses() {
        // creating maps of categories, consoles and publishers
        LOGGER.info("Creating users and addresses");
        int lineNumber = 1;
        for (CSVRecord record : records) {
            if (lineNumber % 1000 == 0)
                LOGGER.info("Parsing CSV file (current lineNumber = " + lineNumber + ")");

            String login = record.get(USERNAME_CSV_HEADER);
            String email = record.get(EMAIL_ADDRESS_CSV_HEADER);
            String password = record.get(PASSWORD_CSV_HEADER);
            String firstName = record.get(FIRST_NAME_NEW_CSV_HEADER);
            String lastName = record.get(LAST_NEW_CSV_HEADER);
            String tel = record.get(TELEPHONE_NUMBER_CSV_HEADER);
            String streetAddress = record.get(STREET_ADDRESS_CSV_HEADER);
            String zipCode = record.get(ZIP_CODE_CSV_HEADER);
            String city = record.get(CITY_CSV_HEADER);
            String country = record.get(COUNTRY_FULL_NEW_CSV_HEADER);


            LOGGER.info("Create user");
            Users aUser = new Users(login, email, password, lastName, firstName, tel);
            LOGGER.info("Create or retrieve address");
            Address anAddress = new Address("Default", streetAddress, city, country, zipCode);
            if (addressMap.containsKey(anAddress.hashCode()))
                anAddress = addressMap.get(anAddress.hashCode());
            LOGGER.info("Mapping user and address");
            ModelRelationsHandler.mapRelations(aUser, anAddress);

            LOGGER.info("Saving user to HashMap");
            usersMap.put(aUser.hashCode(), aUser);
            LOGGER.info("Saving address to HashMap");
            addressMap.put(anAddress.hashCode(), anAddress);

            lineNumber++;
        }
    }


    private void createOrders() {
        LOGGER.info("Creating orders");
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Session session = sessionFactory.openSession();
        List physicalGames = session.createQuery("from PhysicalGame pg " +
                "join fetch pg.game " +
                "join fetch pg.game.publisher " +
                "join fetch pg.game.categories " +
                "join fetch pg.console " +
                "left outer join fetch pg.shoppingBagRows")
                .getResultList();
        int physicalGamesSize = physicalGames.size();

        // create orders and shopping bags for all users
        for (Users user : usersMap.values()) {
            LOGGER.info("Creating orders for a specific user");
            // create orders for a user
            int nbPastOrders = random.nextInt(MAX_ORDERS + 1);
            int hasPendingShoppingBag = random.nextInt(MAX_SHOPPING_BAG + 1);
            for (int i = 0; i < nbPastOrders + hasPendingShoppingBag; i++) {
                LOGGER.info("Creating a shopping bag");
                ShoppingBag bag = new ShoppingBag(user);
                long randomLong = random.nextLong(50000000L);
                LOGGER.info("randomLong = " + randomLong);
                bag.setCreationDate(new Date(new Date().getTime() - randomLong));
                int nbItemsForThisOrder = random.nextInt(MAX_ITEMS);

                // adding items to the shopping bag
                for (int j = 0; j < nbItemsForThisOrder; j++) {
                    int randomIndex = random.nextInt(physicalGamesSize);
                    int randomUnits = random.nextInt(MAX_UNITS_BY_ITEM);
                    LOGGER.info("Getting physical game from list");
                    PhysicalGame physicalGame = (PhysicalGame) physicalGames.get(randomIndex);
                    LOGGER.info("Creating a shopping bag row");
                    ShoppingBagRow row = new ShoppingBagRow(physicalGame, bag, randomUnits);
                    LOGGER.info("Mapping relation between row, bag and physical game");
                    ModelRelationsHandler.mapRelations(row, bag, physicalGame);
                }

                shoppingBagMap.put(bag.hashCode(), bag);

                // creating order only within [0;nbPastOrders[
                if (i < nbPastOrders) {
                    // creating the order from the shopping bag
                    LOGGER.info("Getting address from user");
                    Address deliveryAddress = user.getAddresses().iterator().next();
                    LOGGER.info("Creating order");
                    Orders order = new Orders(bag, deliveryAddress);
                    LOGGER.info("Mapping relations between order and bag");
                    ModelRelationsHandler.mapRelations(order, bag);
                    LOGGER.info("Mapping relations between order and user");
                    ModelRelationsHandler.mapRelations(order, user);
                    LOGGER.info("Mapping relations between order and address");
                    ModelRelationsHandler.mapRelations(order, deliveryAddress);
                    ordersMap.put(order.hashCode(), order);
                } // else will create a pending shopping bag for the user
            }
        }
    }


    @Override
    protected void saveCreatedDataToSession(Session session) {

        LOGGER.info("Saving users");
        for (Users user : usersMap.values())
            session.saveOrUpdate(user);

        LOGGER.info("Saving addresses");
        for (Address address : addressMap.values())
            session.saveOrUpdate(address);

        LOGGER.info("Saving shopping bags");
        for (ShoppingBag bag : shoppingBagMap.values())
            session.saveOrUpdate(bag);

        LOGGER.info("Saving orders");
        for (Orders order : ordersMap.values())
            session.saveOrUpdate(order);
    }
}
