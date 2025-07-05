
create table membre(
                       idMembre SERIAL PRIMARY KEY ,
                       nomMembre VARCHAR(50),
                       prenomMembre VARCHAR(100),
                       mail VARCHAR(150),
                       mdp VARCHAR(50),
                       role VARCHAR(30)
);

CREATE TABLE Unite(
                      idUnite SERIAL,
                      nomUnite VARCHAR(10) ,
                      PRIMARY KEY(idUnite)
);

CREATE TABLE Type(
                     idType SERIAL,
                     nomType VARCHAR(100) ,
                     PRIMARY KEY(idType)
);

CREATE TABLE Option(
                       idOption SERIAL,
                       nomOption VARCHAR(50) ,
                       PRIMARY KEY(idOption)
);

CREATE TABLE Statut(
                       idStatut SERIAL,
                       nomStatus VARCHAR(50) ,
                       PRIMARY KEY(idStatut)
);

CREATE TABLE Parfum(
                       idParfum SERIAL,
                       nomParfum VARCHAR(50) ,
                       PRIMARY KEY(idParfum)
);


CREATE TABLE Ingredient(
                           idIngredient SERIAL,
                           nomIngredient VARCHAR(100) ,
                           prixIngredient NUMERIC(15,2)  ,
                           idUnite INTEGER NOT NULL,
                           PRIMARY KEY(idIngredient),
                           FOREIGN KEY(idUnite) REFERENCES Unite(idUnite)
);

CREATE TABLE Produit(
                        idProduit SERIAL,
                        nomProduit VARCHAR(100) ,
                        prixProduit NUMERIC(15,2)  ,
                        description TEXT,
                        photoPath VARCHAR(250) ,
                        idParfum INTEGER NOT NULL,
                        idType INTEGER NOT NULL,
                        PRIMARY KEY(idProduit),
                        FOREIGN KEY(idParfum) REFERENCES Parfum(idParfum),
                        FOREIGN KEY(idType) REFERENCES Type(idType)
);


CREATE TABLE StockIngredient(
                                idStock SERIAL,
                                quantite INTEGER,
                                idIngredient INTEGER NOT NULL,
                                PRIMARY KEY(idStock),
                                FOREIGN KEY(idIngredient) REFERENCES Ingredient(idIngredient)
);

CREATE TABLE StockProduit(
                             idStock SERIAL,
                             quantite INTEGER,
                             idProduit INTEGER NOT NULL,
                             PRIMARY KEY(idStock),
                             FOREIGN KEY(idProduit) REFERENCES Produit(idProduit)
);

CREATE TABLE Commande(
                         idCommande SERIAL,
                         quantiteCommande INTEGER,
                         dateCommande TIMESTAMP,
                         idOption INTEGER NOT NULL,
                         idStatut INTEGER NOT NULL,
                         idProduit INTEGER NOT NULL,
                         PRIMARY KEY(idCommande),
                         FOREIGN KEY(idOption) REFERENCES Option(idOption),
                         FOREIGN KEY(idStatut) REFERENCES Statut(idStatut),
                         FOREIGN KEY(idProduit) REFERENCES Produit(idProduit)
);

CREATE TABLE HistoriqueCommande(
                                   idHistoriqueCommande SERIAL,
                                   quantiteCommande VARCHAR(50) ,
                                   dateHistorique VARCHAR(50) ,
                                   idStatut INTEGER NOT NULL,
                                   idCommande INTEGER NOT NULL,
                                   PRIMARY KEY(idHistoriqueCommande),
                                   FOREIGN KEY(idStatut) REFERENCES Statut(idStatut),
                                   FOREIGN KEY(idCommande) REFERENCES Commande(idCommande)
);

CREATE TABLE MouvementStockIngredient(
                                         idMouvementStockIngredient SERIAL,
                                         quantite INTEGER,
                                         situation VARCHAR(50) ,
                                         dateMouvement TIMESTAMP,
                                         idIngredient INTEGER NOT NULL,
                                         PRIMARY KEY(idMouvementStockIngredient),
                                         FOREIGN KEY(idIngredient) REFERENCES Ingredient(idIngredient)
);

CREATE TABLE MouvementStockProduit(
                                      idMouvementStockIngredient SERIAL,
                                      quantite INTEGER,
                                      situation VARCHAR(50) ,
                                      dateMouvement TIMESTAMP,
                                      idProduit INTEGER NOT NULL,
                                      PRIMARY KEY(idMouvementStockIngredient),
                                      FOREIGN KEY(idProduit) REFERENCES Produit(idProduit)
);

CREATE TABLE Recette(
                        idIngredient INTEGER,
                        idProduit INTEGER,
                        quantiteRecquise NUMERIC(15,2)  ,
                        PRIMARY KEY(idIngredient, idProduit),
                        FOREIGN KEY(idIngredient) REFERENCES Ingredient(idIngredient),
                        FOREIGN KEY(idProduit) REFERENCES Produit(idProduit)
);

CREATE TABLE Client(
                       idClient SERIAL PRIMARY KEY,
                       nomClient VARCHAR(50)
);

CREATE TABLE Genre(
                      idGenre SERIAL PRIMARY KEY,
                      nomGenre VARCHAR(50)
);

CREATE TABLE Vendeur(
                        idVendeur SERIAL PRIMARY KEY,
                        nomVendeur VARCHAR(50),
                        idGenre INTEGER,
                        FOREIGN KEY(idGenre) REFERENCES Genre(idGenre)
);

CREATE TABLE Vente(
                      idVente SERIAL,
                      idClient INTEGER,
                      idVendeur INTEGER,
                      quantite INTEGER,
                      dateVente TIMESTAMP,
                      idProduit INTEGER NOT NULL,
                      PRIMARY KEY(idVente),
                      FOREIGN KEY(idProduit) REFERENCES Produit(idProduit),
                      FOREIGN KEY(idClient) REFERENCES Client(idClient)
);

CREATE TABLE ProduitDuMois(
                              idProduitDuMois SERIAL PRIMARY KEY,
                              idProduit INTEGER NOT NULL,
                              mois INTEGER CHECK (mois BETWEEN 1 AND 12),
                              annee INTEGER NOT NULL,
                              FOREIGN KEY (idProduit) REFERENCES Produit(idProduit)
);

CREATE TABLE HistoriquePrixProduit(
                                    idHistorique SERIAL PRIMARY KEY,
                                    idProduit INTEGER NOT NULL,
                                    prix NUMERIC(15,2),
                                    dateChangement TIMESTAMP,
                                    FOREIGN KEY (idProduit) REFERENCES Produit(idProduit)
);
