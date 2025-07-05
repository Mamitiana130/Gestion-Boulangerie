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

@WebServlet("/VenteServlet")
public class VenteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

        try (Connection connection = ConnexionPost.getConnectionPost()) {
            if ("i".equals(mode))
            {
                int idProduit = Integer.parseInt(request.getParameter("idProduit"));
                int quantite = Integer.parseInt(request.getParameter("quantite"));
                int idClient = Integer.parseInt(request.getParameter("idClient"));
                int idVendeur = Integer.parseInt(request.getParameter("idVendeur"));

                String daty = request.getParameter("date");
                Timestamp dateVente = Timestamp.valueOf(daty.replace("T", " ") + ":00");
                Client client=Client.getByIdClient(connection,idClient);
                Vendeur vendeur=Vendeur.getByIdVendeur(connection,idVendeur);

                Vente vente=new Vente();
                vente.setIdProduit(idProduit);
                vente.setQuantite(quantite);
                vente.setDateVente(dateVente);
                vente.setClient(client);
                vente.setVendeur(vendeur);
                Vente.insert(connection, vente);
                response.sendRedirect("accueil.jsp");

            }
        }catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur : " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
