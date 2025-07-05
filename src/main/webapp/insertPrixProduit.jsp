<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ page import="com.example.projets3s5.models.Type" %>
<%@ page import="com.example.projets3s5.models.Unite" %>
<%@ include file="template/header.jsp" %>

<div class="container">
    <!-- Contenu HTML -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Ajout Prix Produit</h5>
        </div>
        <div class="card-body">
            <form id="ingredient-form" action="HistoriquePrixProduitServlet?mode=i" method="post">
                <div class="mb-3">
                    <label for="ingredient-unite" class="form-label">Produit :</label>
                    <select name="idProduit" id="ingredient-unite" class="form-select" required>
                        <%
                            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
                            for (Produit produit : produits) {
                        %>
                        <option value="<%= produit.getIdProduit() %>"><%= produit.getNomProduit() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="product-price" class="form-label">Prix produit :</label>
                    <input name="prixProduit" type="text" id="product-price" class="form-control" placeholder="Entrez le prix" required>
                </div>
                <div class="mb-3 row">
                    <label for="html5-datetime-local-input1" class="col-md-2 col-form-label">DATE </label>
                    <div class="col-md-10">
                        <input
                                class="form-control"
                                type="datetime-local"
                                id="html5-datetime-local-input1"
                                name="date"
                        />
                    </div>
                </div>

                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>
