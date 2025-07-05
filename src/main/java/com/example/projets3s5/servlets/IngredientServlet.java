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

@WebServlet("/IngredientServlet")
public class IngredientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode)) {
                String nomIngredient = request.getParameter("nomIngredient");
                double prixIngredient = Double.parseDouble(request.getParameter("prixIngredient"));
                int idUnite = Integer.parseInt(request.getParameter("idUnite"));

                Ingredient ingredient = new Ingredient();
                ingredient.setNomIngredient(nomIngredient);
                ingredient.setPrixIngredient(prixIngredient);
                ingredient.setIdUnite(idUnite);

                Ingredient.insert(connection, ingredient);
                response.sendRedirect("IngredientServlet?mode=r");

            }else if ("u".equals(mode)) {
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                String nomIngredient = request.getParameter("nomIngredient");
                double prixIngredient = Double.parseDouble(request.getParameter("prixIngredient"));
                int idUnite = Integer.parseInt(request.getParameter("idUnite"));

                Ingredient ingredient = new Ingredient();
                ingredient.setIdIngredient(idIngredient);
                ingredient.setNomIngredient(nomIngredient);
                ingredient.setPrixIngredient(prixIngredient);
                ingredient.setIdUnite(idUnite);

                Ingredient.update(connection, ingredient);
                response.sendRedirect("+IngredientServlet?mode=r");
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
                List<Ingredient> ingredients = Ingredient.getAll(connection);
                request.setAttribute("ingredients", ingredients);

                request.getRequestDispatcher("listeIngredient.jsp").forward(request, response);

            }else if ("d".equals(mode)) {
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                Ingredient.delete(connection,idIngredient);

                response.sendRedirect("IngredientServlet?mode=r");
            }

        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


}
