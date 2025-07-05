<%@ page import="com.example.projets3s5.models.MouvementStockProduit" %>
<%@ page import="java.util.List" %>
<%@ include file="template/header.jsp" %>

<div class="container mt-4">
    <h2>Resultats de la recherche</h2>
    <table class="table table-dark">
        <thead>
        <tr>
            <th>ID</th>
            <th>nom</th>
            <th>Situation</th>
            <th>Date</th>
            <th>ID Produit</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<MouvementStockProduit> mouvements = (List<MouvementStockProduit>) request.getAttribute("mouvements");
            if (mouvements != null && !mouvements.isEmpty()) {
                for (MouvementStockProduit mouvement : mouvements) {
        %>
        <tr>
            <td><%= mouvement.getIdMouvementStockIngredient() %></td>
            <td><%= mouvement.getQuantite() %></td>
            <td><%= mouvement.getSituation() %></td>
            <td><%= mouvement.getDateMouvement() %></td>
            <td><%= mouvement.getIdProduit() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="5" class="text-center">Aucun résultat trouvé</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

<%@ include file="template/footer.jsp" %>
