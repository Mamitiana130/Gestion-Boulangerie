<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ include file="template/header.jsp" %>
<div class="container mt-5">
    <h2 class="text-center mb-4">Liste des Produits</h2>
    <div class="row">
        <%
            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
            List<Double> prixFabrications = (List<Double>) request.getAttribute("prixFabrications");

            if (produits != null && !produits.isEmpty()) {
                for (int i = 0; i < produits.size(); i++) {
                    Produit produit = produits.get(i);
        %>
        <div class="col-md-3 mb-4">
            <div class="card">
                <img src="${pageContext.request.contextPath}/assets/img/elements/pain.jpg"
                     class="card-img-top" alt="Image produit">
                <div class="card-body text-center">
                    <h5 class="card-title"><%= produit.getNomProduit() %></h5>
                    <p class="card-text"><%= produit.getDescription() %></p>
                    <p class="card-text"><strong>Type: <%= produit.getNomType() %></strong></p>
                    <p class="card-text">Prix vente: <span><%= produit.getPrixProduit() %>Ar</span></p>
                    <p class="card-text">Prix Fabrication: <span><%= prixFabrications.get(i)%>Ar</span></p>
                    <p class="card-text">Prix Revient: <span><%= produit.getPrixProduit()-prixFabrications.get(i)%>Ar</span></p>

                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="col-12 text-center">
            <p class="text-muted">Aucun produit disponible pour le moment.</p>
        </div>
        <% } %>
    </div>
</div>
<%@ include file="template/footer.jsp" %>
