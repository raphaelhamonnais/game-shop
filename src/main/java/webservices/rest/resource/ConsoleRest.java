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
    public List<Console> getAllConsoles() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_ALL)
                .getResultListAndClose();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Console getConsoleByName(@PathParam("name") String name) {
        return (Console) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_BY_ID)
                .addParameter(QueryHandler.Console.ID_PARAMETER, name)
                .getUniqueResultAndClose();
    }


    @GET
    @Path("{name}/physical-games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhysicalGame> getPhysicalGames(@PathParam("name") String name) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_PHYSICAL_GAMES)
                .addParameter(QueryHandler.Console.ID_PARAMETER, name)
                .getResultListAndClose();
    }


    @GET
    @Path("{name}/games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getGames(@PathParam("name") String name) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Console.GET_GAMES)
                .addParameter(QueryHandler.Console.ID_PARAMETER, name)
                .getResultListAndClose();
    }
}
