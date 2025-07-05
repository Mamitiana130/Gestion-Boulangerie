<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.*" %>
<%@ include file="template/header.jsp" %>

<div class="container">
    <!-- Contenu HTML -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Ajout de nouveau vente</h5>
        </div>
        <div class="card-body">
            <form id="ingredient-form" action="VenteServlet?mode=i" method="post">
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
                    <label for="idClient" class="form-label">Client :</label>
                    <select name="idClient" id="idClient" class="form-select" required>
                        <%
                            List<Client> clients = (List<Client>) request.getAttribute("clients");
                            for (Client client : clients) {
                        %>
                        <option value="<%= client.getIdClient() %>"><%= client.getNomClient() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="idVendeur" class="form-label">Vendeur :</label>
                    <select name="idVendeur" id="idVendeur" class="form-select" required>
                        <%
                            List<Vendeur> vendeurs = (List<Vendeur>) request.getAttribute("vendeurs");
                            for (Vendeur vendeur : vendeurs) {
                        %>
                        <option value="<%= vendeur.getIdVendeur() %>"><%= vendeur.getNomVendeur() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="ingredient-name" class="form-label">quantite :</label>
                    <input
                            name="quantite"
                            type="number"
                            id="ingredient-name"
                            class="form-control"
                            placeholder="Entrez la quantite requise"
                            required>
                </div>
                <div class="mb-3 row">
                    <label for="html5-datetime-local-input1" class="col-md-2 col-form-label">DATE DE VENTE</label>
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
