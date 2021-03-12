package sample;

import fr.eni.bo.Personne;
import fr.eni.bo.Voiture;
import javafx.util.StringConverter;

public class Conducteur extends StringConverter<Personne> {
    // Methode pour convertir l'objet Personne en String
    @Override
    public String toString(Personne personne) {
        return personne == null? null : personne.getNom() + ", " + personne.getPrenom();
    }
    // Methode pour convertir une String en objet Personne
    @Override
    public Personne fromString(String string) {
        Personne personne = null;

        if (string == null)
        {
            return personne;
        }

        int commaIndex = string.indexOf(",");

        if (commaIndex == -1)
        {
            personne = new Personne(string, null);
        }
        else
        {
            String nom = string.substring(commaIndex + 2);
            String prenom = string.substring(0, commaIndex);
            personne = new Personne(nom, prenom);
        }

        return personne;
    }

}
