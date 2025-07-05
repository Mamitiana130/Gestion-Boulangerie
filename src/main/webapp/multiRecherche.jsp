<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ page import="com.example.projets3s5.models.Type" %>
<%@ include file="template/header.jsp" %>

<div class="container">
    <!-- Contenu HTML -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Rapport de production:</h5>
        </div>
        <div class="card-body">
<%--            date debut--%>
            <form id="product-form" action="MouvementStockProduitServlet?mode=research" method="post">
                <div class="mb-3 row">
                    <label for="html5-datetime-local-input" class="col-md-2 col-form-label">DATE DEBUT</label>
                    <div class="col-md-10">
                        <input
                                class="form-control"
                                type="datetime-local"
                                id="html5-datetime-local-input"
                                name="dateDebut"
                        />
                    </div>
                </div>
                <%--date fin--%>
                <div class="mb-3 row">
                    <label for="html5-datetime-local-input1" class="col-md-2 col-form-label">DATE FIN</label>
                    <div class="col-md-10">
                        <input
                                class="form-control"
                                type="datetime-local"
                                id="html5-datetime-local-input1"
                                name="dateFin"
                        />
                    </div>
                </div>
                <!-- Type -->
                <div class="mb-3">
                    <label for="product-type" class="form-label">Type :</label>
                    <select name="idType" id="product-type" class="form-select" required>

                        <%
                            List<Type> types = (List<Type>) request.getAttribute("types");
                            for (int i = 0; i < types.size(); i++) {
                                Type type = types.get(i);%>
                        <option value="<%= type.getIdType() %>"><%= type.getNomType() %></option>
                        <% } %>
                    </select>
                </div>
                <!-- triage -->
                <div class="mb-3">
                    <label for="product-trie" class="form-label">TRIER PAR :</label>
                    <select name="trie" id="product-trie" class="form-select" required>
                        <option value="desc">les plus vendues</option>
                        <option value="asc">les moins vendues</option>
                    </select>
                </div>



                <!-- Bouton confirmer -->
                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>



