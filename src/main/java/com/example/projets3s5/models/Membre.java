package com.example.projets3s5.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Membre {
    private int idMembre;
    private String nomMembre;
    private String prenomMembre;
    private String mail;
    private String mdp;

    public int getIdMembre() {
        return idMembre;
    }

    public void setIdMembre(int idMembre) {
        this.idMembre = idMembre;
    }

    public String getNomMembre() {
        return nomMembre;
    }

    public void setNomMembre(String nomMembre) {
        this.nomMembre = nomMembre;
    }

    public String getPrenomMembre() {
        return prenomMembre;
    }

    public void setPrenomMembre(String prenomMembre) {
        this.prenomMembre = prenomMembre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public static List<Membre> getAll(Connection connection) throws SQLException {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT idMembre, nomMembre, prenomMembre, mail, mdp FROM membre";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Membre membre = new Membre();
                membre.setIdMembre(resultSet.getInt("idMembre"));
                membre.setNomMembre(resultSet.getString("nomMembre"));
                membre.setPrenomMembre(resultSet.getString("prenomMembre"));
                membre.setMail(resultSet.getString("mail"));
                membre.setMdp(resultSet.getString("mdp"));

                membres.add(membre);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des données : " + e.getMessage());
            throw e;
        }
        return membres;
    }

    public static Membre login(Connection connection, String prenomMembre, String mdp) throws SQLException {
        String sql = "SELECT idMembre, nomMembre, prenomMembre, mail, mdp FROM membre WHERE prenomMembre = ? AND mdp = ?";
        Membre membre = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, prenomMembre);
            preparedStatement.setString(2, mdp);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    membre = new Membre();
                    membre.setIdMembre(resultSet.getInt("idMembre"));
                    membre.setNomMembre(resultSet.getString("nomMembre"));
                    membre.setPrenomMembre(resultSet.getString("prenomMembre"));
                    membre.setMail(resultSet.getString("mail"));
                    membre.setMdp(resultSet.getString("mdp")); // Si tu ne veux pas renvoyer le mot de passe, supprime cette ligne.
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la tentative de connexion : " + e.getMessage());
            throw e;
        }
        return membre;
    }

}

