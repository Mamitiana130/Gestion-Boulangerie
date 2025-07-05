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
            <h5>Ajout de nouveau produit du mois</h5>
        </div>
        <div class="card-body">
            <form id="ingredient-form" action="ProduitMoisServlet?mode=i" method="post">
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
                    <label for="product-ingredient" class="form-label">Mois :</label>
                    <select name="mois" id="product-ingredient" class="form-select" >
                        <%
                            String[] mois= (String[]) request.getAttribute("mois");
                            for (int i = 0; i < mois.length; i++)
                            {
                        %>
                        <option value="<%= i+1 %>"><%=mois[i]  %></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <!-- triage -->
                <div class="mb-3">
                    <label for="product-type" class="form-label">Annee :</label>
                    <select name="annee" id="product-type" class="form-select" >
                        <%
                            int[] annee=(int[]) request.getAttribute("annee");
                            for (int i = 0; i < annee.length; i++)
                            {
                        %>
                        <option value="<%= annee[i] %>"><%= annee[i] %></option>
                        <%
                            }
                        %>
                    </select>
                </div>

                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>
