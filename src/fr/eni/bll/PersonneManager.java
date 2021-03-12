package fr.eni.bll;

import fr.eni.bo.Personne;
import fr.eni.bo.Voiture;
import fr.eni.dal.DALException;
import fr.eni.dal.DalFactory;
import fr.eni.dal.PersonneDal;
import fr.eni.dal.VoitureDal;

import java.util.List;

public class PersonneManager {
    private PersonneDal dalPersonne = DalFactory.getPersonneDalFactory();


    private static PersonneManager INSTANCE = null;

    private PersonneManager() {
    }

    public static PersonneManager getInstance() throws BllException {
        if (INSTANCE == null) {
            INSTANCE = new PersonneManager();
        }
        return INSTANCE;
    }

    public List<Personne> getListPersonne() throws BllException {
        try {
            return dalPersonne.getList();
        } catch (DALException e) {
            throw new BllException("Erreur dans get list Personne");
        }
    }

    public void validerPersonne(Personne personne) throws BllException {

        if ("".equals(personne.getNom())){
            throw new BllException("Nom incorrecte");
        }

        if ("".equals(personne.getPrenom())){
            throw new BllException("Prénom incorrecte");
        }
    }

    public void addPersonne(Personne personne) throws BllException {
        validerPersonne(personne);
        try {
            dalPersonne.insert(personne);
        } catch (DALException e) {
            throw new BllException("Erreur dans insert Personne");
        }
    }

    public void updatePersonne(Personne personne) throws BllException {
        validerPersonne(personne);
        try {
            dalPersonne.update(personne);
        } catch (DALException e) {
            throw new BllException("Erreur dans updatePersonne" + e.getMessage());
        }
    }

    public Personne getPersonne(int index) throws BllException {
        try {
            return dalPersonne.getById(index);
        } catch (DALException e) {
            throw new BllException("Erreur dans getPersonne" + e.getMessage());
        }
    }

    public void removePersonne(Personne personne) throws BllException {
        try {
            dalPersonne.delete(personne);
        } catch (DALException e) {
            throw new BllException("Erreur dans removePersonne" + e.getMessage());
        }
    }



}
