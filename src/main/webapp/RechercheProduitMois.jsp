<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ page import="com.example.projets3s5.models.Type" %>
<%@ include file="template/header.jsp" %>
<%

%>
<div class="container">
    <!-- Contenu HTML -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Produit du mois:</h5>
        </div>
        <div class="card-body">
            <%--            date debut--%>
            <form id="product-form" action="ResultProduitMoisServlet?mode=research" method="post">
                <%--date fin--%>

                <!-- Type -->

                <div class="mb-3">
                    <label for="product-ingredient" class="form-label">Mois :</label>
                    <select name="mois" id="product-ingredient" class="form-select" >
                        <option value="">Tous</option>

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
                        <option value="">Tous</option>

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
                <!-- Bouton confirmer -->
                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>
