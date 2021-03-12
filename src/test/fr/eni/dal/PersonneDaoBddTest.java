package test.fr.eni.dal;

import fr.eni.bo.Personne;
import fr.eni.dal.DalFactory;
import fr.eni.dal.PersonneDal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonneDaoBddTest {

    @Test
    void insert() {
        try {
            Personne personne = new Personne();
            List<Personne> liste = null;
            personne.setNom("Cosson");
            personne.setPrenom("Anthony");
            PersonneDal dal = DalFactory.getPersonneDalFactory();
            liste = dal.getList();
            int avant = liste.size();
            dal.insert(personne);
            liste = dal.getList();
            int apres = liste.size();
            int resulat = apres - avant;
            assertEquals(1, resulat);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void selectById() {
        try {
            PersonneDal dal = DalFactory.getPersonneDalFactory();
            List<Personne> liste = dal.getList();
            if (liste.size() > 0) {
                Personne personne = liste.get(0);
                Personne personneATester = dal.getById(personne.getId());
                assertEquals(personne.getId(), personneATester.getId());
                assertTrue(personne.getNom().equals(personneATester.getNom()));
                assertEquals(personne.getPrenom(), personneATester.getPrenom());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    void selectAll() {
        try {
            Personne personne = new Personne();
            personne.setNom("Tare");
            personne.setPrenom("Gui");
            PersonneDal dal = DalFactory.getPersonneDalFactory();
            dal.insert(personne);
            List<Personne> liste = dal.getList();
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
            List<Personne> liste = null;
            PersonneDal dal = DalFactory.getPersonneDalFactory();
            liste = dal.getList();
            Personne personneAModifer = liste.get(0);
            personneAModifer.setPrenom("WOLOLO");
            personneAModifer.setNom("ZOLOLO");
            dal.update(personneAModifer);
            Personne apresModification = dal.getById(personneAModifer.getId());
            assertEquals("WOLOLO", apresModification.getPrenom());
            assertEquals("ZOLOLO", apresModification.getNom());
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