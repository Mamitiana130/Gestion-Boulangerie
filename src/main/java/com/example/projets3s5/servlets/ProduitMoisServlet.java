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

@WebServlet("/ProduitMoisServlet")
public class ProduitMoisServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode))
            {
                int idProduit = Integer.parseInt(request.getParameter("idProduit"));

                Produit produit = Produit.getProduitById(connection,idProduit);
                int  mois = Integer.parseInt(request.getParameter("mois"));
                int annee = Integer.parseInt(request.getParameter("annee"));

                ProduitDuMois produitDuMois = new ProduitDuMois(mois,annee,produit);
                ProduitDuMois.insert(connection,produitDuMois);
                response.sendRedirect("accueil.jsp");

            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
