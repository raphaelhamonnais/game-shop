package webservices.rest.resource;

import model.entity.Console;
import model.entity.Game;
import model.handler.HibernateTransactionHandler;
import model.query.QueryHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("games")
@SuppressWarnings("unchecked")
public class GameRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_ALL)
                .getResultListAndClose();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameById(@PathParam("id") int id) {
        return (Game) new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_BY_ID)
                .addParameter(QueryHandler.Game.ID_PARAMETER, id)
                .getUniqueResultAndClose();
    }


    @GET
    @Path("{id}/consoles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Console> getConsoles(@PathParam("id") int id) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_CONSOLES)
                .addParameter(QueryHandler.Game.ID_PARAMETER, id)
                .getResultListAndClose();
    }

    @GET
    @Path("{id}/physical-games")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Console> getPhysicalGames(@PathParam("id") int id) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(QueryHandler.Game.GET_PHYSICAL_GAMES)
                .addParameter(QueryHandler.Game.ID_PARAMETER, id)
                .getResultListAndClose();
    }

    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(/*TODO to define*/)
    public void createGame(@FormParam("someFormParam") String someFormParam) {
        // TODO create game with params
    }

    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces(/*TODO to define*/)
    public void updateGame(@FormParam("someFormParam") String someFormParam) {
        // TODO update game with params
    }
}
