package com.example.projets3s5.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Unite {
    private int idUnite;
    private String nomUnite;

    // Getters et Setters
    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }

    // Fonction pour récupérer toutes les unités
    public static List<Unite> getAll(Connection connection) throws SQLException {
        List<Unite> unites = new ArrayList<>();
        String sql = "SELECT * FROM Unite";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Unite unite = new Unite();
                unite.setIdUnite(resultSet.getInt("idUnite"));
                unite.setNomUnite(resultSet.getString("nomUnite"));

                unites.add(unite);
            }
        }
        return unites;
    }
}
