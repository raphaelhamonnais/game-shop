package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name;
        if (request.getUserPrincipal() == null)
            name = "no users";
        else
            name = request.getUserPrincipal().getName();
        request.setAttribute("name", name);
        this.getServletContext().getRequestDispatcher("/account.jsp").forward(request, response);
    }
}
