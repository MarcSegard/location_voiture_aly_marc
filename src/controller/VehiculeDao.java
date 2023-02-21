package controller;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Vehicule;
import myConnection.Connect;

public class VehiculeDao implements IDao<Vehicule> {

	PreparedStatement sql;
	ResultSet rs;

	@Override
	public boolean create(Vehicule vehicule) {

		try {
			sql = Connect.currentConnection.prepareStatement("{call addVehicule(?,?,?,?,?,?,?,?,?,now(),?,?,?)}");
			sql.setString(1, vehicule.getAgence());
			sql.setString(2, vehicule.getCategorie());
			sql.setString(3, vehicule.getCarburant());
			sql.setString(4, vehicule.getMarque());
			sql.setString(5, vehicule.getModele_vehicule());
			sql.setString(6, vehicule.getChemin_image());
			sql.setString(7, vehicule.getCouleur());
			sql.setFloat(8, vehicule.getPrix());
			sql.setString(9, vehicule.getImmatriculation());
			sql.setString(10, vehicule.getDescription());
			sql.setDouble(11, vehicule.getKilometrage());
			sql.setString(12, vehicule.getOptions());

			sql.execute();
			System.out.println("L'injection par porcedure stockee a marchée !!!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Vehicule> read() {
		ArrayList<Vehicule> vehicules = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select * from liste_vehicule order by prix_vehicule");
			rs = sql.executeQuery();
			Vehicule vehicule;

			while (rs.next()) {
				vehicule = new Vehicule(rs.getInt("id_vehicule"), rs.getString("couleur_vehicule"),
						rs.getFloat("prix_vehicule"), rs.getString("immatriculation_vehicule"),
						rs.getDate("date_arrivee_vehicule"), rs.getString("description_vehicule"),
						rs.getDouble("kilometrage_vehicule"), rs.getString("options_vehicule"),
						rs.getString("nom_agence"), rs.getString("nom_categorie"), rs.getString("nom_carburant"),
						rs.getString("nom_marque"), rs.getString("modele_vehicule"), rs.getString("chemin_image"));
				vehicules.add(vehicule);
			}
			return vehicules;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getMarque() {
		ArrayList<String> marques = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct nom_marque from marque");
			rs = sql.executeQuery();

			while (rs.next()) {
				marques.add(rs.getString("nom_marque"));
			}
			return marques;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getCategorie() {
		ArrayList<String> categories = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct nom_categorie from categorie");
			rs = sql.executeQuery();

			while (rs.next()) {
				categories.add(rs.getString("nom_categorie"));
			}
			return categories;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getMoyenPaiement() {
		ArrayList<String> paiements = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct nom_type_paiement from type_paiement");
			rs = sql.executeQuery();

			while (rs.next()) {
				paiements.add(rs.getString("nom_type_paiement"));
			}
			return paiements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getCarburant() {
		ArrayList<String> carburants = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct nom_carburant from carburant");
			rs = sql.executeQuery();

			while (rs.next()) {
				carburants.add(rs.getString("nom_carburant"));
			}
			return carburants;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getModele() {
		ArrayList<String> modeles = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct modele_vehicule from vehicule");
			rs = sql.executeQuery();

			while (rs.next()) {
				modeles.add(rs.getString("modele_vehicule"));
			}
			return modeles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getCouleur() {
		ArrayList<String> couleurs = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct couleur_vehicule from vehicule");
			rs = sql.executeQuery();

			while (rs.next()) {
				couleurs.add(rs.getString("couleur_vehicule"));
			}
			return couleurs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<String> getImmatriculation() {
		ArrayList<String> listimta = new ArrayList<>();
		try {
			sql = Connect.currentConnection.prepareStatement("select distinct immatriculation_vehicule from vehicule");
			rs = sql.executeQuery();

			while (rs.next()) {
				listimta.add(rs.getString("immatriculation_vehicule"));
			}
			return listimta;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean update(int id_image, Vehicule vehicule) {
		try {
			sql = Connect.currentConnection.prepareStatement("{call updateVehicule(?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
			sql.setInt(1, vehicule.getId());
			sql.setInt(2, id_image);
			sql.setString(3, vehicule.getAgence());
			sql.setString(4, vehicule.getCategorie());
			sql.setString(5, vehicule.getCarburant());
			sql.setString(6, vehicule.getMarque());
			sql.setString(7, vehicule.getModele_vehicule());
			sql.setString(8, vehicule.getChemin_image());
			sql.setString(9, vehicule.getCouleur());
			sql.setFloat(10, vehicule.getPrix());
			sql.setString(11, vehicule.getImmatriculation());
			sql.setDate(12, vehicule.getDate_arrivee());
			sql.setString(13, vehicule.getDescription());
			sql.setDouble(14, vehicule.getKilometrage());
			sql.setString(15, vehicule.getOptions());
			sql.execute();

			System.out.println("Update OK");
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Vehicule findByImmatriculation(String immatriculation) {
		Vehicule vehicule = null;
		try {
			sql = Connect.currentConnection.prepareStatement("SELECT*FROM liste_vehicule WHERE immatriculation_vehicule = ?");
			sql.setString(1, immatriculation);
			rs = sql.executeQuery();
			while (rs.next()) {

				vehicule = new Vehicule(rs.getInt("id_vehicule"), rs.getString("couleur_vehicule"),
						rs.getFloat("prix_vehicule"), rs.getString("immatriculation_vehicule"),
						rs.getDate("date_arrivee_vehicule"), rs.getString("description_vehicule"),
						rs.getDouble("kilometrage_vehicule"), rs.getString("options_vehicule"),
						rs.getString("nom_agence"), rs.getString("nom_categorie"), rs.getString("nom_carburant"),
						rs.getString("nom_marque"), rs.getString("modele_vehicule"), rs.getString("chemin_image"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Recherche non aboutie veuillez ressayer!!");
		}
		return vehicule;
	}

	public boolean createLocation(Vehicule vehicule, int id_client_in, String type_paiement_in,
			float montant_facture_in, Date start_in, Date end_in) {
		boolean check = false;
		try {
			int id_type_paiement = -1;
			int id_facture = -1;
			sql = Connect.currentConnection.prepareStatement("SELECT id_type_paiement FROM type_paiement WHERE nom_type_paiement=?");
			sql.setString(1, type_paiement_in);
			rs = sql.executeQuery();

			if (rs.next()) {
				id_type_paiement = rs.getInt("id_type_paiement");
			}

			if (id_type_paiement != -1) {
				// Insertion facture
				sql = Connect.currentConnection.prepareStatement(
						"insert into facture (date_facture,montant_facture,id_type_paiement,token_paiement_facture)"
								+ " values (now(),?,?, 'YUIBVFY87654EDF7UHGT')");
				sql.setFloat(1, montant_facture_in);
				sql.setInt(2, id_type_paiement);
				sql.execute();

				// Récupération de l'id de la facture crée
				sql = Connect.currentConnection.prepareStatement("select distinct LAST_INSERT_ID() as id from facture");
				rs = sql.executeQuery();

				if (rs.next()) {
					id_facture = rs.getInt("id");
				}

				if (id_facture != -1) {
					// insertion location
					sql = Connect.currentConnection.prepareStatement(
							"insert into location (id_client,id_vehicule,id_facture,date_debut_location, date_fin_location)"
									+ " values (?,?,?,?,?)");
					sql.setInt(1, id_client_in);
					sql.setInt(2, vehicule.getId());
					sql.setInt(3, id_facture);
					sql.setDate(4, new java.sql.Date(start_in.getTime()));
					sql.setDate(5, new java.sql.Date(end_in.getTime()));
					sql.execute();
				}

			}

			check = true;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return check;
	}

	public int getIdImage(String chemin_in) {
		int imageid = -1;
		try {
			sql = Connect.currentConnection.prepareStatement("SELECT id_image FROM image WHERE chemin_image = ?");

			sql.setString(1, chemin_in);
			rs = sql.executeQuery();
			System.out.println("je suis getId");
			while (rs.next()) {
				imageid = rs.getInt("id_image");

				System.out.println(imageid + " id image trouver ");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageid;
	}
}
