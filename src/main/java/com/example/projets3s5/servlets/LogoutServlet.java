package com.example.projets3s5.servlets;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de la session actuelle
        HttpSession session = request.getSession(false); // false pour ne pas créer une nouvelle session si elle n'existe pas
        if (session != null) {
            session.invalidate(); // Invalider la session
        }
        // Redirection vers la page de connexion
        response.sendRedirect("index.jsp");
    }
}

