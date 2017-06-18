package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.entity.Game;
import model.entity.ShoppingBag;
import model.json.parser.JsonParser;
import webservices.httpclient.GameClient;
import webservices.httpclient.UsersClient;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShoppingBagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        if (request.getUserPrincipal() == null)
            name = "no users";
        else
            name = request.getUserPrincipal().getName();
        request.setAttribute("name", name);

        try {
            HttpResponse<JsonNode> shoppingBagResponse = new UsersClient().getShoppingBag(name);
            ShoppingBag shoppingBag = null;
            List<ShoppingBag> shoppingBagList = new JsonParser().parseJsonListOfObjects(
                    shoppingBagResponse.getBody().toString(),
                    ShoppingBag[].class
            );
            if (! shoppingBagList.isEmpty())
                shoppingBag = shoppingBagList.get(0);

            request.setAttribute("shoppingBag", shoppingBag);
            this.getServletContext().getRequestDispatcher("/shopping-bag.jsp").forward(request, response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
