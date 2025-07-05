package com.example.projets3s5.models;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Genre {

    private int idGenre;
    private String nomGenre;

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getNomGenre() {
        return nomGenre;
    }

    public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    public static List<Genre> getAll(Connection connection) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String sql = "SELECT idGenre, nomGenre FROM Genre";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setIdGenre(resultSet.getInt("idGenre"));
                genre.setNomGenre(resultSet.getString("nomGenre"));

                genres.add(genre);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
            throw e;
        }

        return genres;
    }

    public static Genre getByIdGenre(Connection connection, int idGenre) throws SQLException {
        String sql = "SELECT * FROM Genre WHERE idGenre = ?";
        Genre genre = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, idGenre);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    genre = new Genre();
                    genre.setIdGenre(resultSet.getInt("idGenre"));
                    genre.setNomGenre(resultSet.getString("nomGenre"));
                }
            }
        }
        return genre;
    }

}
