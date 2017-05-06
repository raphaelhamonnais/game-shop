package webservices.rest.resource;

import model.entity.Console;
import model.entity.PhysicalGame;
import model.handler.HibernateTransactionHandler;
import model.query.ConsoleQueriesHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("consoles")
@SuppressWarnings("unchecked")
public class ConsoleRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Console> getAllConsoles() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(ConsoleQueriesHandler.QUERY_GET_ALL_CONSOLES)
                .getResultListAndClose();
    }

    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Console getConsoleByName(@PathParam("name") String name) {
        return (Console) new HibernateTransactionHandler()
                .openSession()
                .createQuery(ConsoleQueriesHandler.QUERY_GET_CONSOLE_BY_NAME)
                .addParameter(ConsoleQueriesHandler.PARAM_CONSOLE_NAME, name)
                .getUniqueResultAndClose();
    }


    @GET
    @Path("{name}/physical-games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhysicalGame> getPhysicalGames(@PathParam("name") String name) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(ConsoleQueriesHandler.QUERY_GET_CONSOLE_PHYSICAL_GAMES)
                .addParameter(ConsoleQueriesHandler.PARAM_CONSOLE_NAME, name)
                .getResultListAndClose();
    }


}
