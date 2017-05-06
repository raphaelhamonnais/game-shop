package model.query;

public class PhysicalGameQueriesHandler {

    public static final String PARAM_PHYSICAL_GAME_ID = "name";

    public static final String QUERY_GET_ALL_PHYSICAL_GAMES = "from PhysicalGame pg " +
            "join fetch pg.console c " +
            "join fetch pg.game g " +
            "join fetch g.publisher " +
            "join fetch g.categories ";

    public static final String QUERY_GET_PHYSICAL_GAME_BY_ID = QUERY_GET_ALL_PHYSICAL_GAMES +
            "where pg.id=:"+ PARAM_PHYSICAL_GAME_ID;

}
