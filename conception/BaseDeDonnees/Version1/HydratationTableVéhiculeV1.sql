use agence_aly_marc;

insert into agence (nom_agence, adresse_agence, ca_agence, SIRET_agence)
values ("ABIS", "2 rue des Fleus Bleues 75004 Paris", 0 , "123 568 941 00056");

call addVehicule("abis","petite citadine","électrique","fiat","500","/assets/images/petite_citadine/Fiat500_electrique.png", "bleue", 35,
"TW-222-YZ", now(),"Véhicule très maniable. Idéal pour vos trajets en ville. ",5000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","petite citadine","électrique","citroën","Ami", "/assets/images/petite_citadine/citroen-ami.png", "grise", 25,
"TW-333-YZ", now(),"Véhicule très maniable. Idéal pour vos déplacement en centre ville ",5000,"Vitres");

call addVehicule("abis","petite citadine","électrique","mercedes","Smart","/assets/images/petite_citadine/smart_mercedes.png", "noire", 45,
"TW-444-YZ", now(),"Véhicule très maniable. Idéal pour vos déplacement en centre ville ",6000,"Vitres");

call addVehicule("abis","petite citadine","super98","renault","Twingo", "/assets/images/petite_citadine/Twingo_electrique.png", "bleue", 40.5,
"TW-555-YZ", now(),"Véhicule idéal pour tajet de 4 personnes en ville ",6000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","citadine","super95","renault","Capture","/assets/images/citadine/CaptureElectrique.png", "rouge", 42,
"TW-666-YZ", now(),"Véhicule à l'aise aussi bien en ville qu'à la campagne",16000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","citadine","diesel","renault","Clio","/assets/images/citadine/ClioElectrique.png", "bleue", 36.8,
"TW-777-YZ", now(),"Véhicule à l'aise aussi bien en ville qu'à la campagne",17000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","citadine","électrique","renault","Zoe","/assets/images/citadine/ZoeElectrique.png", "bleue", 32.8,
"TW-888-YZ", now(),"Véhicule à l'aise aussi bien en ville qu'à la campagne",10000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","berline","hybride","renault","Arkana","/assets/images/berline/ArkanaHybrid.png", "noire", 46.8,
"TW-999-YZ", now(),"Véhicule confortable pour les longs trajets.",23000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","berline","hybride","citroën","C3","/assets/images/berline/C3SuperDiesel.png", "blanche", 46.8,
"TW-000-AA", now(),"C3 confortable pour toute la famille",23000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","berline","électrique","fiat","Tipo","/assets/images/berline/FiatTipoElectrique.png", "orange", 39.2,
"TW-001-AA", now(),"Fiat Tipo. Très confortable pour une Fiat",23000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","berline","électrique","renault","Megane","/assets/images/berline/Megane_Electrique.png", "grise", 28.9,
"TW-002-AA", now(),"Megane électrique, très maniable et en plus ne pollue pas.",34000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","berline","hybride","renault","Mégane","/assets/images/berline/MéganeHybrid.png", "grise", 32.9,
"TW-003-AA", now(),"Megane hybride, très maniable et en plus ne pollue pas.",30000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","berline","électrique","peugeot","308","/assets/images/berline/peugeot308Electrique.png", "grise", 33.9,
"TW-004-AA", now(),"Peugeot 308 hybride, très maniable et en plus ne pollue pas.",30000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","monospace","diesel","renault","Espace","/assets/images/monospace/EspaceSuperDiesel.png", "grise", 53.9,
"TW-005-AA", now(),"Véhicule spacieux idéal pour les longs trajets ou les personnes ayant besoin d'un coffre volumineux.",
30000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","monospace","diesel","renault","Grand Scénic","/assets/images/monospace/GrandScénicSuperGasoil.png", "noire", 55.9,
"TW-006-AA", now(),"Véhicule familial, spacieux idéale pour les voyages", 1000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","utilitaire","hybride","citroën","Berlingo","/assets/images/utilitaire/Citroen_Berlingo.png", "grise", 35.9,
"TW-007-AA", now(),"Véhicule utilitaire, idéale pour les travaux ou activités nécessitant un grand coffre", 4000,"GPS");

call addVehicule("abis","utilitaire","super98","renault","Kangoo","/assets/images/utilitaire/KangooSuperDiesel.png", "rouge", 37.9,
"TW-008-AA", now(),"Véhicule utilitaire, idéale pour les travaux ou activités nécessitant un grand coffre", 8000,"GPS climatisation");

call addVehicule("abis","utilitaire","hybride","renault","Mégane Estate","/assets/images/utilitaire/MeganeEstateHybride.png", "bleue", 47.9,
"TW-009-AA", now(),"Véhicule utilitaire, idéale pour les travaux ou activités nécessitant un grand coffre", 23000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","utilitaire","diesel","renault","Traffic Combi","/assets/images/utilitaire/TrafficCombiSuperDiesel.png", "rouge", 47.9,
"TW-010-AA", now(),"Véhicule utilitaire, idéale pour les travaux ou activités nécessitant un grand coffre", 23000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","utilitaire","super98","renault","Traffic Space","/assets/images/utilitaire/TraficSpaceSuperDiesel.png", "noire", 51.9,
"TW-011-AA", now(),"Véhicule utilitaire, idéale pour les travaux ou activités nécessitant un grand coffre", 2000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","électrique","BMW","iX","/assets/images/SUV/BMWiX_electrique.png", "rouge", 71.9,
"TW-012-AA", now(),"Véhicule sportif, idéal pour promenade à la campgane", 2500,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","super98","BMW","iX M60","/assets/images/SUV/BMWiX_M60_electrique.png", "bleue", 75.6,
"TW-111-YZ", now(),"SUV de marque BMW spacieux et confortable pour vos ballages en campagne.",10000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","électrique","citroën","C4","/assets/images/SUV/C4_Rouge_Electrique.png", "rouge", 76.6,
"TW-112-YZ", now(),"SUV  spacieux et confortable pour vos ballages en campagne.",13000,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","hybride","citroën","C5","/assets/images/SUV/C5_Aircross_Hybride.png", "bleue", 66.6,
"TW-113-YZ", now(),"SUV  spacieux et confortable pour vos ballages en campagne.",13500,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","électrique","fiat","Panda","/assets/images/SUV/FiatPandaElectrique.png", "blanche", 46.6,
"TW-114-YZ", now(),"SUV  spacieux et confortable pour vos ballages en campagne.",3500,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","diesel","renault","Koléos","/assets/images/SUV/KoléosSuperDiesel.png", "noire", 51.6,
"TW-115-YZ", now(),"SUV  spacieux et confortable pour vos ballages en campagne.",5500,"GPS / Android Auto / AppleCar");

call addVehicule("abis","SUV","électrique","audi","Q4","/assets/images/SUV/Q4e-tron.png", "grise", 81.6,
"TW-116-YZ", now(),"SUV  spacieux et confortable pour vos ballages en campagne.",15500,"GPS / Android Auto / AppleCar");

