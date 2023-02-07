package model;

public class User {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private String password;
	private String permis;


	public User(String nom, String prenom, String email, String permis, String password) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.permis = permis;

	}
	
	public User(String nom, String prenom, String email,String permis, int id) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.id = id;
		this.permis = permis;

	}

	public User(String nom, String prenom, String email,String permis) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = null;
		this.permis = permis;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPermis() {
		return permis;
	}

	public void setPermis(String permis) {
		this.permis = permis;
	}
}
