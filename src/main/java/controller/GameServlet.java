package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.entity.Game;
import model.json.parser.JsonParser;
import webservices.httpclient.GameClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by lenovo on 2017/5/31.
 */

@WebServlet("/game")
public class GameServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            HttpResponse<JsonNode> games = new GameClient().getAll(); //TODO remplacer par get games, sans faire de pagination
            List<Game> gamesList = new JsonParser().parseJsonListOfObjects(games.getBody().toString(), Game[].class);
            request.setAttribute("gameList", gamesList);
            this.getServletContext().getRequestDispatcher("game.jsp").forward(request, response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
