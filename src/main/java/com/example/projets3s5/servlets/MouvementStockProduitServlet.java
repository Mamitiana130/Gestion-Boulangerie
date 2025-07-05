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

@WebServlet("/MouvementStockProduitServlet")
public class MouvementStockProduitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("s".equals(mode)) {
                List<Type> types = Type.getAll(connection);
                request.setAttribute("types", types);

                request.getRequestDispatcher("multiRecherche.jsp").forward(request, response);
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

        if ("research".equalsIgnoreCase(mode)) {
            try (Connection connection = ConnexionPost.getConnectionPost()) {
                // Récupérer les critères depuis la requête
                String dateDebutStr = request.getParameter("dateDebut");
                String dateFinStr = request.getParameter("dateFin");
                String trie = request.getParameter("trie");
                int idType = Integer.parseInt(request.getParameter("idType"));

                // Conversion des dates en Timestamp
                Timestamp dateDebut = Timestamp.valueOf(dateDebutStr.replace("T", " ") + ":00");
                Timestamp dateFin = Timestamp.valueOf(dateFinStr.replace("T", " ") + ":00");

                // Appel de la méthode de recherche
                List<MouvementStockProduit> mouvements = MouvementStockProduit.getWithCriterias(connection, dateDebut, dateFin, idType, trie);

                // Envoyer les résultats à la vue
                request.setAttribute("mouvements", mouvements);
                request.getRequestDispatcher("resultMultiRecherche.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }


}
