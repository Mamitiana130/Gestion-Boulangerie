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

@WebServlet("/ProduitServlet")
public class ProduitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode)) {
                String nomProduit = request.getParameter("nomProduit");
                double prixProduit = Double.parseDouble(request.getParameter("prixProduit"));
                String description = request.getParameter("description");
                String photoPath = "images/exemple.jpg"; // Optionnel
                int idType = Integer.parseInt(request.getParameter("idType"));

                Produit produit = new Produit();
                produit.setNomProduit(nomProduit);
                produit.setPrixProduit(prixProduit);
                produit.setDescription(description);
                produit.setPhotoPath(photoPath);
                produit.setIdType(idType);

                int idProduit = Produit.insert(connection, produit);

                String[] ingredientIds = request.getParameterValues("ingredientId");
                String[] quantites = request.getParameterValues("quantite");

                if (ingredientIds != null && quantites != null) {
                    for (int i = 0; i < ingredientIds.length; i++) {
                        int idIngredient = Integer.parseInt(ingredientIds[i]);
//                        System.out.println("idIngr:"+idIngredient);
                        double quantiteRecquise = Double.parseDouble(quantites[i]);
//                        System.out.println("quantiteRecquise:"+quantiteRecquise);
//                        System.out.println("idProd:"+produit.getIdProduit());

                        Recette recette = new Recette(idIngredient, idProduit, quantiteRecquise);
                        Recette.insert(connection, recette);
                    }
                }

                response.sendRedirect("ProduitServlet?mode=r");
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("r".equals(mode)) {
                List<Produit> produits = Produit.getAll(connection);
                List<Double> prixFabrications = Produit.getPrixFabrication(connection);
                request.setAttribute("produits", produits);
                request.setAttribute("prixFabrications", prixFabrications);


                request.getRequestDispatcher("listeProduit.jsp").forward(request, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}
