<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.Vente" %>
<%@ page import="java.util.List" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Résultat de la recherche des ventes</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>ID Vente</th>
                        <th>Nom de produit</th>
                        <th>Quantité</th>
                        <th>Date de vente</th>
                        <th>Nom du client</th>
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
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="5" class="text-center">Aucune vente trouvée pour cette date.</td>
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
