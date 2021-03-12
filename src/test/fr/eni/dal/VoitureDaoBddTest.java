package test.fr.eni.dal;

import fr.eni.bo.Personne;
import fr.eni.bo.Voiture;
import fr.eni.dal.DalFactory;
import fr.eni.dal.PersonneDal;
import fr.eni.dal.VoitureDal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoitureDaoBddTest {

    @Test
    void insert() {
        try {
            Voiture voiture = new Voiture();
            List<Voiture> listeVoiture = null;
            //Récupération du premier conducteur de la liste pour l'ajouter dans la colonne conducteur
            PersonneDal dalConducteur = DalFactory.getPersonneDalFactory();
            Personne conducteur = dalConducteur.getById(75);

            voiture.setNom("k2000");
            voiture.setImmatriculation("007");
            voiture.setConducteur(conducteur);
            VoitureDal dal = DalFactory.getVoitureDalFactory();
            listeVoiture = dal.getList();
            int avant = listeVoiture.size();
            dal.insert(voiture);
            listeVoiture = dal.getList();
            int apres = listeVoiture.size();
            int resulat = apres - avant;
            assertEquals(1, resulat);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void selectById() {
        try {
            VoitureDal dal = DalFactory.getVoitureDalFactory();
            List<Voiture> liste = dal.getList();
            if (liste.size() > 0) {
                Voiture voiture = liste.get(0);
                Voiture voitureATester = dal.getById(voiture.getId());
                assertEquals(voiture.getId(), voitureATester.getId());
                assertTrue(voiture.getNom().equals(voitureATester.getNom()));
                assertEquals(voiture.getImmatriculation(), voitureATester.getImmatriculation());
                assertEquals(voiture.getConducteur(), voitureATester.getConducteur());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void selectAll() {
        try {
            Voiture voiture = new Voiture();
            voiture.setNom("Kangoo");
            voiture.setImmatriculation("35KE230");
            voiture.setConducteur(null);
            VoitureDal dal = DalFactory.getVoitureDalFactory();
            dal.insert(voiture);
            List<Voiture> liste = dal.getList();
            if (liste.size() == 0) {
                fail("On recupere rien");
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void update() {
        try {
            List<Voiture> liste = null;
            VoitureDal dal = DalFactory.getVoitureDalFactory();
            liste = dal.getList();
            Voiture voitureAModifier = liste.get(0);
            voitureAModifier.setNom("bumbo");
            voitureAModifier.setImmatriculation("10/10");
            voitureAModifier.setConducteur(null);
            dal.update(voitureAModifier);
            Voiture apresModification = dal.getById(voitureAModifier.getId());
            assertEquals("bumbo", apresModification.getNom());
            assertEquals("10/10", apresModification.getImmatriculation());
            assertEquals(null, apresModification.getConducteur());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void delete() {
        try {
            List<Personne> liste = null;
            PersonneDal dal = DalFactory.getPersonneDalFactory();
            liste = dal.getList();
            int tailleAvant = liste.size();
            dal.delete(liste.get(0));
            liste = dal.getList();
            int tailleApres = liste.size();
            int resultat = tailleAvant - tailleApres;
            assertEquals(1, resultat);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}