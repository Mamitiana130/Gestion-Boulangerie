<%@ page import="com.example.projets3s5.models.Membre" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="template/header.jsp" %>

    <%
        HttpSession sessionMembre = request.getSession(false);
        Membre membre = (Membre) sessionMembre.getAttribute("membre");
    %>


<%--</div>--%>
<div class="content-wrapper">
    <!-- Content -->

    <div class="container-xxl flex-grow-1 container-p-y">
        <div class="row" data-masonry='{"percentPosition": true }'>
            <div class="col-sm-6 col-lg-4 mb-4">
                <div class="card">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/elements/pain1.jpg" alt="Card image cap" />
                    <div class="card-body">
                        <h5 class="card-title">Une solution sur-mesure pour votre boulangerie</h5>
                        <p class="card-text">
                            Planifiez, organisez et suivez vos activites en toute serenite avec notre interface dediee.
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 mb-4">
                <div class="card p-3">
                    <figure class="p-3 mb-0">
                        <blockquote class="blockquote">
                            <h3>Bienvenue sur notre site <%= membre.getPrenomMembre() %>!</h3>
                            <p> Explorez, decouvrez et profitez de nos services conçus specialement pour vous.</p>
                        </blockquote>
                    </figure>
                </div>
                <div class="card mt-3">
                    <div class="card-body">
                        <h5 class="card-title">Gestion simplifiee</h5>
                        <p class="card-text">
                            Optimisez la gestion quotidienne de votre boulangerie grace a des outils intuitifs et efficaces.
                        </p>
                    </div>
                </div>
                <div class="card mt-3">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/elements/pain2.jpg" alt="Card image cap" />
                    <div class="card-body">
                        <h5 class="card-title">Un nouveau produit à découvrir</h5>
                        <p class="card-text">
                            Découvrez notre dernière création qui saura ravir vos papilles.
                        </p>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-lg-4 mb-4">
                <div class="card">
                    <img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/elements/pain3.jpg" alt="Card image cap" />
                    <div class="card-body">
                        <h5 class="card-title">Pilotez votre boulangerie avec precision</h5>
                        <p class="card-text">
                            Accedez a toutes les donnees essentielles pour gerer vos produits, vos ventes et vos stocks en un seul endroit.                        </p>
                    </div>
                </div>
            </div>

<%--            <div class="col-sm-6 col-lg-4 mb-4">--%>
<%--                <div class="card">--%>
<%--                    <img class="card-img-top" src="${pageContext.request.contextPath}/assets/img/elements/pain2.jpg" alt="Card image cap" />--%>
<%--                </div>--%>
<%--            </div>--%>


    </div>
</div>

<%@ include file="template/footer.jsp" %>
