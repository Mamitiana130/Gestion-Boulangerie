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

@WebServlet("/ResultProductionServlet")
public class ResultProductionServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        if ("research".equalsIgnoreCase(mode)) {
            try (Connection connection = ConnexionPost.getConnectionPost()) {
                String idIngredientParam = request.getParameter("idIngredient");
                String idTypeParam = request.getParameter("idType");

                Integer idIngredient = (idIngredientParam == null || idIngredientParam.isEmpty())
                        ? null
                        : Integer.parseInt(idIngredientParam);
                Integer idType = (idTypeParam == null || idTypeParam.isEmpty())
                        ? null
                        : Integer.parseInt(idTypeParam);

                // Recherche des produits selon les critères
                List<Produit> produits = Produit.getWithCritariasIngredientType(connection, idIngredient, idType);
                List<StockProduit> stockProduits = StockProduit.getStockProduit(connection, produits);

                // Envoi des données à la vue
                request.setAttribute("produits", produits);
                request.setAttribute("stockProduits", stockProduits);

                request.getRequestDispatcher("resultProduction.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }


}
