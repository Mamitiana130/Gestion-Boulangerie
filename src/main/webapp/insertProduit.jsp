<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ page import="com.example.projets3s5.models.Type" %>
<%@ include file="template/header.jsp" %>

<div class="container">
    <!-- Contenu HTML -->
    <div class="card mt-4">
        <div class="card-header">
            <h5>Ajout de nouveau produit:</h5>
        </div>
        <div class="card-body">
            <form id="product-form" action="ProduitServlet?mode=i" method="post">
                <!-- Nom du produit -->
                <div class="mb-3">
                    <label for="product-name" class="form-label">Nom produit :</label>
                    <input name="nomProduit" type="text" id="product-name" class="form-control" placeholder="Entrez le nom du produit" required>
                </div>

                <!-- Prix du produit -->
                <div class="mb-3">
                    <label for="product-price" class="form-label">Prix produit :</label>
                    <input name="prixProduit" type="text" id="product-price" class="form-control" placeholder="Entrez le prix" required>
                </div>

                <!-- Description -->
                <div class="mb-3">
                    <label for="product-description" class="form-label">Description :</label>
                    <textarea name="description" id="product-description" class="form-control" rows="3" placeholder="Entrez une description" required></textarea>
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

                <!-- Ingrédient -->
                <div class="mb-3">
                    <label for="ingredient-select" class="form-label">Ingredient :</label>
                    <div class="d-flex">
                        <select id="ingredient-select" class="form-select">
                                <%
                                 List<Ingredient> ingredients = (List<Ingredient>) request.getAttribute("ingredients");
                                    for (int i = 0; i < ingredients.size(); i++) {
                                        Ingredient ingredient = ingredients.get(i);%>
                                        <option value="<%= ingredient.getIdIngredient() %>"><%= ingredient.getNomIngredient() %></option>
                                <%
                                    }
                                %>
                        </select>
                        <button type="button" id="add-ingredient" class="btn btn-primary ms-2">Ajouter</button>
                    </div>
                </div>

                <!-- Liste des ingrédients -->
                <div id="ingredients-list" class="mb-3"></div>

                <!-- Bouton confirmer -->
                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>

<!-- CSS pour styliser la liste des ingrédients -->
<style>
    #ingredients-list div {
        display: flex;
        align-items: center;
        padding: 5px;
        border: 1px solid #ddd;
        border-radius: 5px;
        margin-bottom: 5px;
        background-color: #f9f9f9;
    }

    #ingredients-list .form-control {
        width: auto;
        flex-grow: 1;
        margin-right: 10px;
    }
</style>

<!-- JavaScript -->
<script>
    document.getElementById("add-ingredient").addEventListener("click", function () {
        const ingredientSelect = document.getElementById("ingredient-select");
        const ingredientValue = ingredientSelect.value;
        const ingredientText = ingredientSelect.options[ingredientSelect.selectedIndex].text;

        if (!ingredientValue) {
            alert("Veuillez sélectionner un ingrédient !");
            return;
        }

        const ingredientList = document.getElementById("ingredients-list");

        // Crée une ligne pour l'ingrédient
        const ingredientRow = document.createElement("div");

        // Champ caché pour l'id de l'ingrédient
        const ingredientIdInput = document.createElement("input");
        ingredientIdInput.type = "hidden";
        ingredientIdInput.name = "ingredientId"; // Correspond au servlet
        ingredientIdInput.value = ingredientValue;

        // Nom de l'ingrédient (affiché uniquement)
        const ingredientName = document.createElement("span");
        ingredientName.className = "me-3";
        ingredientName.textContent = ingredientText;

        // Input pour la quantité
        const ingredientQuantity = document.createElement("input");
        ingredientQuantity.type = "text";
        ingredientQuantity.name = "quantite"; // Correspond au servlet
        ingredientQuantity.className = "form-control me-2";
        ingredientQuantity.placeholder = "Quantité";
        ingredientQuantity.required = true;

        // Bouton supprimer
        const deleteButton = document.createElement("button");
        deleteButton.type = "button";
        deleteButton.className = "btn btn-danger";
        deleteButton.textContent = "Supprimer";
        deleteButton.addEventListener("click", function () {
            ingredientList.removeChild(ingredientRow);
        });

        // Ajout des éléments dans la ligne
        ingredientRow.appendChild(ingredientIdInput);
        ingredientRow.appendChild(ingredientName);
        ingredientRow.appendChild(ingredientQuantity);
        ingredientRow.appendChild(deleteButton);

        // Ajout de la ligne à la liste
        ingredientList.appendChild(ingredientRow);

        // Réinitialise le select
        ingredientSelect.selectedIndex = 0;
    });
</script>
