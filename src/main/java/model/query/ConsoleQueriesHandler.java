package model.query;

public class ConsoleQueriesHandler {

    public static final String PARAM_CONSOLE_NAME = "name";

    public static final String QUERY_GET_ALL_CONSOLES = "from Console c ";

    public static final String QUERY_GET_CONSOLE_BY_NAME = QUERY_GET_ALL_CONSOLES +
            "where c.consoleName=:"+ PARAM_CONSOLE_NAME;

    public static final String QUERY_GET_CONSOLE_PHYSICAL_GAMES = "from PhysicalGame pg " +
            " join fetch pg.console c " +
            " join fetch pg.game g" +
            " join fetch g.publisher " +
            " join fetch g.categories " +
            " where c.consoleName=:" + PARAM_CONSOLE_NAME;
}
