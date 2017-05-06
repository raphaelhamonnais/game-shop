package webservices.rest.resource;

import model.dao.GameDao;
import model.entity.Game;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.*;


@Path("json-test/games")
public class GameRest {

    private GameDao gameDao = new GameDao();

    // get all games
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGames() {
        return gameDao.getAllGames();
    }

    // get game by id
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameById(@PathParam("id") int id) {
        return gameDao.getGameById(id);
    }

    // create game
    @POST
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    //@Produces(//TODO to define)
    public void createGame(@FormParam("someFormParam") String someFormParam) {
        // TODO create game with params
    }

    // update game
    @PUT
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    //@Produces(//TODO to define)
    public void updateGame(@FormParam("someFormParam") String someFormParam) {
        // TODO update game with params
    }
}
