package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.entity.Category;
import model.json.parser.JsonParser;
import webservices.httpclient.CategoryClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpResponse<JsonNode> categories = new CategoryClient().getAll();
            List<Category> categoriesList = new JsonParser().parseJsonListOfObjects(categories.getBody().toString(), Category[].class);
            request.setAttribute("categoriesList", categoriesList);
            this.getServletContext().getRequestDispatcher("game.jsp").forward(request,response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
