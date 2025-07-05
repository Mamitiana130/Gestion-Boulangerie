<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.HistoriquePrixProduit" %>
<%@ page import="java.util.List" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Historique des Prix des Produits</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>ID Historique</th>
                        <th>ID Produit</th>
                        <th>Prix</th>
                        <th>Date de Changement</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<HistoriquePrixProduit> histo = (List<HistoriquePrixProduit>) request.getAttribute("histo");

                        if (histo != null && !histo.isEmpty()) {
                            for (HistoriquePrixProduit record : histo) {
                    %>
                    <tr>
                        <td><%= record.getIdHistorique() %></td>
                        <td><%= record.getIdProduit() %></td>
                        <td><%= record.getPrix() %></td>
                        <td><%= record.getDateChangement() %></td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="4" class="text-center">Aucun historique de prix disponible.</td>
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
