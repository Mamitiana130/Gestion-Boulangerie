package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Parfum {
    private int idParfum;
    private String nomParfum;

    // Getters and Setters
    public int getIdParfum() {
        return idParfum;
    }

    public void setIdParfum(int idParfum) {
        this.idParfum = idParfum;
    }

    public String getNomParfum() {
        return nomParfum;
    }

    public void setNomParfum(String nomParfum) {
        this.nomParfum = nomParfum;
    }

    // Method to retrieve all Parfum records
    public static List<Parfum> getAll(Connection connection) throws SQLException {
        List<Parfum> parfums = new ArrayList<>();
        String sql = "SELECT idParfum, nomParfum FROM Parfum";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Parfum parfum = new Parfum();
                parfum.setIdParfum(resultSet.getInt("idParfum"));
                parfum.setNomParfum(resultSet.getString("nomParfum"));

                parfums.add(parfum);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
            throw e;
        }

        return parfums;
    }
}
