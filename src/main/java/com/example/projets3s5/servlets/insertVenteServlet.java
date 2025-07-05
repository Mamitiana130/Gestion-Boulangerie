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

@WebServlet("/insertVenteServlet")
public class insertVenteServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost())
        {
            if ("i".equals(mode))
            {
                List<Produit> produits = Produit.getAll(connection);
                List<Client> clients = Client.getAll(connection);
                List<Vendeur> vendeurs= Vendeur.getAll(connection);
                request.setAttribute("clients", clients);
                request.setAttribute("vendeurs",vendeurs);
                request.setAttribute("produits", produits);

                request.getRequestDispatcher("insertVente.jsp").forward(request, response);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }




}

