package fr.eni.dal;

import fr.eni.bo.Personne;
import fr.eni.bo.Voiture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureDaoBdd implements VoitureDal{
	
	/**
	 * CREATE : Methode permettant d'inserer dans la table utilisateurs
	 */
	@Override
	public void insert(Voiture voiture) throws DALException {
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("INSERT INTO voitures VALUES(null,?,?,?);",
				Statement.RETURN_GENERATED_KEYS);
				//Les 1, et 2 correspondent à la position du point d'interrogation que l'on veut remplacer par des données

				requete.setString(1, voiture.getNom());
				requete.setString(2, voiture.getImmatriculation());

				if (voiture.getConducteur() != null) {
					requete.setInt(3, voiture.getConducteur().getId());
				} else {
					requete.setNull(3, Types.INTEGER);
				}
				//J'execute ma requete
				requete.executeUpdate();
				
				ResultSet rs = requete.getGeneratedKeys();
				if (rs.next()) {
					int idGenereParLaBdd = rs.getInt(1);
					voiture.setId(idGenereParLaBdd);// Avec l'id gener� par la base de donn�es.
				}
			} catch (Exception e) {
				throw new DALException();
			}
	}



	@Override
	public void update(Voiture voiture) throws DALException {
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("UPDATE voitures SET nom=?,immatriculation=?,conducteur=? WHERE id=?");
			requete.setString(1, voiture.getNom());
			requete.setString(2, voiture.getImmatriculation());
			if (voiture.getConducteur() != null) {
				requete.setInt(3, voiture.getConducteur().getId());
			} else {
				requete.setNull(3, Types.INTEGER);
			}
			requete.setInt(4, voiture.getId());
			requete.executeUpdate();
		} catch (Exception e) {
			throw new DALException();
		}
		
	}

	@Override
	public void delete(Voiture voiture)  throws DALException{
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("DELETE FROM voitures WHERE Id=?");
			// on sélectionne la Voiture dont l'id est renseigné dans la requête
			requete.setInt(1, voiture.getId());
			requete.executeUpdate();
		} catch (Exception e) {
			throw new DALException();
		}
	}
		
	

	@Override
	public Voiture getById(int id) throws DALException {
		Voiture voiture = null;
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("SELECT * FROM voitures where id = ?");
			// on sélectionne la Voiture dont l'id est renseigné dans la requête
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();

			if (rs.next()) {
				//appelle de la fonction VoitureBuilder qui permet de récupérer toutes les colonnes de la table Voitures
				voiture = voitureBuilder(rs);
			}
		} catch (Exception e) {
			throw new DALException();
		}
		//Je retourne la Voiture sélectionnée
		return voiture;
	}
	
	@Override
	public List<Voiture> getList() throws DALException {
		//Permet de stocker la liste des utilisateurs de la table
		List<Voiture> resultat = new ArrayList<>();
		
		try(Connection cnx = DalUtils.getConnection())
		{
			// 2 j'ecris la requete
			PreparedStatement requete = cnx.prepareStatement("SELECT * FROM Voitures");
			// 3 j'execute la requete et rs contient la totalité des resultats de la requete.
			ResultSet rs = requete.executeQuery();
			// 4 enregistre le resultat de la requete dans la liste
			//Je lis l'enregistrement colonne par colonne et je stocke ces infos dans un objet utilisateur
			while(rs.next())
			{				
				//Je mets l'objet utilisateur dans la liste des utilisateurs
				//appelle de la fonction VoitureBuilder qui permet de récupérer toutes les colonnes de la table Voitures
				resultat.add(voitureBuilder(rs));
			}
		} catch (Exception e) {
			throw new DALException();
		}
		//Je retourne la liste des utilisateurs
		return resultat;

	}
	
	public static Voiture voitureBuilder(ResultSet rs) throws Exception {
		//permet de récupérer toutes les colonnes de la table Voitures
		Voiture voiture = new Voiture();
		//Création d'une  instance personneDao pour pouvoir compléter l'instance Personne
		PersonneDaoBdd personneDao = new PersonneDaoBdd();
		Personne personne = new Personne();
		voiture.setId(rs.getInt("id"));
		voiture.setNom(rs.getString("nom"));		
		voiture.setImmatriculation(rs.getString("immatriculation"));
		//Permet de vérifier que la personne est bien renseignée dans la table
			if(rs.getInt("conducteur") != 0) {
				//L'instance personne récupère les données de personneDao
				personne = personneDao.getById(rs.getInt("conducteur"));
				voiture.setConducteur(personne);
			}
		
		//Retourne une Voiture avec toutes ses informations
		return voiture;
	}

}

