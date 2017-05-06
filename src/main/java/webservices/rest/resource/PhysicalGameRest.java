package webservices.rest.resource;

import model.entity.PhysicalGame;
import model.handler.HibernateTransactionHandler;
import model.query.PhysicalGameQueriesHandler;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("physical-games")
@SuppressWarnings("unchecked")
public class PhysicalGameRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhysicalGame> getAllPhysicalGames() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_GET_ALL_PHYSICAL_GAMES)
                .getResultListAndClose();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PhysicalGame getPhysicalGameById(@PathParam("id") int id) {
        return (PhysicalGame) new HibernateTransactionHandler()
                .openSession()
                .createQuery(PhysicalGameQueriesHandler.QUERY_GET_PHYSICAL_GAME_BY_ID)
                .addParameter(PhysicalGameQueriesHandler.PARAM_PHYSICAL_GAME_ID, id)
                .getUniqueResultAndClose();
    }
}
