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
import java.util.List;

@WebServlet("/InsertProduitMoisServlet")
public class InsertProduitMoisServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode)) {
                String[] mois=new String[12];
                mois[0]="Janvier";
                mois[1]="Fevrier";
                mois[2]="Mars";
                mois[3]="Avril";
                mois[4]="Mai";
                mois[5]="Juin";
                mois[6]="Juillet";
                mois[7]="Aout";
                mois[8]="Septembre";
                mois[9]="Octobre";
                mois[10]="Novembre";
                mois[11]="Decembre";

                int[] annee=new int[3];
                annee[0]=2023;
                annee[1]=2024;
                annee[2]=2025;

                List<Produit> produits = Produit.getAll(connection);

                request.setAttribute("produits", produits);
                request.setAttribute("mois", mois);
                request.setAttribute("annee", annee);
                request.setAttribute("mode", "i");
                request.getRequestDispatcher("insertProduitMois.jsp").forward(request, response);
            }

        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
