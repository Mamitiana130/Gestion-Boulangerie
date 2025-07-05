package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDuMois {
    private int idProduitDuMois;
    private int mois;
    private int annee;
    private Produit produit; // Association avec la classe Produit

    // Constructeurs
    public ProduitDuMois() {}

    public ProduitDuMois(int idProduitDuMois, int mois, int annee, Produit produit) {
        this.idProduitDuMois = idProduitDuMois;
        this.mois = mois;
        this.annee = annee;
        this.produit = produit;
    }

    public ProduitDuMois(int mois, int annee, Produit produit) {
        this.mois = mois;
        this.annee = annee;
        this.produit = produit;
    }

    // Getters et Setters
    public int getIdProduitDuMois() {
        return idProduitDuMois;
    }

    public void setIdProduitDuMois(int idProduitDuMois) {
        this.idProduitDuMois = idProduitDuMois;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    // Méthodes CRUD
    public static List<ProduitDuMois> getAll(Connection connection) throws SQLException {
        List<ProduitDuMois> produitsDuMois = new ArrayList<>();
        String query = "SELECT pdm.idProduitDuMois, pdm.mois, pdm.annee, " +
                "p.idProduit, p.nomProduit, p.description, p.prixProduit, p.photoPath, p.idType, t.nomType " +
                "FROM ProduitDuMois pdm " +
                "INNER JOIN Produit p ON pdm.idProduit = p.idProduit " +
                "INNER JOIN Type t ON p.idType = t.idType " +
                "ORDER BY pdm.annee DESC, pdm.mois DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(resultSet.getInt("idProduit"));
                produit.setNomProduit(resultSet.getString("nomProduit"));
                produit.setDescription(resultSet.getString("description"));
                produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                produit.setPhotoPath(resultSet.getString("photoPath"));
                produit.setIdType(resultSet.getInt("idType"));
                produit.setNomType(resultSet.getString("nomType"));

                ProduitDuMois produitDuMois = new ProduitDuMois();
                produitDuMois.setIdProduitDuMois(resultSet.getInt("idProduitDuMois"));
                produitDuMois.setMois(resultSet.getInt("mois"));
                produitDuMois.setAnnee(resultSet.getInt("annee"));
                produitDuMois.setProduit(produit);

                produitsDuMois.add(produitDuMois);
            }
        }
        return produitsDuMois;
    }

//    public static int insert(Connection connection, ProduitDuMois produitDuMois) throws SQLException {
//        String sql = "INSERT INTO ProduitDuMois (idProduit, mois, annee) VALUES (?, ?, ?) RETURNING idProduitDuMois";
//
//        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.setInt(1, produitDuMois.getProduit().getIdProduit());
//            preparedStatement.setInt(2, produitDuMois.getMois());
//            preparedStatement.setInt(3, produitDuMois.getAnnee());
//
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    return resultSet.getInt("idProduitDuMois");
//                } else {
//                    throw new SQLException("Insertion du produit du mois échouée, aucun ID généré.");
//                }
//            }
//        }
//    }

    public static int insert(Connection connection, ProduitDuMois produitDuMois) throws SQLException {
        String checkQuery = "SELECT 1 FROM ProduitDuMois WHERE idProduit = ? AND mois = ? AND annee = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
            checkStmt.setInt(1, produitDuMois.getProduit().getIdProduit());
            checkStmt.setInt(2, produitDuMois.getMois());
            checkStmt.setInt(3, produitDuMois.getAnnee());

            try (ResultSet resultSet = checkStmt.executeQuery()) {
                if (resultSet.next()) {

                    return 0;
                }
            }
        }

        String insertQuery = "INSERT INTO ProduitDuMois (idProduit, mois, annee) VALUES (?, ?, ?) RETURNING idProduitDuMois";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, produitDuMois.getProduit().getIdProduit());
            preparedStatement.setInt(2, produitDuMois.getMois());
            preparedStatement.setInt(3, produitDuMois.getAnnee());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idProduitDuMois");
                } else {
                    throw new SQLException("Insertion du produit du mois échouée, aucun ID généré.");
                }
            }
        }
    }


    public static List<ProduitDuMois> getWithCriteriasMoisAnnee(Connection connection, Integer mois, Integer annee) throws SQLException {
        List<ProduitDuMois> produitsDuMois = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT pdm.idProduitDuMois, pdm.mois, pdm.annee, " +
                        "p.idProduit, p.nomProduit, p.description, p.prixProduit, p.photoPath, p.idType, t.nomType " +
                        "FROM ProduitDuMois pdm " +
                        "INNER JOIN Produit p ON pdm.idProduit = p.idProduit " +
                        "INNER JOIN Type t ON p.idType = t.idType "
        );

        if (mois != null || annee != null) {
            query.append("WHERE ");
            if (mois != null) {
                query.append("pdm.mois = ? ");
            }
            if (annee != null) {
                if (mois != null) {
                    query.append("AND ");
                }
                query.append("pdm.annee = ? ");
            }
        }

        query.append("ORDER BY pdm.annee DESC, pdm.mois DESC");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;

            if (mois != null) {
                preparedStatement.setInt(parameterIndex++, mois);
            }
            if (annee != null) {
                preparedStatement.setInt(parameterIndex++, annee);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produit produit = new Produit();
                    produit.setIdProduit(resultSet.getInt("idProduit"));
                    produit.setNomProduit(resultSet.getString("nomProduit"));
                    produit.setDescription(resultSet.getString("description"));
                    produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                    produit.setPhotoPath(resultSet.getString("photoPath"));
                    produit.setIdType(resultSet.getInt("idType"));
                    produit.setNomType(resultSet.getString("nomType"));

                    ProduitDuMois produitDuMois = new ProduitDuMois();
                    produitDuMois.setIdProduitDuMois(resultSet.getInt("idProduitDuMois"));
                    produitDuMois.setProduit(produit);
                    produitDuMois.setMois(resultSet.getInt("mois"));
                    produitDuMois.setAnnee(resultSet.getInt("annee"));

                    produitsDuMois.add(produitDuMois);
                }
            }
        }
        return produitsDuMois;
    }

    public static List<ProduitDuMois> get2024(Connection connection) throws SQLException {
        List<ProduitDuMois> produitsDuMois = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT pdm.idProduitDuMois, pdm.mois, pdm.annee, " +
                        "p.idProduit, p.nomProduit, p.description, p.prixProduit, p.photoPath, p.idType, t.nomType " +
                        "FROM ProduitDuMois pdm " +
                        "INNER JOIN Produit p ON pdm.idProduit = p.idProduit " +
                        "INNER JOIN Type t ON p.idType = t.idType where pdm.annee=2024 "
        );



        query.append("ORDER BY pdm.annee DESC, pdm.mois DESC");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {




            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produit produit = new Produit();
                    produit.setIdProduit(resultSet.getInt("idProduit"));
                    produit.setNomProduit(resultSet.getString("nomProduit"));
                    produit.setDescription(resultSet.getString("description"));
                    produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                    produit.setPhotoPath(resultSet.getString("photoPath"));
                    produit.setIdType(resultSet.getInt("idType"));
                    produit.setNomType(resultSet.getString("nomType"));

                    ProduitDuMois produitDuMois = new ProduitDuMois();
                    produitDuMois.setIdProduitDuMois(resultSet.getInt("idProduitDuMois"));
                    produitDuMois.setProduit(produit);
                    produitDuMois.setMois(resultSet.getInt("mois"));
                    produitDuMois.setAnnee(resultSet.getInt("annee"));

                    produitsDuMois.add(produitDuMois);
                }
            }
        }
        return produitsDuMois;
    }

}
