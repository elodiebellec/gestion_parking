package fr.eni.dal;

import fr.eni.bo.Personne;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;





public class PersonneDaoBdd implements PersonneDal{
	
	/**
	 * CREATE : Methode permettant d'inserer dans la table utilisateurs
	 */
	@Override
	public void insert(Personne personne) throws DALException {
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("INSERT INTO personnes VALUES (null,?,?);",
					Statement.RETURN_GENERATED_KEYS);
				//Les 1, et 2 correspondent à la position du point d'interrogation que l'on veut remplacer par des données
				requete.setString(1, personne.getNom());
				requete.setString(2, personne.getPrenom());
				//J'execute ma requete
				requete.executeUpdate();
			
				ResultSet rs = requete.getGeneratedKeys();
				if (rs.next()) {
					int idGenereParLaBdd = rs.getInt(1);
					personne.setId(idGenereParLaBdd);// Avec l'id gener� par la base de donn�es.
				}
			} catch (Exception e) {
			throw new DALException();
			}
	}



	@Override
	public void update(Personne personne) throws DALException {
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("UPDATE personnes SET nom=?,prenom=? WHERE id=?");
			requete.setString(1, personne.getNom());
			requete.setString(2, personne.getPrenom());
			requete.setInt(3, personne.getId());
			requete.executeUpdate();
		} catch (Exception e) {
			throw new DALException();
		}
		
	}

	@Override
	public void delete(Personne personne) throws DALException {
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("DELETE FROM personnes WHERE Id=?");
			// on sélectionne la personne dont l'id est renseigné dans la requête
			requete.setInt(1, personne.getId());
			requete.executeUpdate();
		} catch (Exception e) {
			throw new DALException();
		}
	}
		
	


	@Override
	public Personne getById(int id) throws DALException {
		Personne personne = null;
		//Connexion à la base de données et try pour que la connexion se ferme automatiquement.
		try(Connection cnx = DalUtils.getConnection()) {
			//requete
			PreparedStatement requete = cnx.prepareStatement("SELECT * FROM personnes where id = ?");
			// on sélectionne la personne dont l'id est renseigné dans la requête
			requete.setInt(1, id);
			ResultSet rs = requete.executeQuery();

			if (rs.next()) {
				//appelle de la fonction personneBuilder qui permet de récupérer toutes les colonnes de la table personnes
				personne = personneBuilder(rs);
			}
		} catch (Exception e) {
			throw new DALException();
		}
		//Je retourne la personne sélectionnée
		return personne;
	}
	
	@Override
	public List<Personne> getList() throws DALException {
		//Permet de stocker la liste des utilisateurs de la table
		List<Personne> resultat = new ArrayList<>();
		
		try(Connection cnx = DalUtils.getConnection())
		{
			// 2 j'ecris la requete
			PreparedStatement requete = cnx.prepareStatement("SELECT * FROM personnes");
			// 3 j'execute la requete et rs contient la totalité des resultats de la requete.
			ResultSet rs = requete.executeQuery();
			// 4 enregistre le resultat de la requete dans la liste
			//Je lis l'enregistrement colonne par colonne et je stocke ces infos dans un objet utilisateur
			while(rs.next())
			{				
				//Je mets l'objet utilisateur dans la liste des utilisateurs
				//appelle de la fonction personneBuilder qui permet de récupérer toutes les colonnes de la table personnes
				resultat.add(personneBuilder(rs));
			}
		} catch (Exception e) {
			throw new DALException();
		}
		//Je retourne la liste des utilisateurs
		return resultat;

	}
	
	public static Personne personneBuilder(ResultSet rs) throws Exception {
		//permet de récupérer toutes les colonnes de la table personnes
		Personne personne = new Personne();
		personne.setId(rs.getInt("id"));
		personne.setNom(rs.getString("nom"));
		personne.setPrenom(rs.getString("prenom"));
		//Retourne une personne avec toutes ses informations
		return personne;
	}

}
