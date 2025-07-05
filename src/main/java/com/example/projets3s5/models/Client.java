package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private int idClient;
    private String nomClient;

    // Constructeurs
    public Client() {}

    public Client(int idClient, String nomClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
    }

    // Getters et Setters
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    // Méthodes pour interagir avec la base de données

    // Récupérer tous les clients
    public static List<Client> getAll(Connection connection) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT idClient, nomClient FROM Client ORDER BY idClient ASC";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setIdClient(resultSet.getInt("idClient"));
                client.setNomClient(resultSet.getString("nomClient"));
                clients.add(client);
            }
        }
        return clients;
    }
    // Récupérer un client par son ID
    public static Client getByIdClient(Connection connection, int idClient) throws SQLException {
        String query = "SELECT idClient, nomClient FROM Client WHERE idClient = ?";
        Client client = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idClient);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    client = new Client();
                    client.setIdClient(resultSet.getInt("idClient"));
                    client.setNomClient(resultSet.getString("nomClient"));
                }
            }
        }

        return client; // Retourne null si le client n'existe pas
    }

}