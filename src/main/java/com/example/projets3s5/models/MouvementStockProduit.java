package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MouvementStockProduit {
    private int idMouvementStockIngredient;
    private int quantite;
    private String situation;
    private Timestamp dateMouvement;
    private int idProduit;

    // Constructeurs
    public MouvementStockProduit() {}

    public MouvementStockProduit(int idMouvementStockIngredient, int quantite, String situation, Timestamp dateMouvement, int idProduit) {
        this.idMouvementStockIngredient = idMouvementStockIngredient;
        this.quantite = quantite;
        this.situation = situation;
        this.dateMouvement = dateMouvement;
        this.idProduit = idProduit;
    }

    // Getters et Setters
    public int getIdMouvementStockIngredient() {
        return idMouvementStockIngredient;
    }

    public void setIdMouvementStockIngredient(int idMouvementStockIngredient) {
        this.idMouvementStockIngredient = idMouvementStockIngredient;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Timestamp getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(Timestamp dateMouvement) {
        this.dateMouvement = dateMouvement;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }



    public static List<MouvementStockProduit> getAll(Connection connection) throws SQLException {
        List<MouvementStockProduit> mouvements = new ArrayList<>();
        String query = "SELECT * FROM MouvementStockProduit ORDER BY dateMouvement DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                MouvementStockProduit mouvement = new MouvementStockProduit();
                mouvement.setIdMouvementStockIngredient(resultSet.getInt("idMouvementStockIngredient"));
                mouvement.setQuantite(resultSet.getInt("quantite"));
                mouvement.setSituation(resultSet.getString("situation"));
                mouvement.setDateMouvement(resultSet.getTimestamp("dateMouvement"));
                mouvement.setIdProduit(resultSet.getInt("idProduit"));
                mouvements.add(mouvement);
            }
        }
        return mouvements;
    }

    public static List<MouvementStockProduit> getWithCriterias(Connection connection, Timestamp dateDebut, Timestamp dateFin, int idType, String trie) throws SQLException {

        List<MouvementStockProduit> mouvements = new ArrayList<>();
        String query = "SELECT m.* FROM MouvementStockProduit m " +
                "INNER JOIN Produit p ON m.idProduit = p.idProduit " +
                "WHERE m.dateMouvement BETWEEN ? AND ? " +
                "AND m.situation like 'sortie' "+
                "AND p.idType = ? " +
                "ORDER BY m.quantite " + (trie.equalsIgnoreCase("asc") ? "ASC" : "DESC");

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setTimestamp(1, dateDebut);
            preparedStatement.setTimestamp(2, dateFin);
            preparedStatement.setInt(3, idType);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    MouvementStockProduit mouvement = new MouvementStockProduit();
                    mouvement.setIdMouvementStockIngredient(resultSet.getInt("idMouvementStockIngredient"));
                    mouvement.setQuantite(resultSet.getInt("quantite"));
                    mouvement.setSituation(resultSet.getString("situation"));
                    mouvement.setDateMouvement(resultSet.getTimestamp("dateMouvement"));
                    mouvement.setIdProduit(resultSet.getInt("idProduit"));
                    mouvements.add(mouvement);
                }
            }
        }
        return mouvements;
    }

}
