package webservices.rest.resource;

import model.entity.Console;
import model.handler.HibernateTransactionHandler;
import model.entity.Game;
import model.query.GameQueriesHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.*;


@Path("games")
@SuppressWarnings("unchecked")
public class GameRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames() {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(GameQueriesHandler.QUERY_GET_ALL_GAMES)
                .getResultListAndClose();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameById(@PathParam("id") int id) {
        return (Game) new HibernateTransactionHandler()
                .openSession()
                .createQuery(GameQueriesHandler.QUERY_GET_GAME_BY_ID)
                .addParameter(GameQueriesHandler.PARAM_GAME_ID, id)
                .getUniqueResultAndClose();
    }


    @GET
    @Path("{id}/consoles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Console> getConsoles(@PathParam("id") int id) {
        return new HibernateTransactionHandler()
                .openSession()
                .createQuery(GameQueriesHandler.QUERY_GAME_CONSOLES)
                .addParameter(GameQueriesHandler.PARAM_GAME_ID, id)
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
