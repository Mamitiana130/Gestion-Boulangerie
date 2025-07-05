package com.example.projets3s5.servlets;

import com.example.projets3s5.connexion.ConnexionPost;
import com.example.projets3s5.models.Membre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String prenomMembre = request.getParameter("prenomMembre");
        String mdp = request.getParameter("mdp");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            Membre membre = Membre.login(connection, prenomMembre, mdp);

            if (membre != null) {
                HttpSession session = request.getSession();
                session.setAttribute("membre", membre);
                request.getRequestDispatcher("accueil.jsp").forward(request, response); // Redirige vers une page d'accueil
            } else {
                request.setAttribute("error", "Prénom ou mot de passe incorrect.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la connexion", e);
        }
    }
}
