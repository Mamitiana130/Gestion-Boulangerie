/*========================================================================================================================================================================================================*/
INSERT INTO membre(nomMembre,prenomMembre,mail,mdp) VALUES
('RATSIMBA','Mamitiana','mamt@gmail.com','111');

INSERT INTO Unite (nomUnite) VALUES
                                 ('g'), -- Solide
                                 ('ml'), -- Liquide
                                 ('piece'); -- Comptes

INSERT INTO Type (nomType) VALUES
                               ('Viennoiseries'),
                               ('Sandwicherie'),
                               ('Patisserie'),
                               ('Pains classique');

INSERT INTO Parfum (nomParfum) VALUES
                                   ('Nature'),
                                   ('Chocolat'),
                                   ('Vanille'),
                                   ('Fraise'),
                                   ('Citron'),
                                   ('Cafe');

INSERT INTO Ingredient (nomIngredient, prixIngredient, idUnite) VALUES
                                                                    ('Farine', 30, 1),
                                                                    ('Sucre', 40, 1),
                                                                    ('Sel', 50, 1),
                                                                    ('Beurre', 10, 1),
                                                                    ('Lait', 10, 2),
                                                                    ('Œufs', 20, 3),
                                                                    ('Levure', 150, 1),
                                                                    ('Chocolat', 30, 1),
                                                                    ('Creme patissiere', 10, 2),
                                                                    ('Jambon', 20, 1),
                                                                    ('Myrtilles', 2000.00, 1),
                                                                    ('Cafe', 1500.00, 2),
                                                                    ('Jus de citron', 1200.00, 2),
                                                                    ('Meringue', 1800.00, 1),
                                                                    ('Saumon fume', 8000.00, 3),
                                                                    ('Fromage frais', 6000.00, 3);

/*========================================================================================================================================================================================================*/
INSERT INTO Produit (nomProduit, prixProduit, description, photoPath, idParfum, idType) VALUES
                                                                                            ('Croissant', 2000.00, 'Croissant classique au beurre', 'images/croissant.jpg', 1, 1), --1, Parfum Nature, Viennoiseries
                                                                                            ('Pain au chocolat', 2000.00, 'Viennoiserie au chocolat', 'images/pain_chocolat.jpg', 2, 1), --2, Parfum Chocolat, Viennoiseries
                                                                                            ('Sandwich Jambon Beurre', 3000.00, 'Sandwich classique au jambon', 'images/sandwich_jambon.jpg', 1, 2), --3, Parfum Nature, Sandwicherie
                                                                                            ('Tarte aux fraises', 4000.00, 'Tarte sucree avec fraises fraîches', 'images/tarte_fraise.jpg', 4, 3), --4, Parfum Fraise, Patisserie
                                                                                            ('Baguette classique', 1000.00, 'Baguette de pain française classique', 'images/baguette.jpg', 1, 4), --5, Parfum Nature, Pains classiques
                                                                                            ('Muffin a la fraise', 2500.00, 'Muffin moelleux avec des fraises fraîches', 'images/muffin_myrtilles.jpg', 4, 3),--6,Parfum fraise, Muffin a la fraise
                                                                                            ('Brownie au chocolat', 3000.00, 'Delicieux brownie fondant au chocolat', 'images/brownie.jpg', 2, 3),--7,Parfum chocolat, Brownie au chocolat
                                                                                            ('Eclair au cafe', 3500.00, 'Pâtisserie remplie de crème au cafe et glaçage cafe', 'images/eclair_cafe.jpg', 6, 3);--8.Parfum cafe, Eclair au cafe

/*========================================================================================================================================================================================================*/

INSERT INTO StockIngredient (quantite, idIngredient) VALUES
                                                         (10000, 1), -- Farine : 1000000 g
                                                         (50000, 2),  -- Sucre : 50000 g
                                                         (10000, 3),  -- Sel : 10000 g
                                                         (20000, 4),  -- Beurre : 20000 g
                                                         (200000, 5), -- Lait : 200000 ml
                                                         (500000, 6), -- Œufs : 500000 pièces
                                                         (5000, 7),   -- Levure : 5000 g
                                                         (10000, 8),  -- Chocolat : 10000 g
                                                         (30000, 9),  -- Crème pâtissière : 30000 ml
                                                         (15000, 10); -- Jambon : 15000 g

