package fr.eni.bo;

/**
 * 
 * @author Elodie
 *8 mars 2021
	*GESTION_PARKING
	*
	*Représente une voiture
 */

public class Voiture {
	private int id;
	private String nom;
	private String immatriculation;
	private Personne conducteur;
	
	public Voiture() {
		
	}
	
	public Voiture(int id, String nom, String immatriculation, Personne conducteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.immatriculation = immatriculation;
		this.conducteur = conducteur;
	}
	
	public Voiture(String nom, String immatriculation, Personne conducteur) {
		super();
		this.nom = nom;
		this.immatriculation = immatriculation;
		this.conducteur = conducteur;
	}
	
	public Voiture(String nom, String immatriculation) {
		super();
		this.nom = nom;
		this.immatriculation = immatriculation;
		this.conducteur = null;
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

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Personne getConducteur() {
		return conducteur;
	}

	public void setConducteur(Personne conducteur) {
		this.conducteur = conducteur;
	}
	
	public void setConducteurById(int conducteurId) {
		this.conducteur.setId(conducteurId);
	}
	

	

	@Override
	public String toString() {
		return "voiture [id=" + id + ", nom=" + nom + ", immatriculation=" + immatriculation + ", conducteur="
				+ conducteur + "]";
	}
	
	
}
