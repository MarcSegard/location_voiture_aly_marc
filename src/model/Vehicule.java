package model;

import java.util.Date;

public class Vehicule {

	private int id;
	private String couleur;
	private float prix;
	private String immatriculation;
	private Date date_arrivee;
	private String description;
	private double kilometrage;
	private String options;
	private String agence;
	private String categorie;
	private String carburant;
	private String marque;
	private String chemin_image;

	public Vehicule() {
	}

	public Vehicule(int id, String couleur, float prix, String immatriculation, Date date_arrivee, String description,
			double kilometrage, String options, String agence, String categorie, String carburant, String marque,
			String chemin_image) {
		super();
		this.id = id;
		this.couleur = couleur;
		this.prix = prix;
		this.immatriculation = immatriculation;
		this.date_arrivee = date_arrivee;
		this.description = description;
		this.kilometrage = kilometrage;
		this.options = options;
		this.agence = agence;
		this.categorie = categorie;
		this.carburant = carburant;
		this.marque = marque;
		this.chemin_image = chemin_image;
	}

	public Vehicule(String couleur, float prix, String immatriculation, String description,
			double kilometrage, String options, String agence, String categorie, String carburant, String marque,
			String chemin_image) {
		super();
		this.couleur = couleur;
		this.prix = prix;
		this.immatriculation = immatriculation;
		this.description = description;
		this.kilometrage = kilometrage;
		this.options = options;
		this.agence = agence;
		this.categorie = categorie;
		this.carburant = carburant;
		this.marque = marque;
		this.chemin_image = chemin_image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Date getDate_arrivee() {
		return date_arrivee;
	}

	public void setDate_arrivee(Date date_arrivee) {
		this.date_arrivee = date_arrivee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(double kilometrage) {
		this.kilometrage = kilometrage;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAgence() {
		return agence;
	}

	public void setAgence(String agence) {
		this.agence = agence;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getCarburant() {
		return carburant;
	}

	public void setCarburant(String carburant) {
		this.carburant = carburant;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getChemin_image() {
		return chemin_image;
	}

	public void setChemin_image(String chemin_image) {
		this.chemin_image = chemin_image;
	}

}
