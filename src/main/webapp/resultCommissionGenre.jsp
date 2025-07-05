<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.Vente" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Résultat de la recherche des commissions par genre</h5>

            <!-- Table des ventes -->
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>ID Vente</th>
                        <th>Nom de produit</th>
                        <th>Quantité</th>
                        <th>Date de vente</th>
                        <th>Nom du client</th>
                        <th>Nom du Vendeur</th>
                        <th>Genre</th>
                        <th>Prix Total</th>

                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Vente> ventes = (List<Vente>) request.getAttribute("ventes");

                        if (ventes != null && !ventes.isEmpty()) {
                            for (Vente vente : ventes) {
                    %>
                    <tr>
                        <td><%= vente.getIdVente() %></td>
                        <td><%= vente.getNomProduit() %></td>
                        <td><%= vente.getQuantite() %></td>
                        <td><%= vente.getDateVente() %></td>
                        <td><%= vente.getClient() != null ? vente.getClient().getNomClient() : "Non spécifié" %></td>
                        <td><%= vente.getVendeur() != null ? vente.getVendeur().getNomVendeur() : "Non spécifié" %></td>
                        <td><%= vente.getVendeur() != null ? vente.getVendeur().getGenre().getNomGenre() : "Non spécifié" %></td>
                        <td><%= vente.getPrixTotal() %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="6" class="text-center">Aucune vente trouvée pour cette date.</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>

            <!-- Table des commissions -->
            <div class="table-responsive text-nowrap mt-4">
                <h5 class="card-header">Commissions des vendeurs par genre</h5>
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Genre</th>
                        <th>Commission (5%)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        Map<String, Double> commissions = (Map<String, Double>) request.getAttribute("commission");

                        if (commissions != null && !commissions.isEmpty()) {
                            for (Map.Entry<String, Double> entry : commissions.entrySet()) {
                    %>
                    <tr>
                        <td><%= entry.getKey() %></td>
                        <td><%= String.format("%.2f", entry.getValue()) %> Ar</td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="2" class="text-center">Aucune commission calculée.</td>
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
