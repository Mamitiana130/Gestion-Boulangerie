package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Vendeur {
    private int idVendeur;
    private String nomVendeur;

    private Genre genre;
    // Constructeurs
    public Vendeur() {}

    public Vendeur(String nomVendeur) {

        this.nomVendeur = nomVendeur;
    }

    // Getters et Setters

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(int idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getNomVendeur() {
        return nomVendeur;
    }

    public void setNomVendeur(String nomVendeur) {
        this.nomVendeur = nomVendeur;
    }


    // Méthodes pour interagir avec la base de données

    // Récupérer tous les clients
    public static List<Vendeur> getAll(Connection connection) throws SQLException {
        List<Vendeur> vendeurs = new ArrayList<>();
        String query = "SELECT idVendeur, nomVendeur,idGenre FROM Vendeur ORDER BY idVendeur ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Vendeur vendeur = new Vendeur();
                Genre genre=Genre.getByIdGenre(connection,resultSet.getInt("idGenre"));
                vendeur.setIdVendeur(resultSet.getInt("idVendeur"));
                vendeur.setNomVendeur(resultSet.getString("nomVendeur"));
                vendeur.setGenre(genre);
                vendeurs.add(vendeur);
            }
        }
        return vendeurs;
    }
    // Récupérer un client par son ID
    public static Vendeur getByIdVendeur(Connection connection, int idVendeur) throws SQLException {
        String query = "SELECT idVendeur, nomVendeur , idGenre FROM Vendeur WHERE idVendeur = ?";
        Vendeur  vendeur = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idVendeur);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    vendeur  = new Vendeur();
                    Genre genre=Genre.getByIdGenre(connection,resultSet.getInt("idGenre"));
                    vendeur.setIdVendeur(resultSet.getInt("idVendeur"));
                    vendeur.setNomVendeur(resultSet.getString("nomVendeur"));
                    vendeur.setGenre(genre);
                }
            }
        }

        return vendeur; // Retourne null si le client n'existe pas
    }

}