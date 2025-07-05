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
            <h5>Commissions par genre:</h5>
        </div>
        <div class="card-body">
            <%--            date debut--%>
            <form id="product-form" action="ResultCommissionGenreServlet?mode=research" method="post">


                <div class="mb-3 row">
                    <label for="html5-datetime-local-input1" class="col-md-2 col-form-label">DATE DEBUT</label>
                    <div class="col-md-10">
                        <input
                                class="form-control"
                                type="datetime-local"
                                id="html5-datetime-local-input1"
                                name="dateDebut"
                        />
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="html5-datetime-local-input2" class="col-md-2 col-form-label">DATE FIN</label>
                    <div class="col-md-10">
                        <input
                                class="form-control"
                                type="datetime-local"
                                id="html5-datetime-local-input2"
                                name="dateFin"
                        />
                    </div>
                </div>

                <!-- Bouton confirmer -->
                <button type="submit" class="btn btn-success">Confirmer</button>
            </form>
        </div>
    </div>
</div>

<%@ include file="template/footer.jsp" %>
