package com.example.projets3s5.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientStockView {
    private String nomIngredient;
    private int quantite;
    private String nomUnite;

    public String getNomIngredient() {
        return nomIngredient;
    }

    public void setNomIngredient(String nomIngredient) {
        this.nomIngredient = nomIngredient;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    public static List<IngredientStockView> getAll(Connection connection) throws SQLException {
        List<IngredientStockView> stocks = new ArrayList<>();
        String sql = "SELECT nomIngredient, quantite, nomUnite FROM v_IngredientStock";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                IngredientStockView stock = new IngredientStockView();
                stock.setNomIngredient(resultSet.getString("nomIngredient"));
                stock.setQuantite(resultSet.getInt("quantite"));
                stock.setNomUnite(resultSet.getString("nomUnite"));

                stocks.add(stock);
            }
        }
        return stocks;
    }

    public static List<String> checkStatut(Connection connection) throws SQLException {
        List<String> statuts = new ArrayList<>();
        String sql = "SELECT nomIngredient, quantite, nomUnite FROM v_IngredientStock";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int quantite = resultSet.getInt("quantite");
                String nomUnite = resultSet.getString("nomUnite");
                String statut;

                switch (nomUnite.toLowerCase()) {
                    case "ml":
                        statut = quantite < 10 ? "Insuffisant" : "Suffisant";
                        break;
                    case "kilogrammes":
                    case "g":
                        statut = quantite < 20 ? "Insuffisant" : "Suffisant";
                        break;
                    case "piece":
                        statut = quantite < 5 ? "Insuffisant" : "Suffisant";
                        break;
                    default:
                        statut = "Unité inconnue";
                        break;
                }

                statuts.add(statut);
            }
        }
        return statuts;
    }

}
