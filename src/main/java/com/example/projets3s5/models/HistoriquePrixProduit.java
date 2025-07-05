package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HistoriquePrixProduit {
    private int idHistorique;
    private int idProduit;
    private double prix;
    private Timestamp dateChangement;

    // Constructeurs
    public HistoriquePrixProduit() {}

    public HistoriquePrixProduit(int idHistorique, int idProduit, double prix, Timestamp dateChangement) {
        this.idHistorique = idHistorique;
        this.idProduit = idProduit;
        this.prix = prix;
        this.dateChangement = dateChangement;
    }

    // Getters et Setters
    public int getIdHistorique() {
        return idHistorique;
    }

    public void setIdHistorique(int idHistorique) {
        this.idHistorique = idHistorique;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Timestamp getDateChangement() {
        return dateChangement;
    }

    public void setDateChangement(Timestamp dateChangement) {
        this.dateChangement = dateChangement;
    }

    // Récupération de tous les historiques
    public static List<HistoriquePrixProduit> getAll(Connection connection) throws SQLException {
        List<HistoriquePrixProduit> historiqueList = new ArrayList<>();
        String query = "SELECT * FROM HistoriquePrixProduit ORDER BY dateChangement DESC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                HistoriquePrixProduit historique = new HistoriquePrixProduit();
                historique.setIdHistorique(resultSet.getInt("idHistorique"));
                historique.setIdProduit(resultSet.getInt("idProduit"));
                historique.setPrix(resultSet.getDouble("prix"));
                historique.setDateChangement(resultSet.getTimestamp("dateChangement"));

                historiqueList.add(historique);
            }
        }
        return historiqueList;
    }

    // Insertion d'un nouvel historique
    public static void insert(Connection connection, HistoriquePrixProduit historique) throws SQLException {
        String sql = "INSERT INTO HistoriquePrixProduit (idProduit, prix, dateChangement) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, historique.getIdProduit());
            preparedStatement.setDouble(2, historique.getPrix());
            preparedStatement.setTimestamp(3, historique.getDateChangement());

            preparedStatement.executeUpdate();
        }
    }
}