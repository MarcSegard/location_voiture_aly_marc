use agence_aly_marc;


create user 'client'@'localhost' identified by 'client';
create user 'agence'@'localhost' identified by 'agence';
-- create user 'admin'@'localhost' identified by 'etnoncenestpasadmin';

-- droit pour le client
grant insert on agence_aly_marc.location to 'client'@'localhost';
grant insert on  agence_aly_marc.facture to 'client'@'localhost';
grant select on  agence_aly_marc.type_paiement to 'client'@'localhost';
grant select on  agence_aly_marc.vehicule to 'client'@'localhost';
grant select on agence_aly_marc.image to 'client'@'localhost';
grant select on agence_aly_marc.marque to 'client'@'localhost';
grant select on agence_aly_marc.carburant to 'client'@'localhost';
grant select on agence_aly_marc.categorie to 'client'@'localhost';
grant select on agence_aly_marc.liste_vehicule to 'client'@'localhost';
grant select,insert,update,delete on agence_aly_marc.client to 'client'@'localhost';

-- droit pour l'agence
grant select,insert on agence_aly_marc.location to 'agence'@'localhost';
grant select,insert on  agence_aly_marc.facture to 'agence'@'localhost';
grant select on  agence_aly_marc.type_paiement to 'agence'@'localhost';
grant select,insert,update on  agence_aly_marc.vehicule to 'agence'@'localhost';
grant select,insert,update on agence_aly_marc.image to 'agence'@'localhost';
grant select,insert,update on agence_aly_marc.marque to 'agence'@'localhost';
grant select,insert,update on agence_aly_marc.carburant to 'agence'@'localhost';
grant select,insert,update on agence_aly_marc.categorie to 'agence'@'localhost';
grant select,insert,update on agence_aly_marc.liste_vehicule to 'agence'@'localhost';
grant select on agence_aly_marc.client to 'agence'@'localhost';
GRANT EXECUTE ON PROCEDURE agence_aly_marc.addVehicule TO 'agence'@'localhost';
GRANT EXECUTE ON PROCEDURE agence_aly_marc.updateVehicule TO 'agence'@'localhost';

-- droit pour l'admin
-- grant all on agence_aly_marc.* to 'admin'@'localhost';


-- SHOW GRANTS FOR 'client'@'localhost';
SHOW GRANTS FOR 'agence'@'localhost';
-- SHOW GRANTS FOR 'admin'@'localhost';

-- DROP USER 'agence'@'localhost';



