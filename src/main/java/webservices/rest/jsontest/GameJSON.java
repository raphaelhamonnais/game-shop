package webservices.rest.jsontest;

import dao.GameDao;
import model.Game;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.math.BigDecimal;
import java.util.*;


/**
 * Created by LU Han on 2017/5/3.
 */
@Path("json-test/game")
public class GameJSON {

    private GameDao gameDao = new GameDao();

    /*obtenir tous les jeux*/
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Game> getAllGame() {
        List<Game> games = new ArrayList<Game>();
        games = gameDao.getAllGame();
        return games;
    }

    /*obtenir un jeu par l'identifiant*/
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Game getGameById(@PathParam("id") int id) {
        Game game = gameDao.getGameById(id);
        return game;
    }

    /*creer un jeu*/
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createGame(Game game) {
        gameDao.createGame(game);
    }

    /*mis a jour un jeu*/
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateGame(Game game) {
        gameDao.updateGame(game);
    }

    /*supprimer un jeu*/
    @DELETE
    @Path("{id}")
    public void deleteGame(@PathParam("id") int id) {
        gameDao.deleteGameById(id);
    }




   /* public Game game(@Context SecurityContext sc) {
        Game game = new Game();
        Date date = new Date();
        BigDecimal game_sale_rate = BigDecimal.valueOf(0.98);
        game.setGameName("Game Name");
        game.setGameIsOnSale(true);
        game.setGameAddTime(date);
        game.setGameReleaseYear(2017);
        game.setGameSaleRate(game_sale_rate);

        return game;
    }*/



}
