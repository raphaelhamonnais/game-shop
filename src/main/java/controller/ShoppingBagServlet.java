package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        this.getServletContext().getRequestDispatcher("/shopping-bag.jsp").forward(request, response);
    }
}
