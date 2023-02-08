create or replace database agence_aly_marc;
use agence_aly_marc;

create or replace table categorie (id_categorie int primary key auto_increment, nom_categorie varchar (50) not null );
create or replace table carburant (id_carburant int primary key auto_increment, nom_carburant varchar (50) not null);
create or replace table marque (id_marque int primary key auto_increment, nom_marque varchar (50) not null);
create or replace table image (id_image int primary key auto_increment, chemin_image varchar (250) not null,
id_vehicule int not null);

create or replace table agence (id_agence int primary key auto_increment, nom_agence varchar (150) not null, 
adresse_agence varchar (250) not null, ca_agence double not null, SIRET_agence varchar (30) not null);

create or replace table vehicule (id_vehicule int primary key auto_increment, couleur_vehicule varchar(20) not null,
prix_vehicule float not null, immatriculation_vehicule varchar (20), date_arrivee_vehicule date not null, 
description_vehicule text not null, kilometrage_vehicule double not null,
options_vehicule text not null, id_agence int not null, id_categorie int not null,
id_carburant int not null, id_marque int not null);

alter table vehicule add constraint fk_vehicule_agence foreign key (id_agence) references agence(id_agence);
alter table vehicule add constraint fk_vehicule_categorie foreign key (id_categorie) references categorie(id_categorie);
alter table vehicule add constraint fk_vehicule_carburant foreign key (id_carburant) references carburant(id_carburant);
alter table vehicule add constraint fk_vehicule_marque foreign key (id_marque) references marque(id_marque);
alter table image add constraint fk_vehicule_image foreign key (id_vehicule) references vehicule(id_vehicule);

create or replace table client (id_client int primary key auto_increment, nom_client varchar(30) not null, 
prenom_client varchar (30) not null, email_client varchar(50) not null, password_client varchar (250) not null, permis_client varchar(20) not null);

create or replace table type_paiement (id_type_paiement int primary key auto_increment, nom_type_paiement varchar (100) not null);

create or replace table facture (id_facture int primary key auto_increment, date_facture date not null, montant_facture float not null,
id_type_paiement int not null, token_paiement_facture varchar(250) not null);


alter table facture add constraint fk_facture_type_paiement foreign key (id_type_paiement) references type_paiement(id_type_paiement);

create or replace table location (id_location int primary key auto_increment, id_client int not null, id_vehicule int not null,
id_facture int not null, date_debut_location date not null, date_fin_location date not null);

alter table location add constraint fk_location_client foreign key (id_client) references client(id_client);
alter table location add constraint fk_location_vehicule foreign key (id_vehicule) references vehicule(id_vehicule);
alter table location add constraint fk_location_facture foreign key (id_facture) references facture(id_facture);

insert into marque (nom_marque) values ("renault"),("peugeot"),("citroën"),("fiat"),("BMW");
insert into carburant (nom_carburant) values ("diesel"),("super98"),("super95"),("électrique"),("hybride");
insert into categorie (nom_categorie) values ("petite citadine"),("citadine"),("SUV"),("berline"),("monospace"),("utilitaire");
insert into type_paiement (nom_type_paiement) values ("paypal"),("VISA"),("MASTER CARD");

-- trigger mise à jour CA de l'agence
delimiter \\
create  or replace trigger MaJ_CA_agence
 after insert on facture
	for each row
    begin
    select id_agence into @id_agence from agence inner join vehicule using (id_agence)
           inner join location using (id_vehicule)
           inner join facture using (id_facture)
           where id_facture= NEW.id_facture;
		update  agence set ca_agence = (select ca_agence from agence where id_agence=@id_agence) + new.montant_facture where id_agence=@id_agence;
	end \\
delimiter ;

-- procedure stockee ajout véhicule
delimiter \\
create OR replace procedure addVehicule(IN 
in_nom_agence varchar(150),
in_nom_categorie varchar(50),
in_nom_carburant varchar(50),
in_nom_marque varchar(50),
in_chemin_image varchar(250),
in_couleur_vehicule varchar(20),
in_prix_vehicule float,
in_immatriculation varchar (20),
in_date_arrivee_vehicule date,
in_description_vehicule text,
in_kilometrage_vehicule double,
in_options_vehicule text)
begin
	select id_agence into @id_agence from agence where nom_agence=in_nom_agence;
	select id_categorie into @id_categorie from categorie where nom_categorie=in_nom_categorie;
    select id_carburant into @id_carburant from carburant where nom_carburant=in_nom_carburant;
    select id_marque into @id_marque from marque where nom_marque=in_nom_marque;
    insert into vehicule (couleur_vehicule, prix_vehicule, immatriculation_vehicule, date_arrivee_vehicule,
    description_vehicule, kilometrage_vehicule, options_vehicule, id_agence, id_categorie, id_carburant,id_marque)
    values (in_couleur_vehicule, in_prix_vehicule, in_immatriculation, in_date_arrivee_vehicule,
    in_description_vehicule, in_kilometrage_vehicule, in_options_vehicule, @id_agence, @id_categorie, 
    @id_carburant,@id_marque);
    SELECT distinct LAST_INSERT_ID() into @id_vehicule FROM vehicule;
    insert into image (chemin_image,id_vehicule) values (in_chemin_image,@id_vehicule);
end\\
delimiter ;

-- procedure stockee update véhicule
delimiter \\
create OR replace procedure updateVehicule(IN 
in_id_vehicule int,
in_id_image int,
in_nom_agence varchar(150),
in_nom_categorie varchar(50),
in_nom_carburant varchar(50),
in_nom_marque varchar(50),
in_chemin_image varchar(250),
in_couleur_vehicule varchar(20),
in_prix_vehicule float,
in_immatriculation varchar (20),
in_date_arrivee_vehicule date,
in_description_vehicule text,
in_kilometrage_vehicule double,
in_options_vehicule text)
begin
	select id_agence into @id_agence from agence where nom_agence=in_nom_agence;
	select id_categorie into @id_categorie from categorie where nom_categorie=in_nom_categorie;
    select id_carburant into @id_carburant from carburant where nom_carburant=in_nom_carburant;
    select id_marque into @id_marque from marque where nom_marque=in_nom_marque;
    update vehicule set
    couleur_vehicule = in_couleur_vehicule,
    prix_vehicule = in_prix_vehicule,
    immatriculation_vehicule = in_immatriculation,
    date_arrivee_vehicule = in_date_arrivee_vehicule,
    description_vehicule = in_description_vehicule,
    kilometrage_vehicule = in_kilometrage_vehicule,
    options_vehicule = in_options_vehicule,
    id_agence=@id_agence,
    id_categorie=@id_categorie,
    id_carburant=@id_carburant,
    id_marque= @id_marque 
    where id_vehicule= in_id_vehicule;
    update image set chemin_image = in_chemin_image where id_image=in_id_image;
end\\
delimiter ;



-- creation d'une vue pour avoir toutes les informations
-- concernant un véhicule
create or replace view liste_vehicule as
select * from vehicule 
inner join categorie using(id_categorie)
inner join carburant using(id_carburant)
inner join marque using(id_marque)
inner join image using (id_vehicule)
inner join agence using (id_agence);


