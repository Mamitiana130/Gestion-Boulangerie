<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.IngredientStockView" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="com.example.projets3s5.models.StockProduit" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Resultat recherche Production</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom de produit</th>
                        <th>quantite</th>

                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Produit> produits = (List<Produit>) request.getAttribute("produits");
                        List<StockProduit> stockProduits = (List<StockProduit>) request.getAttribute("stockProduits");


                        if (produits != null) {
                            for (int i = 0; i < produits.size(); i++) {
                                Produit produit = produits.get(i);
                    %>
                    <tr>
                        <td><%= produit.getIdProduit() %></td>
                        <td><%= produit.getNomProduit() %></td>
                        <td><%= stockProduits.get(i).getQuantite() %></td>


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
