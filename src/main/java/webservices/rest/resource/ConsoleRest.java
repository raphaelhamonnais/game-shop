package webservices.rest.resource;

import model.entity.Console;
import model.entity.Game;
import model.entity.PhysicalGame;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;
import webservices.rest.PaginationHandler;
import webservices.rest.PaginationService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("consoles")
@SuppressWarnings("unchecked")
public class ConsoleRest extends PaginationService {

    @Override
    protected PaginationHandler setPaginationHandler() {
        return new PaginationHandler(
                QueryHandler.Console.COUNT,
                QueryHandler.Console.GET_ALL);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_ALL)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("name") String name) {
        Console result = (Console) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_BY_ID)
                .addParameter(QueryHandler.Console.ID_PARAMETER, name)
                .getUniqueResultAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{name}/physical-games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPhysicalGames(@PathParam("name") String name) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_PHYSICAL_GAMES)
                .addParameter(QueryHandler.Console.ID_PARAMETER, name)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }


    @GET
    @Path("{name}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGames(@PathParam("name") String name) {
        List result = new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_GAMES)
                .addParameter(QueryHandler.Console.ID_PARAMETER, name)
                .getResultListAndClose();
        return Response.status(Response.Status.OK).entity(result).build();
    }
}
