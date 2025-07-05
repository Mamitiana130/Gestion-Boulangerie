<%@ page import="com.example.projets3s5.models.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.projets3s5.models.Ingredient" %>
<%@ page import="com.example.projets3s5.models.Type" %>
<%@ page import="com.example.projets3s5.models.Parfum" %>
<%@ include file="template/header.jsp" %>

<div class="container">
  <!-- Contenu HTML -->
  <div class="card mt-4">
    <div class="card-header">
      <h5>Rapport de vente:</h5>
    </div>
    <div class="card-body">
      <%--            date debut--%>
      <form id="product-form" action="ResultVenteServlet?mode=research" method="post">
        <%--date fin--%>

        <!-- Type -->
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
          <div class="mb-3">
            <label for="product-parfum" class="form-label">Parfum :</label>
            <select name="idParfum" id="product-parfum" class="form-select" >
              <option value="">Tous</option>

              <%
                List<Parfum> parfums =(List<Parfum>) request.getAttribute("parfums");
                for (int i = 0; i < parfums.size(); i++)
                {
                  Parfum parfum = parfums.get(i);
              %>
              <option value="<%= parfum.getIdParfum() %>"><%= parfum.getNomParfum() %></option>
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
