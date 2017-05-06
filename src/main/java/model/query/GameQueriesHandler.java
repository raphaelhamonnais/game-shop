package model.query;

public class GameQueriesHandler {

    public static final String PARAM_GAME_ID = "id";

    public static final String QUERY_GET_ALL_GAMES = "from Game g " +
            " join fetch g.publisher " +
            " join fetch g.categories ";

    public static final String QUERY_GET_GAME_BY_ID = QUERY_GET_ALL_GAMES +
            "where g.gameId=:" + PARAM_GAME_ID;

    public static final String QUERY_GAME_CONSOLES = "from Console c " +
            "join fetch c.physicalGames pg " +
            "join fetch pg.game g " +
            "where g.id=:" + PARAM_GAME_ID;


    /**
     * - Si les "join fetch" ne sont pas présents, alors deux possibilités
     *      - si les attributs sont en FetchType.LAZY
     *          - erreur de type "failed to lazily initialize a collection of role",
     *          c'est à dire qu'il est impossible de charger les relations définies
     *          en FetchType.LAZY
     *      - si les attributs sont en FetchType.EAGER
     *          - les relation définies en FetchType.EAGER vont se charger... mais une
     *          par une...
     *          C'est à dire qu'on sélectionne tous les jeux puis qu'après, pour chaque
     *          jeu, on va aller chercher les relations avec un ou plusieurs select : s'il
     *          y a 1000 jeux, on fera 1000 select pour avoir les 1000 Publishers
     * - Les joins fetch permettent de faire tout cela en une seule requête, c'est à dire
     * une requête SQL classique avec des joins. Le "fetch" est là pour forcer les relations
     * LASY à se charger.
     * - Les relations sont définies en LASY pour éviter le cas où on fait 1000 select sur la
     * base de données parce qu'on a oublié de joindre les entités dont on a besoin : si on oublie,
     * il y aura une erreur.
     */

}
