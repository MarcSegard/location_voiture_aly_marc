package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Vehicule;
import myConnection.Connect;

public class VehiculeDao implements IDao<Vehicule> {

	// Il faudra faire attention à l'utilisateur ou agence pour la connection et les
	// droits
	Connection connect = Connect.getConnection();
	PreparedStatement sql;
	ResultSet rs;

	@Override
	public boolean create(Vehicule vehicule) {

		try {
			sql = connect.prepareStatement("{call addVehicule(?,?,?,?,?,?, ?,?, now(),?,?,?)}");
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public ArrayList<Vehicule> read() {
		ArrayList<Vehicule> vehicules = new ArrayList<>();
		try {
			sql = connect.prepareStatement("select * from liste_vehicule");
			rs = sql.executeQuery();
			Vehicule vehicule;

			while (rs.next()) {
				vehicule = new Vehicule(rs.getInt("id_vehicule"), rs.getString("couleur_vehicule"),
						rs.getFloat("prix_vehicule"), rs.getString("immatriculation_vehicule"),
						rs.getDate("date_arrivee_vehicule"), rs.getString("description_vehicule"),
						rs.getDouble("kilometrage_vehicule"), rs.getString("options_vehicule"),
						rs.getString("nom_agence"), rs.getString("nom_categorie"), rs.getString("nom_carburant"),
						rs.getString("nom_marque"), rs.getString("modele_vehicule"),rs.getString("chemin_image"));
				vehicules.add(vehicule);
			}
			return vehicules;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean update(int id_image,Vehicule vehicule) {
		try {
			sql = connect.prepareStatement("{call updateVehicule(?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?)}");
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
			sql.setDate(12 ,vehicule.getDate_arrivee());
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
}
