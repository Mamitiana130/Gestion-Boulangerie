package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockProduit {
    private int idStock;
    private int quantite;
    private int idProduit;

    // Constructeurs
    public StockProduit() {}

    public StockProduit(int idStock, int quantite, int idProduit) {
        this.idStock = idStock;
        this.quantite = quantite;
        this.idProduit = idProduit;
    }

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

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    /**
     * Fonction pour récupérer les stocks des produits donnés
     *
     * @param connection Connexion à la base de données
     * @param produits Liste des produits
     * @return Liste des stocks des produits
     * @throws SQLException Exception en cas de problème SQL
     */
    public static List<StockProduit> getStockProduit(Connection connection, List<Produit> produits) throws SQLException {
        List<StockProduit> stockProduits = new ArrayList<>();

        if (produits == null || produits.isEmpty()) {
            return stockProduits;
        }

        StringBuilder query = new StringBuilder(
                "SELECT idStock, quantite, idProduit FROM StockProduit WHERE quantite > 0 AND idProduit IN ("
        );

        for (int i = 0; i < produits.size(); i++) {
            query.append("?");
            if (i < produits.size() - 1) {
                query.append(", ");
            }
        }
        query.append(")");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query.toString())) {
            for (int i = 0; i < produits.size(); i++) {
                preparedStatement.setInt(i + 1, produits.get(i).getIdProduit());
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    StockProduit stockProduit = new StockProduit();
                    stockProduit.setIdStock(resultSet.getInt("idStock"));
                    stockProduit.setQuantite(resultSet.getInt("quantite"));
                    stockProduit.setIdProduit(resultSet.getInt("idProduit"));
                    stockProduits.add(stockProduit);
                }
            }
        }

        return stockProduits;
    }

}
