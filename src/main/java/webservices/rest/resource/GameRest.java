package webservices.rest.resource;

import model.entity.Game;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("games")
@SuppressWarnings("unchecked")
public class GameRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Game.COUNT,
                QueryHandler.Game.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Game result = (Game) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_BY_ID)
                .addParameter(QueryHandler.Game.ID_PARAMETER, id)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{id}/consoles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsoles(@PathParam("id") int id) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_CONSOLES)
                .addParameter(QueryHandler.Game.ID_PARAMETER, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{id}/physical-games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPhysicalGames(@PathParam("id") int id) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_PHYSICAL_GAMES)
                .addParameter(QueryHandler.Game.ID_PARAMETER, id)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGamesSearch(
            @QueryParam("name") String name,
            @QueryParam("console") String console,
            @QueryParam("category") String category,
            @QueryParam("isBest") String isBest,
            @QueryParam("isNew") String isNew,
            @QueryParam("isHot") String isHot,
            @QueryParam("isOnSale") String isOnSale) {

        StringBuilder query = new StringBuilder()
                .append("from Game ga join fetch ga.publisher join fetch ga.categories cat join fetch ga.physicalGames pg join fetch pg.console con where 1=1 ");


        Map<String, String> params = new HashMap<>();
        params.put("name", name);
        params.put("console", console);
        params.put("category", category);
        params.put("isBest", isBest);
        params.put("isNew", isNew);
        params.put("isHot", isHot);
        params.put("isOnSale", isOnSale);
        Map<String, String> queries = new HashMap<>();
        queries.put("name", " and ga.gameName LIKE :name ");
        queries.put("console", " and con.consoleName LIKE :console ");
        queries.put("category", " and cat.catName LIKE :category ");
        queries.put("isBest", " and ga.gameIsBest=true ");
        queries.put("isNew", " and ga.gameIsNew=true ");
        queries.put("isHot", " and ga.gameIsHot=true ");
        queries.put("isOnSale", " and ga.gameIsOnSale=true ");


        boolean queryHasParam = false;
        for (String paramKey : params.keySet()) {
            queryHasParam = params.get(paramKey) != null;
            if (queryHasParam)
                break;
        }

        if (!queryHasParam) {
            System.out.println("No params => get all games");
            return this.getAll();

        }


        for (String paramKey : params.keySet())
            if (params.get(paramKey) != null)
                query.append(queries.get(paramKey));

        HibernateTransactionHandler tx = new HibernateTransactionHandler()
                .openSession()
                .createQuery(query.toString());
        if (params.get("name") != null)
            tx = tx.addParameter("name", "%" + name + "%");
        if (params.get("console") != null)
            tx = tx.addParameter("console", "%" + console + "%");
        if (params.get("category") != null)
            tx = tx.addParameter("category", "%" + category + "%");


        List result = tx.getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(/*TODO to define*/)
    public Response createGame(@FormParam("someFormParam") String someFormParam) {
        // TODO create game with params
        return null;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(/*TODO to define*/)
    public Response updateGame(@FormParam("someFormParam") String someFormParam) {
        // TODO update game with params
        return null;
    }
}
