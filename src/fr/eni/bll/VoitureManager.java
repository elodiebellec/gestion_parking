package fr.eni.bll;


import fr.eni.bo.Voiture;
import fr.eni.dal.DALException;
import fr.eni.dal.DalFactory;
import fr.eni.dal.VoitureDal;

import java.util.List;

public class VoitureManager {


    private VoitureDal dalVoiture = DalFactory.getVoitureDalFactory();

    private static VoitureManager INSTANCE = null;

    private VoitureManager() {
    }

    public static VoitureManager getInstance() throws BllException {
        if (INSTANCE == null) {
            INSTANCE = new VoitureManager();
        }
        return INSTANCE;
    }

    public List<Voiture> getListVoiture() throws BllException {
        try {
            return dalVoiture.getList();
        } catch (DALException e) {
            throw new BllException("Erreur dans get list Voiture");
        }
    }

    public void validerVoiture(Voiture voiture) throws BllException {

        if ("".equals(voiture.getNom())){
            throw new BllException("Nom incorrecte");
        }

        if ("".equals(voiture.getImmatriculation())){
            throw new BllException("Immatriculation incorrecte");
        }
    }

    public void addVoiture(Voiture voiture) throws BllException {
       validerVoiture(voiture);
        try {
            dalVoiture.insert(voiture);
        } catch (DALException e) {
            throw new BllException("Erreur dans insert voiture");
        }
    }

    public void updateVoiture(Voiture voiture) throws BllException {
        validerVoiture(voiture);
        try {
            dalVoiture.update(voiture);
        } catch (DALException e) {
            throw new BllException("Erreur dans updateVoiture" + e.getMessage());
        }
    }

    public Voiture getVoiture(int index) throws BllException {
        try {
            return dalVoiture.getById(index);
        } catch (DALException e) {
            throw new BllException("Erreur dans getPersonne" + e.getMessage());
        }
    }

    public void removeVoiture(Voiture voiture) throws BllException {
        try {
            dalVoiture.delete(voiture);
        } catch (DALException e) {
            throw new BllException("Erreur dans removePersonne" + e.getMessage());
        }
    }



}
