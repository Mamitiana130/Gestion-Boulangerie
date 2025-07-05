

package com.example.projets3s5.servlets;

import com.example.projets3s5.connexion.ConnexionPost;
import com.example.projets3s5.models.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

@WebServlet("/RechercheProduitMoisServlet")
public class RechercheProduitMoisServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mode = request.getParameter("mode");

            if ("s".equals(mode)) {

                String[] mois=new String[12];
                mois[0]="Janvier";
                mois[1]="Fevrier";
                mois[2]="Mars";
                mois[3]="Avril";
                mois[4]="Mai";
                mois[5]="Juin";
                mois[6]="Juillet";
                mois[7]="Aout";
                mois[8]="Septembre";
                mois[9]="Octobre";
                mois[10]="Novembre";
                mois[11]="Decembre";

                int[] annee=new int[3];
                annee[0]=2023;
                annee[1]=2024;
                annee[2]=2025;

                request.setAttribute("mois", mois);
                request.setAttribute("annee", annee);
                request.getRequestDispatcher("RechercheProduitMois.jsp").forward(request, response);
            }

    }




}

