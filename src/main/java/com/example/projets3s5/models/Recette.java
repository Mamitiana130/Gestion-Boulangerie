package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Recette {
    private int idIngredient;
    private int idProduit;
    private double quantiteRecquise;

    // Constructeurs
    public Recette() {}

    public Recette(int idIngredient, int idProduit, double quantiteRecquise) {
        this.idIngredient = idIngredient;
        this.idProduit = idProduit;
        this.quantiteRecquise = quantiteRecquise;
    }

    // Getters et Setters
    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public double getQuantiteRecquise() {
        return quantiteRecquise;
    }

    public void setQuantiteRecquise(double quantiteRecquise) {
        this.quantiteRecquise = quantiteRecquise;
    }

    // Méthode pour récupérer toutes les recettes
    public static List<Recette> getAll(Connection connection) throws SQLException {
        List<Recette> recettes = new ArrayList<>();
        String query = "SELECT idIngredient, idProduit, quantiteRecquise FROM Recette";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Recette recette = new Recette();
                recette.setIdIngredient(resultSet.getInt("idIngredient"));
                recette.setIdProduit(resultSet.getInt("idProduit"));
                recette.setQuantiteRecquise(resultSet.getDouble("quantiteRecquise"));
                recettes.add(recette);
            }
        }
        return recettes;
    }

    // Méthode pour récupérer les recettes d'un produit spécifique
    public static List<Recette> getByProduit(Connection connection, int idProduit) throws SQLException {
        List<Recette> recettes = new ArrayList<>();
        String query = "SELECT idIngredient, idProduit, quantiteRecquise FROM Recette WHERE idProduit = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idProduit);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Recette recette = new Recette();
                    recette.setIdIngredient(resultSet.getInt("idIngredient"));
                    recette.setIdProduit(resultSet.getInt("idProduit"));
                    recette.setQuantiteRecquise(resultSet.getDouble("quantiteRecquise"));
                    recettes.add(recette);
                }
            }
        }
        return recettes;
    }

    public static void insert(Connection connection, Recette recette) throws SQLException {
        String sql = "INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, recette.getIdIngredient());
            preparedStatement.setInt(2, recette.getIdProduit());
            preparedStatement.setDouble(3, recette.getQuantiteRecquise());

            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour mettre à jour une recette existante
    public static void update(Connection connection, int idIngredient, int idProduit, double quantiteRecquise) throws SQLException {
        String query = "UPDATE Recette SET quantiteRecquise = ? WHERE idIngredient = ? AND idProduit = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, quantiteRecquise);
            preparedStatement.setInt(2, idIngredient);
            preparedStatement.setInt(3, idProduit);
            preparedStatement.executeUpdate();
        }
    }

    // Méthode pour supprimer une recette
    public static void delete(Connection connection, int idIngredient, int idProduit) throws SQLException {
        String query = "DELETE FROM Recette WHERE idIngredient = ? AND idProduit = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idIngredient);
            preparedStatement.setInt(2, idProduit);
            preparedStatement.executeUpdate();
        }
    }
}
