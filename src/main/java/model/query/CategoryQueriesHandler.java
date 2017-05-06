package model.query;

public class CategoryQueriesHandler {

    public static final String PARAM_CATEGORY_NAME = "name";

    public static final String QUERY_GET_ALL_CATEGORIES = "from Category ";

    public static final String QUERY_GET_CATEGORY_BY_NAME = "from Category c " +
            "where c.catName=:"+ PARAM_CATEGORY_NAME;

    public static final String QUERY_GET_CATEGORY_GAMES = "from Game g " +
            "join fetch g.publisher p " +
            "join fetch g.categories c " +
            "where c.catName=:" + PARAM_CATEGORY_NAME;
}
