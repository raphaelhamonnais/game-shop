package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

import java.math.BigDecimal;

/**

 */
public class ModelTestingUtilities {

    private static final Logger LOGGER = LogManager.getLogger(ModelTestingUtilities.class);

    public static final String MAPPING_RELATIONS = "Mapping relations";
    public static final String SAVING_TO_DATABASE = "Saving to database";

    public static Address createAddress() {
        Address address = new Address();
        address.setAdrName("address name");
        address.setAdrCity("Paris");
        address.setAdrStreet("136 rue saint honoré");
        address.setAdrZipCode("75001");
        address.setAdrCountry("France");
        return address;
    }

    public static Users createUser() {
        Users user = new Users();
        user.setUserEmail("email");
        user.setUserFirstName("firstname");
        user.setUserLastName("lastname");
        user.setUserLogin("login");
        user.setUserPasswd("password");
        user.setUserTel("002983");
        return user;
    }

    public static Category createCategory() {
        return new Category("cat1");
    }

    public static Console createConsole() {
        return new Console("XBOX");
    }

    public static Game createGame() {
        Game game = new Game();
        game.setGameName("Game name");
        game.setGameSummary("A summary");
        game.setGameImg("An image");
        return game;
    }

    public static PhysicalGame createPhysicalGame() {
        PhysicalGame physicalGame = new PhysicalGame();
        physicalGame.setGamePrice(new BigDecimal(10.5));
        physicalGame.setGameStock(5);
        return physicalGame;
    }

    public static ShoppingBag createShoppingBag() {
        return new ShoppingBag();
    }

    public static Orders createOrder(ShoppingBag sp, Address a) {
        return new Orders(sp, a);
    }

    public static Orders createOrder() {
        return new Orders();
    }





    public static  void saveToSession(Session session, Console aConsole, Game aGame, PhysicalGame aPhysicalGame) {
        LOGGER.info("session.saveOrUpdate(aConsole)");
        session.saveOrUpdate(aConsole);
        LOGGER.info("session.saveOrUpdate(aGame)");
        session.saveOrUpdate(aGame);
        LOGGER.info("session.saveOrUpdate(aPhysicalGame)");
        session.saveOrUpdate(aPhysicalGame); // should do nothing in hibernate logs, because the "saveOrUpdate(aGame)" call already saved the physical game
        LOGGER.info("session.flush();");
        session.flush();
    }

    public static void saveToSession(Session session, Game aGame, Category aCategory) {
        LOGGER.info("session.saveOrUpdate(aCategory)");
        session.saveOrUpdate(aCategory);
        LOGGER.info("session.saveOrUpdate(aGame)");
        session.saveOrUpdate(aGame);
        LOGGER.info("session.flush()");
        session.flush(); // mapped objects saved => insert into category_game to link the game and the category
    }

    public static void saveToSession(Session session, Users aUser, ShoppingBag aShoppingBag) {
        LOGGER.info("session.saveOrUpdate(aUser)");
        session.saveOrUpdate(aUser);
        LOGGER.info("session.save(aShoppingBag)");
        session.save(aShoppingBag);
        LOGGER.info("session.flush()");
        session.flush();
    }

    public static void saveToSession(Session session, Users aUser, Address anAddress) {
        LOGGER.info("session.saveOrUpdate(aUser)");
        session.saveOrUpdate(aUser);
        LOGGER.info("session.saveOrUpdate(anAddress)");
        session.saveOrUpdate(anAddress);
        LOGGER.info("session.flush()");
        session.flush();
    }

    public static void saveToSession(Session session, Users aUser, ShoppingBag aShoppingBag, Address anAddress, Orders anOrder) {
        LOGGER.info("session.saveOrUpdate(aUser)");
        session.saveOrUpdate(aUser);
        LOGGER.info("session.saveOrUpdate(aShoppingBag)");
        session.saveOrUpdate(aShoppingBag);
        LOGGER.info("session.saveOrUpdate(anAddress)");
        session.saveOrUpdate(anAddress);
        LOGGER.info("session.saveOrUpdate(anOrder)");
        session.saveOrUpdate(anOrder);
        LOGGER.info("session.flush()");
        session.flush();
    }

    public static ShoppingBagRow createShoppingBagRow() {
        ShoppingBagRow shoppingBagRow = new ShoppingBagRow();
        return shoppingBagRow;
    }

    public static void saveToSession(Session session, Users aUser, ShoppingBag aShoppingBag, ShoppingBagRow aShoppingBagRow) {
        LOGGER.info("session.saveOrUpdate(aUser)");
        session.saveOrUpdate(aUser);
        LOGGER.info("session.save(aShoppingBag)");
        session.save(aShoppingBag);
        LOGGER.info("session.save(aShoppingBagRow)");
        session.save(aShoppingBagRow);
        LOGGER.info("session.flush()");
        session.flush();

    }
}
