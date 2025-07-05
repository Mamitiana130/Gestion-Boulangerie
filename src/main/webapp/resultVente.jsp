<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.IngredientStockView" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="com.example.projets3s5.models.StockProduit" %>
<%@ page import="com.example.projets3s5.models.Vente" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Resultat recherche vente</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom de produit</th>
                        <th>quantite</th>
                        <th>date vente</th>


                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Vente> ventes = (List<Vente>) request.getAttribute("ventes");


                        if (ventes != null) {
                            for (int i = 0; i < ventes.size(); i++) {
                                Vente vente = ventes.get(i);
                    %>
                    <tr>
                        <td><%= vente.getIdVente() %></td>
                        <td><%= vente.getNomProduit() %></td>
                        <td><%= vente.getQuantite() %></td>
                        <td><%= vente.getDateVente() %></td>


                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="3" class="text-center">Aucun stock disponible.</td>
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
