<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ page import="com.example.projets3s5.models.Type" %>
<%@ include file="template/header.jsp" %>

<div class="container">
    <!-- Contenu HTML -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Recherche de production:</h5>
        </div>
        <div class="card-body">
            <%--            date debut--%>
            <form id="product-form" action="ResultProductionServlet?mode=research" method="post">
                <%--date fin--%>

                <!-- Type -->

                    <div class="mb-3">
                        <label for="product-ingredient" class="form-label">Ingredient :</label>
                        <select name="idIngredient" id="product-ingredient" class="form-select" >
                            <option value="">Tous</option>

                            <%
                                List<Ingredient> ingredients =(List<Ingredient>) request.getAttribute("ingredients");
                                for (int i = 0; i < ingredients.size(); i++)
                                {
                                    Ingredient ingredient = ingredients.get(i);
                            %>
                            <option value="<%= ingredient.getIdIngredient() %>"><%= ingredient.getNomIngredient() %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                <!-- triage -->
                    <div class="mb-3">
                        <label for="product-type" class="form-label">Type :</label>
                        <select name="idType" id="product-type" class="form-select" >
                            <option value="">Tous</option>


                            <%
                                List<Type> types =(List<Type>) request.getAttribute("types");
                                for (int i = 0; i < types.size(); i++)
                                {
                                    Type type = types.get(i);
                            %>
                            <option value="<%= type.getIdType() %>"><%= type.getNomType() %></option>
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
