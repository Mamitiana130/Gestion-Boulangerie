<%--
  Created by IntelliJ IDEA.
  User: Mamt
  Date: 10/11/2024
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<html lang="en" class="light-style customizer-hide" dir="ltr" data-theme="theme-default" data-assets-path="${pageContext.request.contextPath}/assets/" data-template="vertical-menu-template-free">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

    <title>Boulangerie</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon/favicon.ico" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap" rel="stylesheet" />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

    <!-- Page CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/vendor/css/pages/page-auth.css" />

    <!-- Helpers -->
    <script src="${pageContext.request.contextPath}/assets/vendor/js/helpers.js"></script>

    <!-- Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <script src="${pageContext.request.contextPath}/assets/js/config.js"></script>
</head>
<body>
<!-- Layout wrapper -->
<div class="layout-wrapper layout-content-navbar">
    <div class="layout-container">
        <!-- Menu -->

        <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
            <div class="app-brand demo">
                <a href="index.jsp" class="app-brand-link">
                    <span class="app-brand-text demo menu-text fw-bolder ms-2">Boulangerie</span>
                </a>

                <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                    <i class="bx bx-chevron-left bx-sm align-middle"></i>
                </a>
            </div>

            <div class="menu-inner-shadow"></div>

            <ul class="menu-inner py-1">
                <li class="menu-item">
                    <a href="accueil.jsp" class="menu-link">
                        <i class="menu-icon tf-icons bx bx-home-circle"></i>
                        <div data-i18n="Analytics">Accueil</div>
                    </a>
                </li>

                <!-- Ingredient -->
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class='bx bx-grid-alt'></i>
                        <div data-i18n="Layouts">Ingredient</div>
                    </a>
                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="IngredientServlet?mode=r" class="menu-link">
                                <div>Listes des Ingredients</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="InsertIngredientServlet?mode=i" class="menu-link">
                                <div>Ajouter des Ingredients</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="IngredientStockViewServlet?mode=r" class="menu-link">
                                <div>Suivi stock Ingredient</div>
                            </a>
                        </li>
                    </ul>

                </li>
                <!-- Produit -->
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class='bx bx-food-menu'></i>
                        <div data-i18n="Layouts">Produit</div>
                    </a>

                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="ProduitServlet?mode=r" class="menu-link">
                                <div>Liste des Produits</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="InsertProduitServlet?mode=i" class="menu-link">
                                <div>Ajouter Produit</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="RechercheProduitMoisServlet?mode=s" class="menu-link">
                                <div>Rechercher produit mois</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="InsertProduitMoisServlet?mode=i" class="menu-link">
                                <div>Ajouter produit mois</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="Result2024" class="menu-link">
                                <div> Produit mois 2024</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="InsertPrixProduitServlet?mode=i" class="menu-link">
                                <div>Insert Prix Produit </div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="HistoriquePrixProduitServlet?mode=s" class="menu-link">
                                <div>Historique </div>
                            </a>
                        </li>
                    </ul>

                </li>

                <!-- Production -->
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class='bx bxs-store'></i>
                        <div data-i18n="Layouts">Production</div>
                    </a>

                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="MouvementStockProduitServlet?mode=s" class="menu-link">
                                <div>Rapport de production</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="RechercheProductionServlet?mode=s" class="menu-link">
                                <div>Rechercher Production</div>
                            </a>
                        </li>
                    </ul>
                </li>
                
                <%--Vente--%>
                <li class="menu-item">
                    <a href="javascript:void(0);" class="menu-link menu-toggle">
                        <i class='bx bx-money'></i>
                        <div data-i18n="Layouts">Vente</div>
                    </a>

                    <ul class="menu-sub">
                        <li class="menu-item">
                            <a href="RechercheVenteServlet?mode=s" class="menu-link">
                                <div>Rechercher ventes</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="insertVenteServlet?mode=i" class="menu-link">
                                <div>Ajouter ventes</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="RechercheVenteDateServlet?mode=s" class="menu-link">
                                <div>Rechercher par date </div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="RechercheCommissionServlet?mode=s" class="menu-link">
                                <div>Rechercher commission</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="RechercheCommissionGenreServlet?mode=s" class="menu-link">
                                <div>Rechercher commission par genre </div>
                            </a>
                        </li>
                    </ul>

                </li>
            </ul>
        </aside>
        <!-- / Menu -->
        <!-- Layout container -->
        <div class="layout-page">
            <!-- Navbar -->

            <nav
                    class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                    id="layout-navbar"
            >
                <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                    <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                        <i class="bx bx-menu bx-sm"></i>
                    </a>
                </div>

                <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                    <!-- Search -->
                    <div class="navbar-nav align-items-center">
                        <div class="nav-item d-flex align-items-center">
                            <i class="bx bx-search fs-4 lh-0"></i>
                            <input
                                    type="text"
                                    class="form-control border-0 shadow-none"
                                    placeholder="Search..."
                                    aria-label="Search..."
                            />
                        </div>
                    </div>
                    <!-- /Search -->

                    <ul class="navbar-nav flex-row align-items-center ms-auto">


                        <!-- User -->
                        <li class="nav-item navbar-dropdown dropdown-user dropdown">
                            <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                                <div class="avatar avatar-online">
                                    <img src="${pageContext.request.contextPath}/assets/img/avatars/2.jpg" alt class="w-px-40 h-auto rounded-circle" />
                                </div>
                            </a>
                            <ul class="dropdown-menu dropdown-menu-end">
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <div class="d-flex">
                                            <div class="flex-shrink-0 me-3">
                                                <div class="avatar avatar-online">
                                                    <img src="${pageContext.request.contextPath}/assets/img/avatars/2.jpg" alt class="w-px-40 h-auto rounded-circle" />
                                                </div>
                                            </div>
                                            <div class="flex-grow-1">
                                                <span class="fw-semibold d-block">Mamitiana</span>
                                                <small class="text-muted">Admin</small>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <i class="bx bx-user me-2"></i>
                                        <span class="align-middle">My Profile</span>
                                    </a>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="#">
                                        <i class="bx bx-cog me-2"></i>
                                        <span class="align-middle">Settings</span>
                                    </a>
                                </li>
                                <li>
                                    <div class="dropdown-divider"></div>
                                </li>
                                <li>
                                    <a class="dropdown-item" href="LogoutServlet">
                                        <i class="bx bx-power-off me-2"></i>
                                        <span class="align-middle">Log Out</span>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <!--/ User -->
                    </ul>
                </div>
            </nav>

            <!-- / Navbar -->
