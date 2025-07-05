<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.projets3s5.models.IngredientStockView" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ include file="template/header.jsp" %>

<div class="content-wrapper">
    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="card">
            <h5 class="card-header">Listes des Ingrédients</h5>
            <div class="table-responsive text-nowrap">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th>Nom de l'Ingrédient</th>
                        <th>Prix</th>
                        <th>Unité</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients");

                        if (ingredients != null) {
                            for (int i = 0; i < ingredients.size(); i++) {
                                Ingredient ing = ingredients.get(i);
                    %>
                    <tr>
                        <td><%= ing.getNomIngredient() %></td>
                        <td><%= ing.getPrixIngredient() %></td>
                        <td><%= ing.getIdUnite() %></td>
                        <td>
                            <a href="InsertIngredientServlet?mode=u&&idIngredient=<%= ing.getIdIngredient() %>"><button type="button" class="btn btn-success" >Update</button></a>
                            <a href="IngredientServlet?mode=d&&idIngredient=<%= ing.getIdIngredient() %>"><button type="button" class="btn btn-danger" >Delete</button></a>
                        </td>

                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="3" class="text-center">Aucun ingredient disponible.</td>
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
