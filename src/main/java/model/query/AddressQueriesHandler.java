package model.query;

public class AddressQueriesHandler {

    public static final String PARAM_ADDRESS_NAME = "id";

    public static final String QUERY_GET_ALL_ADDRESSES = "from Address address ";

    public static final String QUERY_GET_ADDRESS_BY_ID = QUERY_GET_ALL_ADDRESSES +
            "where address.id =:" + PARAM_ADDRESS_NAME;

    public static final String QUERY_GET_ADDRESS_USERS = "from Users u " +
            "join fetch u.addresses a " +
            "join fetch u.roles r " +
            "where a.id =:" + PARAM_ADDRESS_NAME;

    public static final String QUERY_GET_ADDRESS_ORDERS = "from Orders o " +
            " join fetch o.user u " +
            " join fetch u.addresses " +
            " join fetch u.roles r " +
            " join fetch o.shoppingBag sb " +
            " join fetch sb.user " +
            " join fetch sb.shoppingBagRows sbr " +
            " join fetch sbr.physicalGame pg " +
            " join fetch pg.console " +
            " join fetch pg.game g " +
            " join fetch g.categories " +
            " join fetch g.publisher " +
            " join fetch o.address a " +
            " where a.id =:" + PARAM_ADDRESS_NAME;
}
