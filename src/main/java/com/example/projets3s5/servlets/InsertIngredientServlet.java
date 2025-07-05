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

@WebServlet("/InsertIngredientServlet")
public class InsertIngredientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode)) {
                List<Unite> unites = Unite.getAll(connection);
                request.setAttribute("unites", unites);

                request.setAttribute("mode", "i");
                request.getRequestDispatcher("insertIngredient.jsp").forward(request, response);
            }
            if ("u".equals(mode)){
                int idIngredient = Integer.parseInt(request.getParameter("idIngredient"));
                Ingredient ingredientChosen = Ingredient.getByIdIngredient(connection,idIngredient);

                List<Unite> unites = Unite.getAll(connection);

                request.setAttribute("ingredientChosen", ingredientChosen);
                request.setAttribute("unites", unites);

                request.setAttribute("mode", "u");
                request.getRequestDispatcher("insertIngredient.jsp").forward(request, response);
            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
