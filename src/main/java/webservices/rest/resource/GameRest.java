package webservices.rest.resource;

import model.entity.Console;
import model.entity.Game;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


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
