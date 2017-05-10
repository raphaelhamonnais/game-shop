package model.query;

public class QueryHandler {


    private static AddressQH addressQH = new AddressQH();
    private static CategoryQH categoryQH = new CategoryQH();
    private static ConsoleQH consoleQH = new ConsoleQH();
    private static GameQH gameQH = new GameQH();
    private static OrdersQH ordersQH = new OrdersQH();
    private static PhysicalGameQH physicalGameQH = new PhysicalGameQH();
    private static PublisherQH publisherQH = new PublisherQH();
    private static RolesQH rolesQH = new RolesQH();
    private static ShoppingBagQH shoppingBagQH = new ShoppingBagQH();
    private static ShoppingBagRowQH shoppingBagRowQH = new ShoppingBagRowQH();
    private static UsersQH usersQH = new UsersQH();


    public static final String COUNT_ALL = " select count(*) ";


    public static class Address {

        private static AddressQH that = addressQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_USERS = usersQH.from()
                + usersQH.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_ORDERS = ordersQH.from()
                + ordersQH.fetchRequiredProperties()
                + usersQH.removeAddressAlias(usersQH.fetchRequiredProperties())
                + shoppingBagQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.filterById();
    }


    public static class Category {

        private static CategoryQH that = categoryQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_GAMES = gameQH.from()
                + gameQH.fetchRequiredProperties()
                + that.filterById();
    }


    public static class Console {

        private static ConsoleQH that = consoleQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_PHYSICAL_GAMES = physicalGameQH.from()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_GAMES = gameQH.from()
                + gameQH.fetchRequiredProperties()
                + gameQH.fetchPhysicalGames()
                + physicalGameQH.fetchRequiredProperties()
                + that.filterById();
    }


    public static class Game {

        private static GameQH that = gameQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_PHYSICAL_GAMES = physicalGameQH.from()
                + physicalGameQH.fetchRequiredProperties()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_CONSOLES = consoleQH.from()
                + consoleQH.fetchRequiredProperties()
                + consoleQH.fetchPhysicalGames()
                + physicalGameQH.fetchRequiredProperties()
                + that.filterById();
    }


    public static class PhysicalGame {

        private static PhysicalGameQH that = physicalGameQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.filterById();
    }


    public static class Users {

        private static UsersQH that = usersQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_SHOPPING_BAGS = shoppingBagQH.from()
                + shoppingBagQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_ORDERS = ordersQH.from()
                + ordersQH.fetchRequiredProperties()
                + shoppingBagQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.fetchRequiredProperties()
                + that.filterById();
    }


    public static class ShoppingBag {
        private static ShoppingBagQH that = shoppingBagQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties()
                + usersQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + usersQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.filterById();
    }


    public static class Order {
        private static OrdersQH that = ordersQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties()
                + usersQH.fetchRequiredProperties()
                + shoppingBagQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + usersQH.fetchRequiredProperties()
                + shoppingBagQH.fetchRequiredProperties()
                + shoppingBagRowQH.fetchRequiredProperties()
                + physicalGameQH.fetchRequiredProperties()
                + gameQH.fetchRequiredProperties()
                + that.filterById();

        public static final String DELETE_BY_ID = that.delete()
                + that.filterById();
    }


    public static class Publisher {
        private static PublisherQH that = publisherQH;

        public static final String ID_PARAMETER = that.getParamIdFilter();

        public static final String COUNT = that.count() + that.from();

        public static final String GET_ALL = that.from()
                + that.fetchRequiredProperties();

        public static final String GET_BY_ID = that.from()
                + that.fetchRequiredProperties()
                + that.filterById();

        public static final String GET_GAMES = gameQH.from()
                + gameQH.fetchRequiredProperties()
                + that.filterById();
    }
}
