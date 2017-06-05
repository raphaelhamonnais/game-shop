package controller;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.exceptions.UnirestException;
import model.entity.Address;
import model.json.parser.JsonParser;
import webservices.httpclient.AddressClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/addresses")
public class AddressServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpResponse<JsonNode> addresses = new AddressClient().getAll();
            List<Address> addressesList = new JsonParser().parseJsonListOfObjects(addresses.getBody().toString(), Address[].class);
            request.setAttribute("addressesList", addressesList);
            this.getServletContext().getRequestDispatcher("address.jsp").forward(request, response);
        }
        catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
