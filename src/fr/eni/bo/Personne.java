package fr.eni.bo;

/**
 * 
 * @author Elodie
 *8 mars 2021
	*GESTION_PARKING
	*
	*Represente un conducteur de voiture
 */

public class Personne {
	private int id;
	private String nom;
	private String prenom;
	
	public Personne() {
		super();
	}

	public Personne(int id, String nom, String prenom) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Personne(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "personne [id=" + id + ", nom=" + nom + ", prenom=" + prenom + "]";
	}
	
	
}