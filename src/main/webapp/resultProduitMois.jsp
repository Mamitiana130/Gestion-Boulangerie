<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.ProduitDuMois" %>
<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Résultat de la recherche - Produits du mois</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>ID Produit du Mois</th>
                        <th>Nom du Produit</th>
                        <th>Mois</th>
                        <th>Année</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<ProduitDuMois> produitDuMoisList = (List<ProduitDuMois>) request.getAttribute("produitDuMois");

                        if (produitDuMoisList != null && !produitDuMoisList.isEmpty()) {
                            for (ProduitDuMois pdm : produitDuMoisList) {
                                Produit produit = pdm.getProduit();
                    %>
                    <tr>
                        <td><%= pdm.getIdProduitDuMois() %></td>
                        <td><%= produit.getNomProduit() %></td>
                        <td><%= pdm.getMois() %></td>
                        <td><%= pdm.getAnnee() %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">Aucun produit trouvé pour la période spécifiée.</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>