INSERT INTO MouvementStockIngredient (quantite, situation, dateMouvement, idIngredient) VALUES
                                                                                            (50, 'entree', '2024-12-01 08:00:00', 1),  -- Reapprovisionnement de 50 g de Farine
                                                                                            (20, 'sortie', '2024-12-02 10:00:00', 1), -- Utilisation de 20 g de Farine
                                                                                            (30, 'entree', '2024-12-01 08:00:00', 2), -- Reapprovisionnement de 30 g de Sucre
                                                                                            (10, 'sortie', '2024-12-02 10:15:00', 2), -- Utilisation de 10 g de Sucre
                                                                                            (10, 'entree', '2024-12-01 08:00:00', 5), -- Reapprovisionnement de 10 litres de Lait
                                                                                            (5, 'sortie', '2024-12-02 11:00:00', 5),  -- Utilisation de 5 litres de Lait
                                                                                            (500, 'entree', '2024-12-01 08:00:00', 6), -- Reapprovisionnement de 500 Œufs
                                                                                            (200, 'sortie', '2024-12-02 12:00:00', 6), -- Utilisation de 200 Œufs
                                                                                            (10, 'entree', '2024-12-01 08:00:00', 8), -- Reapprovisionnement de 10 g de Chocolat
                                                                                            (3, 'sortie', '2024-12-02 13:00:00', 8);  -- Utilisation de 3 g de Chocolat

/*========================================================================================================================================================================================================*/
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 1, 0.500), (2, 1, 0.020), (3, 1, 0.005), (4, 1, 0.150), (7, 1, 0.010); -- croissant
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 2, 0.500), (2, 2, 0.020), (4, 2, 0.100), (8, 2, 0.050), (7, 2, 0.010); -- pain au chocolat
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (5, 3, 1.000), (4, 3, 0.050), (10, 3, 0.100); -- sandwitch jomban beurre
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 4, 0.300), (2, 4, 0.100), (4, 4, 0.200), (6, 4, 3), (9, 4, 0.200); -- tarte au fraise
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 5, 0.400), (2, 5, 0.010), (3, 5, 0.005), (7, 5, 0.015); -- pain classique
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 6, 0.015), (2, 6, 0.010), (3, 6, 0.005);--muffin a la fraise
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 7, 0.400),(2, 7, 0.150), (3, 7, 0.005), (4, 7, 0.200),(6, 7, 2),(8, 7, 0.300);--brownie au choco
INSERT INTO Recette (idIngredient, idProduit, quantiteRecquise) VALUES (1, 8, 0.250), (2, 8, 0.050), (3, 8, 0.002), (4, 8, 0.100),(6, 8, 3),(5, 8, 0.100),(12, 8, 0.100); --Eclair au cafe

/*========================================================================================================================================================================================================*/
INSERT INTO StockProduit (quantite, idProduit) VALUES
                                                   (120, 1), -- 120 croissants en stock
                                                   (80, 2), -- 80 pains au chocolat en stock
                                                   (50, 3), -- 50 sandwichs jambon beurre en stock
                                                   (30, 4), -- 30 tartes aux fraises en stock
                                                   (200, 5); -- 200 baguettes classiques en stock

