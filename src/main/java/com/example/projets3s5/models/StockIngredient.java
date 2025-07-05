package com.example.projets3s5.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockIngredient {
    private int idStock;
    private int quantite;
    private int idIngredient;

    // Getters et Setters
    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdIngredient() {
        return idIngredient;
    }

    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }

    // Fonction pour récupérer toutes les entrées de stock d'ingrédients
    public static List<StockIngredient> getAll(Connection connection) throws SQLException {
        List<StockIngredient> stocks = new ArrayList<>();
        String sql = "SELECT * FROM StockIngredient";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StockIngredient stock = new StockIngredient();
                stock.setIdStock(resultSet.getInt("idStock"));
                stock.setQuantite(resultSet.getInt("quantite"));
                stock.setIdIngredient(resultSet.getInt("idIngredient"));

                stocks.add(stock);
            }
        }
        return stocks;
    }
}
