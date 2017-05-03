package webservices.rest.jsontest;

import model.Game;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.math.BigDecimal;
import java.util.*;


/**
 * Created by lenovo on 2017/5/3.
 */
@Path("json-test/game")
public class GameJSON {

    @GET
    @Produces(MediaType.APPLICATION_JSON)

    public Game game(@Context SecurityContext sc) {
        Game game = new Game();
        Date date = new Date();
         BigDecimal game_sale_rate = BigDecimal.valueOf(0.98);
        game.setGameName("Game Name");
        game.setGameIsOnSale(true);
        game.setGameAddTime(date);
        game.setGameReleaseYear(2017);
        game.setGameSaleRate(game_sale_rate);

        return game;
    }



}
