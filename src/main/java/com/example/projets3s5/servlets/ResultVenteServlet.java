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

@WebServlet("/ResultVenteServlet")
public class ResultVenteServlet extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        if ("research".equalsIgnoreCase(mode)) {
            try (Connection connection = ConnexionPost.getConnectionPost()) {
                String idTypeParam = request.getParameter("idType");
                String idParfumParam = request.getParameter("idParfum");

                Integer idType = (idTypeParam != null && !idTypeParam.isEmpty()) ? Integer.parseInt(idTypeParam) : null;
                Integer idParfum = (idParfumParam != null && !idParfumParam.isEmpty()) ? Integer.parseInt(idParfumParam) : null;

                List<Integer> produits = Produit.getWithCritariasParfumType(connection, idType, idParfum);
                List<Vente> ventes = Vente.getVenteSelect(connection, produits);

                request.setAttribute("ventes", ventes);
                request.getRequestDispatcher("resultVente.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }


}
