package com.example.projets3s5.servlets;

import com.example.projets3s5.connexion.ConnexionPost;
import com.example.projets3s5.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/ResultVenteDateServlet")
public class ResultVenteDateServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        if ("research".equalsIgnoreCase(mode)) {
            try (Connection connection = ConnexionPost.getConnectionPost()) {
                // Récupérer la date envoyée depuis le formulaire
                String daty = request.getParameter("dateVente");
                // Convertir la chaîne de date en format Timestamp
                Timestamp dateVente = Timestamp.valueOf(daty.replace("T", " ") + ":00");
                // Convertir Timestamp en java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(dateVente.getTime());

                // Appeler la méthode getByDate pour récupérer la liste des ventes
                List<Vente> ventesDate = Vente.getByDate(connection, sqlDate);

                // Ajouter les ventes récupérées dans les attributs de la requête pour les transmettre à la JSP
                request.setAttribute("ventes", ventesDate);

                // Rediriger vers la JSP avec les données
                request.getRequestDispatcher("resultVenteDate.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer les erreurs et afficher un message à l'utilisateur
                request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }



}
