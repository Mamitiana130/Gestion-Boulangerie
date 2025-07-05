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
import java.sql.Types;
import java.util.List;

@WebServlet("/HistoriquePrixProduitServlet")
public class HistoriquePrixProduitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("s".equals(mode)) {
                List<HistoriquePrixProduit> histo = HistoriquePrixProduit.getAll(connection);
                request.setAttribute("histo", histo);

                request.getRequestDispatcher("historiquePrix.jsp").forward(request, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode)) {
                // Récupérer les paramètres depuis le formulaire
                int idProduit = Integer.parseInt(request.getParameter("idProduit"));
                double prixProduit = Double.parseDouble(request.getParameter("prixProduit"));
                String dateStr = request.getParameter("date");
                HistoriquePrixProduit historique = new HistoriquePrixProduit();

                if (dateStr != null && !dateStr.isEmpty()) {
                    // Convertir le format ISO 8601 (datetime-local) en format attendu par Timestamp
                    dateStr = dateStr.replace("T", " ") + ":00"; // Ajouter les secondes si manquantes
                    Timestamp date = Timestamp.valueOf(dateStr);
                    historique.setDateChangement(date);
                }
                // Créer un objet HistoriquePrixProduit
                historique.setIdProduit(idProduit);
                historique.setPrix(prixProduit);

                // Insertion dans la base de données
                HistoriquePrixProduit.insert(connection, historique);

                // Redirection après l'insertion
                response.sendRedirect("HistoriquePrixProduitServlet?mode=s");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de l'insertion : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
