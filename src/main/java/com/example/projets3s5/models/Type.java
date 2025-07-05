package com.example.projets3s5.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Type {
    private int idType;
    private String nomType;

    // Getters et Setters
    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getNomType() {
        return nomType;
    }

    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    // Fonction pour récupérer tous les types
    public static List<Type> getAll(Connection connection) throws SQLException {
        List<Type> types = new ArrayList<>();
        String sql = "SELECT * FROM Type";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Type type = new Type();
                type.setIdType(resultSet.getInt("idType"));
                type.setNomType(resultSet.getString("nomType"));

                types.add(type);
            }
        }
        return types;
    }
}
