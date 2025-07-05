<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.IngredientStockView" %>
<%@ page import="java.util.List" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Stock des Ingrédients</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>Nom de l'Ingrédient</th>
                        <th>Quantité</th>
                        <th>Unité</th>
                        <th>Statut</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<IngredientStockView> stocks = (List<IngredientStockView>) request.getAttribute("stocks");
                        List<String> statuts = (List<String>) request.getAttribute("statuts");

                        if (stocks != null) {
                            for (int i = 0; i < stocks.size(); i++) {
                                IngredientStockView stock = stocks.get(i);
                    %>
                    <tr>
                        <td><%= stock.getNomIngredient() %></td>
                        <td><%= stock.getQuantite() %></td>
                        <td><%= stock.getNomUnite() %></td>
                        <td><%= statuts.get(i) %></td>

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
