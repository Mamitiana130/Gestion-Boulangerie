package com.example.projets3s5.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private int idIngredient;
    private String nomIngredient;
    private double prixIngredient;
    private int idUnite;

    // Getters et Setters
    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public double getPrixIngredient() {
        return prixIngredient;
    }

    public void setPrixIngredient(double prixIngredient) {
        this.prixIngredient = prixIngredient;
    }

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    // Fonction pour récupérer tous les ingrédients
    public static List<Ingredient> getAll(Connection connection) throws SQLException {
        List<Ingredient> ingredients = new ArrayList<>();
        String sql = "SELECT * FROM Ingredient";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Ingredient ingredient = new Ingredient();
                ingredient.setIdIngredient(resultSet.getInt("idIngredient"));
                ingredient.setNomIngredient(resultSet.getString("nomIngredient"));
                ingredient.setPrixIngredient(resultSet.getDouble("prixIngredient"));
                ingredient.setIdUnite(resultSet.getInt("idUnite"));

                ingredients.add(ingredient);
            }
        }

        return ingredients;
    }
    public static void delete(Connection connection, int idIngredient) throws SQLException {
        String sql = "DELETE FROM ingredient WHERE idIngredient = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idIngredient);
            preparedStatement.executeUpdate();
        }
    }

    public static void insert(Connection connection, Ingredient ingredient) throws SQLException {
        String sql = "INSERT INTO Ingredient (nomIngredient, prixIngredient, idUnite) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ingredient.getNomIngredient());
            preparedStatement.setDouble(2, ingredient.getPrixIngredient());
            preparedStatement.setInt(3, ingredient.getIdUnite());

            preparedStatement.executeUpdate();
        }
    }

    public static Ingredient getByIdIngredient(Connection connection, int idIngredient) throws SQLException {
        String sql = "SELECT * FROM Ingredient WHERE idIngredient = ?";
        Ingredient ingredient = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idIngredient);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    ingredient = new Ingredient();
                    ingredient.setIdIngredient(resultSet.getInt("idIngredient"));
                    ingredient.setNomIngredient(resultSet.getString("nomIngredient"));
                    ingredient.setPrixIngredient(resultSet.getDouble("prixIngredient"));
                    ingredient.setIdUnite(resultSet.getInt("idUnite"));
                }
            }
        }

        return ingredient;
    }
    public static void update(Connection connection, Ingredient ingredient) throws SQLException {
        String sql = "UPDATE Ingredient SET nomIngredient = ?, prixIngredient = ?, idUnite = ? WHERE idIngredient = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ingredient.getNomIngredient());
            preparedStatement.setDouble(2, ingredient.getPrixIngredient());
            preparedStatement.setInt(3, ingredient.getIdUnite());
            preparedStatement.setInt(4, ingredient.getIdIngredient());

            preparedStatement.executeUpdate();
        }
    }


}
