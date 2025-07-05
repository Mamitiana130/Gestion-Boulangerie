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
import java.util.Map;

@WebServlet("/ResultCommissionGenreServlet")
public class ResultCommissionGenreServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        if ("research".equalsIgnoreCase(mode)) {
            try (Connection connection = ConnexionPost.getConnectionPost()) {

                String date_debut = request.getParameter("dateDebut");
                Timestamp dateDebut = Timestamp.valueOf(date_debut.replace("T", " ") + ":00");
                java.sql.Date sqlDateDebut = new java.sql.Date(dateDebut.getTime());

                String date_fin = request.getParameter("dateFin");
                Timestamp dateFin = Timestamp.valueOf(date_fin.replace("T", " ") + ":00");
                java.sql.Date sqlDateFin = new java.sql.Date(dateFin.getTime());
                List<Vente> ventesDate = Vente.getByDateDebutDateFin(connection, sqlDateDebut,sqlDateFin);

                Map<String,Double> commission = Vente.getCommissionParGenre(connection,ventesDate);

                request.setAttribute("ventes", ventesDate);
                request.setAttribute("commission", commission);


                request.getRequestDispatcher("resultCommissionGenre.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                // Gérer les erreurs et afficher un message à l'utilisateur
                request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }
}
