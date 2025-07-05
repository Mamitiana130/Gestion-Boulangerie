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
            <h5><%= "i".equals(request.getAttribute("mode")) ? "Ajout de nouveau ingrédient" : "Modification d'un ingrédient" %>:</h5>
        </div>
        <div class="card-body">
            <form id="ingredient-form" action="IngredientServlet?mode=<%= request.getAttribute("mode") %>" method="post">
                <%
                    Ingredient ingredient = (Ingredient) request.getAttribute("ingredientChosen");
                    boolean isUpdateMode = "u".equals(request.getAttribute("mode"));
                %>

                <% if (isUpdateMode) { %>
                <input type="hidden" name="idIngredient" value="<%= ingredient.getIdIngredient() %>">
                <% } %>

                <div class="mb-3">
                    <label for="ingredient-name" class="form-label">Nom Ingrédient :</label>
                    <input
                            name="nomIngredient"
                            type="text"
                            id="ingredient-name"
                            class="form-control"
                            placeholder="Entrez le nom de l'ingrédient"
                            value="<%= isUpdateMode ? ingredient.getNomIngredient() : "" %>"
                            required>
                </div>

                <div class="mb-3">
                    <label for="product-price" class="form-label">Prix Ingrédient :</label>
                    <input
                            name="prixIngredient"
                            type="text"
                            id="product-price"
                            class="form-control"
                            placeholder="Entrez le prix"
                            value="<%= isUpdateMode ? ingredient.getPrixIngredient() : "" %>"
                            required>
                </div>

                <div class="mb-3">
                    <label for="ingredient-unite" class="form-label">Unité :</label>
                    <select name="idUnite" id="ingredient-unite" class="form-select" required>
                        <%
                            List<Unite> unites = (List<Unite>) request.getAttribute("unites");
                            for (Unite unite : unites) {
                                boolean isSelected = isUpdateMode && unite.getIdUnite() == ingredient.getIdUnite();
                        %>
                        <option value="<%= unite.getIdUnite() %>" <%= isSelected ? "selected" : "" %>><%= unite.getNomUnite() %></option>
                        <% } %>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>
