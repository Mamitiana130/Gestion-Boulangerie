-- liens entre table Ingredient, Unite et StockIngredient
create or replace view v_IngredientStock as
       (select i.nomIngredient,si.quantite,u.nomUnite
       from Ingredient i
       join unite u on u.idUnite=i.idUnite
       join StockIngredient si on si.idIngredient=i.idIngredient);

-- prix total ingredients pour un produit
create or replace view v_totalPrixFabrication as
        (select r.idIngredient,r.idProduit as idProduit,i.prixIngredient*r.quantiteRecquise as prixFabrication
        from recette r
        join ingredient i on i.idIngredient=r.idIngredient);