INSERT INTO MouvementStockProduit (quantite, situation, dateMouvement, idProduit) VALUES
                                                                                      (50, 'entree', '2025-01-01 08:00:00', 1), -- Reapprovisionnement de croissants
                                                                                      (30, 'sortie', '2025-01-01 10:00:00', 1), -- Vente de croissants
                                                                                      (40, 'entree', '2025-01-01 08:30:00', 2), -- Reapprovisionnement de pains au chocolat
                                                                                      (20, 'sortie', '2025-01-01 11:00:00', 2), -- Vente de pains au chocolat
                                                                                      (15, 'entree', '2025-01-01 09:00:00', 3), -- Reapprovisionnement de sandwichs jambon beurre
                                                                                      (10, 'sortie', '2025-01-01 12:00:00', 3), -- Vente de sandwichs jambon beurre
                                                                                      (25, 'entree', '2025-01-01 09:30:00', 4), -- Reapprovisionnement de tartes aux fraises
                                                                                      (18, 'sortie', '2025-01-01 14:00:00', 4), -- Vente de tartes aux fraises
                                                                                      (100, 'entree', '2025-01-01 07:00:00', 5), -- Reapprovisionnement de baguettes classiques
                                                                                      (90, 'sortie', '2025-01-01 15:00:00', 5), -- Vente de baguettes classiques
                                                                                      (20, 'entree', '2025-01-04 08:00:00', 1), -- Reapprovisionnement supplementaire de croissants
                                                                                      (10, 'sortie', '2025-01-04 10:00:00', 1), -- Nouvelle vente de croissants
                                                                                      (25, 'entree', '2025-01-04 08:30:00', 2), -- Reapprovisionnement de pains au chocolat
                                                                                      (15, 'sortie', '2025-01-04 11:00:00', 2), -- Nouvelle vente de pains au chocolat
                                                                                      (30, 'entree', '2025-01-04 09:00:00', 3), -- Reapprovisionnement supplementaire de sandwichs jambon beurre
                                                                                      (20, 'sortie', '2025-01-04 12:00:00', 3), -- Nouvelle vente de sandwichs jambon beurre
                                                                                      (10, 'entree', '2025-01-04 09:30:00', 4), -- Reapprovisionnement de tartes aux fraises
                                                                                      (8, 'sortie', '2025-01-04 14:00:00', 4), -- Nouvelle vente de tartes aux fraises
                                                                                      (50, 'entree', '2025-01-04 07:00:00', 5), -- Reapprovisionnement supplementaire de baguettes classiques
                                                                                      (45, 'sortie', '2025-01-04 15:00:00', 5); -- Nouvelle vente de baguettes classiques

/*========================================================================================================================================================================================================*/
INSERT INTO Client (nomClient) VALUES ('Jean Dupont');
INSERT INTO Client (nomClient) VALUES ('Marie Martin');
INSERT INTO Client (nomClient) VALUES ('Paul Durand');


INSERT INTO Genre (nomGenre) VALUES ('homme');
INSERT INTO Genre (nomGenre) VALUES ('femme');


INSERT INTO Vendeur (nomVendeur,idGenre) VALUES
                                             ('Vendeur 1',1),
                                             ('Vendeur 2',2),
                                             ('Vendeur 3',2);

/*========================================================================================================================================================================================================*/
INSERT INTO Vente (idClient,idVendeur,quantite, dateVente, idProduit) VALUES
                                                       (1,1,3, '2025-01-01 10:30:00', 1), -- 3 Croissants vendus
                                                       (2,1,5, '2025-01-01 11:00:00', 2), -- 5 Pains au chocolat vendus
                                                       (3,2,2, '2025-01-02 08:45:00', 3), -- 2 Sandwichs Jambon Beurre vendus
                                                       (1,2,1, '2025-01-02 14:15:00', 4), -- 1 Tarte aux fraises vendue
                                                       (1,3,6, '2025-01-03 09:00:00', 5); -- 6 Baguettes classiques vendues
/*========================================================================================================================================================================================================*/
INSERT INTO ProduitDuMois (idProduit, mois, annee) VALUES
                                                       (1, 1, 2023), (2, 2, 2023), (3, 3, 2023),
                                                       (4, 4, 2023), (5, 5, 2023), (6, 6, 2023),
                                                       (7, 7, 2023), (8, 8, 2023), (1, 9, 2023),
                                                       (2, 10, 2023);
INSERT INTO ProduitDuMois (idProduit, mois, annee) VALUES
                                                       (3, 1, 2024), (4, 2, 2024), (5, 3, 2024),
                                                       (6, 4, 2024), (7, 5, 2024), (8, 6, 2024),
                                                       (1, 7, 2024), (2, 8, 2024), (3, 9, 2024),
                                                       (4, 10, 2024);
INSERT INTO ProduitDuMois (idProduit, mois, annee) VALUES
                                                       (5, 1, 2025), (6, 1, 2025), (7, 1, 2025);
/*========================================================================================================================================================================================================*/

INSERT INTO HistoriquePrixProduit (idProduit, prix, dateChangement)
VALUES (1, 150.00, '2023-01-01 10:00:00');

-- Insertion d'un historique de prix plus récent (aujourd'hui)
INSERT INTO HistoriquePrixProduit (idProduit, prix, dateChangement)
VALUES (1, 165.00, '2025-01-27 10:00:00');