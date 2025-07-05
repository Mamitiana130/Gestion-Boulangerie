package com.example.projets3s5.models;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Produit {
    private int idProduit;
    private String nomProduit;
    private String description;
    private String photoPath;
    private double prixProduit;
    private int idType;
    private String nomType;

    // Constructeurs
    public Produit() {}

    public Produit(int idProduit, String nomProduit, String description, double prixProduit, int idType, String nomType) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.description = description;
        this.prixProduit = prixProduit;
        this.idType = idType;
        this.nomType = nomType;
    }

    // Getters et Setters
    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }


    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public String getPhotoPath() {return photoPath;}

    public void setPhotoPath(String photoPath) {this.photoPath = photoPath;}


    public static List<Produit> getAll(Connection connection) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = " SELECT p.idProduit, p.nomProduit, p.description, p.prixProduit, p.idType, t.nomType FROM Produit p INNER JOIN Type t ON p.idType = t.idType ORDER BY idProduit ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Produit produit = new Produit();
                produit.setIdProduit(resultSet.getInt("idProduit"));
                produit.setNomProduit(resultSet.getString("nomProduit"));
                produit.setDescription(resultSet.getString("description"));
                produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                produit.setIdType(resultSet.getInt("idType"));
                produit.setNomType(resultSet.getString("nomType")); // Récupération du nom du type
                produits.add(produit);
            }
        }
        return produits;
    }

    public static Produit getProduitById(Connection connection, int idProduit) throws SQLException {
        Produit produit = null;
        String query = "SELECT p.idProduit, p.nomProduit, p.description, p.prixProduit, p.idType, t.nomType, p.photoPath " +
                "FROM Produit p INNER JOIN Type t ON p.idType = t.idType " +
                "WHERE p.idProduit = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idProduit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    produit = new Produit();
                    produit.setIdProduit(resultSet.getInt("idProduit"));
                    produit.setNomProduit(resultSet.getString("nomProduit"));
                    produit.setDescription(resultSet.getString("description"));
                    produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                    produit.setIdType(resultSet.getInt("idType"));
                    produit.setNomType(resultSet.getString("nomType"));
                    produit.setPhotoPath(resultSet.getString("photoPath"));
                }
            }
        }
        return produit;
    }

    public static List<Double> getPrixFabrication(Connection connection) throws SQLException {
        List<Double> prixFabricationList = new ArrayList<>();
        String query = "SELECT SUM(prixFabrication) AS totalPrix " +
                "FROM v_totalPrixFabrication " +
                "GROUP BY idProduit " +
                "ORDER BY idProduit ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Double totalPrix = resultSet.getDouble("totalPrix");
                prixFabricationList.add(totalPrix);
            }
        }
        return prixFabricationList;
    }

    public static int insert(Connection connection, Produit produit) throws SQLException {
        String sql = "INSERT INTO Produit (nomProduit, prixProduit, description, photoPath, idType) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING idProduit";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, produit.getNomProduit());
            preparedStatement.setDouble(2, produit.getPrixProduit());
            preparedStatement.setString(3, produit.getDescription());
            preparedStatement.setString(4, produit.getPhotoPath()); // Remplacez par une valeur si nécessaire
            preparedStatement.setInt(5, produit.getIdType());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("idProduit");
                } else {
                    throw new SQLException("Insertion du produit échouée, aucun ID généré.");
                }
            }
        }
    }

    public static List<Produit> getWithCritariasIngredientType(Connection connection, Integer idIngredient, Integer idType) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        StringBuilder query = new StringBuilder(
                "SELECT DISTINCT p.idProduit, p.nomProduit, p.description, p.prixProduit, p.idType, t.nomType " +
                        "FROM Produit p " +
                        "LEFT JOIN Recette r ON p.idProduit = r.idProduit " +
                        "LEFT JOIN Type t ON p.idType = t.idType " +
                        "WHERE 1=1"
        );

        // Ajout des filtres dynamiques
        if (idIngredient != null) {
            query.append(" AND r.idIngredient = ?");
        }
        if (idType != null) {
            query.append(" AND p.idType = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int paramIndex = 1;

            if (idIngredient != null) {
                preparedStatement.setInt(paramIndex++, idIngredient);
            }
            if (idType != null) {
                preparedStatement.setInt(paramIndex++, idType);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Produit produit = new Produit();
                    produit.setIdProduit(resultSet.getInt("idProduit"));
                    produit.setNomProduit(resultSet.getString("nomProduit"));
                    produit.setDescription(resultSet.getString("description"));
                    produit.setPrixProduit(resultSet.getDouble("prixProduit"));
                    produit.setIdType(resultSet.getInt("idType"));
                    produit.setNomType(resultSet.getString("nomType"));

                    produits.add(produit);
                }
            }
        }
        return produits;
    }

    public static List<Integer> getWithCritariasParfumType(Connection connection, Integer idType, Integer idParfum) throws SQLException {
        List<Integer> produits = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT idProduit FROM Produit WHERE 1=1");

        if (idType != null) {
            query.append(" AND idType = ?");
        }
        if (idParfum != null) {
            query.append(" AND idParfum = ?");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            int parameterIndex = 1;

            if (idType != null) {
                preparedStatement.setInt(parameterIndex++, idType);
            }
            if (idParfum != null) {
                preparedStatement.setInt(parameterIndex++, idParfum);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    produits.add(resultSet.getInt("idProduit"));
                }
            }
        }
        return produits;
    }

    public void updatePrixProduit(Connection connection, double nouveauPrix) throws SQLException {
        String updateQuery = "UPDATE Produit SET prixProduit = ? WHERE idProduit = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setDouble(1, nouveauPrix);
            preparedStatement.setInt(2, this.idProduit);

        }
    }

}
