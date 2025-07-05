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

@WebServlet("/ResultProduitMoisServlet")
public class ResultProduitMoisServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        if ("research".equalsIgnoreCase(mode)) {
            try (Connection connection = ConnexionPost.getConnectionPost()) {
                String  mois = request.getParameter("mois");
                String annee = request.getParameter("annee");

                Integer moisVrais = (mois == null || mois.isEmpty())
                        ? null
                        : Integer.parseInt(mois);
                Integer anneeVrais = (annee == null || annee.isEmpty())
                        ? null
                        : Integer.parseInt(annee);
                List<ProduitDuMois> produitDuMois = ProduitDuMois.getWithCriteriasMoisAnnee(connection, moisVrais, anneeVrais);

                request.setAttribute("produitDuMois", produitDuMois);


                request.getRequestDispatcher("resultProduitMois.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }


}
