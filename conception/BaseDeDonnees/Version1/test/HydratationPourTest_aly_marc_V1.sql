use agence_aly_marc;

insert into client (nom_client, prenom_client, email_client, password_client, permis_client)
values ("SEGARD", "Marc", "segardmarc@gmail.com", password("123"), "6561565267525");
SELECT distinct LAST_INSERT_ID() into @id_client FROM client;

insert into agence (nom_agence, adresse_agence, ca_agence, SIRET_agence)
values ("ABIS", "2 rue des Fleus Bleues 75004 Paris", 0 , "123 568 941 00056");
SELECT distinct LAST_INSERT_ID() into @id_agence FROM agence;


select id_marque into @id_marque from marque where nom_marque="renault";
select id_carburant into @id_carburant from carburant where nom_carburant="diesel";
select id_categorie into @id_categorie from categorie where nom_categorie="berline";

insert into vehicule (couleur_vehicule, prix_vehicule, immatriculation_vehicule, date_arrivee_vehicule, 
description_vehicule,kilometrage_vehicule,options_vehicule, id_agence, id_categorie, id_carburant,
id_marque) 
values
("rouge",45.3,"EW-900-JI",now(),"Superbe voiture rouge",34000,"climatisation et GPS", @id_agence, @id_categorie, @id_carburant, @id_marque);
SELECT distinct LAST_INSERT_ID() into @id_vehicule FROM vehicule;

insert into image (chemin_image,id_vehicule) values ("./monchemin/image3.jpg", @id_vehicule);

select id_type_paiement into @type_paiement from type_paiement where nom_type_paiement="paypal";

insert into facture (date_facture, montant_facture, id_type_paiement, token_paiement_facture) 
values (now(),150.4, @type_paiement ,"JHJKHIA7987ZH");
SELECT distinct LAST_INSERT_ID() into @id_facture FROM facture;

insert into location (id_client,id_vehicule,id_facture,date_debut_location,date_fin_location)
values
(@id_client, @id_vehicule, @id_facture, now(),now());

-- test procedure stockee
call addVehicule("abis","berline","super98","renault","./mon/chemin/monimage.jpg", "rouge", 45.6,
"TW-000-XX", now(),"super voirure",65000,"GPS");
call addVehicule("abis","citadine","diesel","renault","./mon/chemin/monimage2.jpg", "verte", 60,
"TW-111-XX", now(),"super voirure",5000,"GPS");

-- utilisation de la vue pour voir les infos des véhicules
select * from liste_vehicule;

-- utilisation de la procedure stockee updateVehicule
call updateVehicule(6,6,"abis","citadine","diesel","renault","./mon/chemin/update.jpg", "verte", 60,
"TW-111-XX", now(),"update update",5000,"GPS");


