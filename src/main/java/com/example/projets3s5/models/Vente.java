package com.example.projets3s5.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vente
{
    private int idVente;
    private int idProduit;
    private int quantite;
    private Timestamp dateVente;

    private String nomProduit;

    private Client client;
/////////////////////////////////////////////
    private Produit produit;

    private double prixTotal;

    private Vendeur vendeur;

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getIdVente()
    {
        return idVente;
    }
    public void setIdVente(int idVente)
    {
        this.idVente = idVente;
    }
    public int getIdProduit()
    {
        return idProduit;
    }
    public void setIdProduit(int idProduit)
    {
        this.idProduit = idProduit;
    }

    public int getQuantite()
    {
        return quantite;
    }

    public void setQuantite(int quantite)
    {
        this.quantite = quantite;
    }
    public Timestamp getDateVente()
    {
        return dateVente;
    }
    public void setDateVente(Timestamp dateVente)
    {
        this.dateVente = dateVente;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public static void insert(Connection connection, Vente vente) throws SQLException {
        String sql = "INSERT INTO Vente (idProduit, quantite, dateVente, idClient,idVendeur) VALUES (?, ?, ?, ? ,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, vente.getIdProduit());
            preparedStatement.setInt(2, vente.getQuantite());
            preparedStatement.setTimestamp(3, vente.getDateVente());
            preparedStatement.setInt(4, vente.getClient().getIdClient());
            preparedStatement.setInt(5, vente.getVendeur().getIdVendeur());// Ajout de l'ID du client

            preparedStatement.executeUpdate();
        }
    }


    public static List<Vente> getVenteSelect(Connection connection, List<Integer> produits) throws SQLException {
        List<Vente> ventes = new ArrayList<>();

        if (produits == null || produits.isEmpty()) {
            return ventes;
        }

        StringBuilder query = new StringBuilder(
                "SELECT Vente.idVente, Vente.quantite, Vente.dateVente, Produit.nomProduit " +
                        "FROM Vente " +
                        "JOIN Produit ON Produit.idProduit = Vente.idProduit " +
                        "WHERE Vente.idProduit IN ("
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
                preparedStatement.setInt(i + 1, produits.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vente venteProduit = new Vente();
                    venteProduit.setIdVente(resultSet.getInt("idVente"));
                    venteProduit.setQuantite(resultSet.getInt("quantite"));
                    venteProduit.setDateVente(resultSet.getTimestamp("dateVente"));
                    venteProduit.setNomProduit(resultSet.getString("nomProduit"));
                    ventes.add(venteProduit);
                }
            }
        }

        return ventes;
    }
    public static List<Vente> getByDate(Connection connection, Date date) throws SQLException {
        List<Vente> ventes = new ArrayList<>();
        String sql = "SELECT v.idVente, v.idProduit, v.quantite, v.dateVente, c.idClient, c.nomClient, p.nomProduit " +
                "FROM Vente v " +
                "JOIN Client c ON v.idClient = c.idClient " +
                "JOIN Produit p ON v.idProduit = p.idProduit " +
                "WHERE DATE(v.dateVente) = ? " +
                "ORDER BY v.dateVente ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new java.sql.Date(date.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vente vente = new Vente();
                    vente.setIdVente(resultSet.getInt("idVente"));
                    vente.setIdProduit(resultSet.getInt("idProduit"));
                    vente.setQuantite(resultSet.getInt("quantite"));
                    vente.setDateVente(resultSet.getTimestamp("dateVente"));
                    vente.setNomProduit(resultSet.getString("nomProduit"));

                    // Récupérer les informations du client
                    Client client = new Client();
                    client.setIdClient(resultSet.getInt("idClient"));
                    client.setNomClient(resultSet.getString("nomClient"));

                    // Associer le client à la vente
                    vente.setClient(client);

                    ventes.add(vente);
                }
            }
        }

        return ventes;
    }
    public static List<Vente> getByDateDebutDateFin(Connection connection, Date dateDebut, Date dateFin) throws SQLException {
        List<Vente> ventes = new ArrayList<>();
        String sql = "SELECT g.idGenre,g.nomGenre,v.idVente, v.idProduit, v.quantite, v.dateVente, c.idClient, c.nomClient, p.nomProduit, p.prixProduit,ven.nomVendeur,ven.idVendeur " +
                "FROM Vente v " +
                "JOIN Vendeur ven ON ven.idVendeur = v.idVendeur " +
                "JOIN Client c ON v.idClient = c.idClient " +
                "JOIN Produit p ON v.idProduit = p.idProduit " +
                "JOIN Genre g ON g.idGenre = ven.idGenre " +
                "WHERE v.dateVente BETWEEN ? AND ? " +
                "ORDER BY v.dateVente ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setTimestamp(1, new java.sql.Timestamp(dateDebut.getTime()));
            preparedStatement.setTimestamp(2, new java.sql.Timestamp(dateFin.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Vente vente = new Vente();
                    vente.setIdVente(resultSet.getInt("idVente"));
                    vente.setIdProduit(resultSet.getInt("idProduit"));
                    vente.setQuantite(resultSet.getInt("quantite"));
                    vente.setDateVente(resultSet.getTimestamp("dateVente"));
                    vente.setNomProduit(resultSet.getString("nomProduit"));


                    // Calculer le prix total
                    double prixProduit = resultSet.getDouble("prixProduit");
                    double prixTotal = vente.getQuantite() * prixProduit;
                    vente.setPrixTotal(prixTotal);

                    // Récupérer les informations du client
                    Client client = new Client();
                    client.setIdClient(resultSet.getInt("idClient"));
                    client.setNomClient(resultSet.getString("nomClient"));

                    Genre genre = new Genre();
                    genre.setIdGenre(resultSet.getInt("idGenre"));
                    genre.setNomGenre(resultSet.getString("nomGenre"));


                    Vendeur vendeur = new Vendeur();
                    vendeur.setIdVendeur(resultSet.getInt("idVendeur"));
                    vendeur.setNomVendeur(resultSet.getString("nomVendeur"));
                    vendeur.setGenre(genre);

                    // Associer le client à la vente
                    vente.setVendeur(vendeur);
                    vente.setClient(client);

                    ventes.add(vente);
                }
            }
        }
        return ventes;
    }
    public static Map<String, Double> getCommission(Connection connection, List<Vente> ventes) throws SQLException {
        Map<String, Double> commissionMap = new HashMap<>();

        for (Vente vente : ventes) {
            String vendeurNom = null;
            double totalVente = 0.0;

            // Récupérer le nom du vendeur à partir de l'ID de la vente
            String sql = "SELECT v.idVendeur, ve.nomVendeur, SUM(v.quantite * p.prixProduit) AS totalVente " +
                    "FROM Vente v " +
                    "JOIN Vendeur ve ON v.idVendeur = ve.idVendeur " +
                    "JOIN Produit p ON v.idProduit = p.idProduit " +
                    "WHERE v.idVente = ? " +
                    "GROUP BY v.idVendeur, ve.nomVendeur";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, vente.getIdVente());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        vendeurNom = resultSet.getString("nomVendeur");
                        totalVente = resultSet.getDouble("totalVente");
                    }
                }
            }

            // Ajouter ou mettre à jour la commission pour le vendeur
            if (vendeurNom != null) {
                double commission = totalVente * 0.05; // 5% de commission
                commissionMap.put(vendeurNom, commissionMap.getOrDefault(vendeurNom, 0.0) + commission);
            }
        }

        return commissionMap;
    }

    public static Map<String, Double> getCommissionParGenre(Connection connection, List<Vente> ventes) throws SQLException {
        Map<String, Double> commissionMap = new HashMap<>();

        for (Vente vente : ventes) {
            String genreNom = null;
            double totalVente = 0.0;

            // Récupérer le nom du vendeur à partir de l'ID de la vente
            String sql = "SELECT v.idVendeur, ve.nomVendeur, g.nomGenre, SUM(v.quantite * p.prixProduit) AS totalVente " +
                    "FROM Vente v " +
                    "JOIN Vendeur ve ON v.idVendeur = ve.idVendeur " +
                    "JOIN Produit p ON v.idProduit = p.idProduit " +
                    "JOIN Genre g ON g.idGenre = ve.idGenre " +
                    "WHERE v.idVente = ? " +
                    "GROUP BY v.idVendeur, ve.nomVendeur, g.nomGenre";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, vente.getIdVente());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        genreNom = resultSet.getString("nomGenre");
                        totalVente = resultSet.getDouble("totalVente");
                    }
                }
            }

            // Ajouter ou mettre à jour la commission pour le vendeur
            if (genreNom != null) {
                double commission=0;
                if(totalVente>=200000)
                {
                    commission = totalVente * 0.05;
                }
                commissionMap.put(genreNom, commissionMap.getOrDefault(genreNom, 0.0) + commission);
            }
        }

        return commissionMap;
    }


}