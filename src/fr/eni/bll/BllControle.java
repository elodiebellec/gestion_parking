package fr.eni.bll;

import fr.eni.bo.Personne;
import fr.eni.bo.Voiture;

import java.util.ArrayList;
import java.util.List;

public class BllControle {
    public static void main(String[] args) throws BllException {
        List<Personne> personnes = new ArrayList<>();
        Personne henry = new Personne("Dupont", "Henry");
        personnes.add(henry);
        Personne james = new Personne("Bond", "James");
        personnes.add(james);
        Personne anthony = new Personne("Cosson", "Anthony");
        personnes.add(anthony);
        Personne charles = new Personne("DeGaulle", "Charles");
        personnes.add(charles);

        List<Voiture> voitures = new ArrayList<>();
        Voiture boumbo = new Voiture("Boumbo", "123JK12", henry);
        voitures.add(boumbo);
        voitures.add(new Voiture("Aston Martin", "007", james));
        voitures.add(new Voiture("Kangoo", "123AB23", null));
        voitures.add(new Voiture("DS19", "000001", charles));
        Voiture tut = new Voiture("tut", "1235", henry);

        PersonneManager personneManager;
        VoitureManager voitureManager;
        try {
            personneManager = PersonneManager.getInstance();
            voitureManager = VoitureManager.getInstance();

            // Ajout d'une personne
            try {
                for (Personne personne : personnes) {
                    personneManager.addPersonne(personne);
                }
                System.out.println(personneManager.getListPersonne());
            } catch (BllException e) {
                e.printStackTrace();
            }

            // Modification d'une personne
            try {
                (henry).setNom("Nonnon");
                (henry).setPrenom("ouioui");
                personneManager.updatePersonne(henry);
                System.out.println("Personne après modification  : " + henry.toString());
            } catch (BllException e) {
                e.printStackTrace();
            }

            // Ajout d'une voiture
            try {
                for (Voiture voit : voitures) {
                    voitureManager.addVoiture(voit);
                }
                System.out.println(voitureManager.getListVoiture());
            } catch (BllException e) {
                e.printStackTrace();
            }

            // Modification d'une voiture
            try {
                (boumbo).setImmatriculation("123456");
                (boumbo).setNom("Tuttut");
                voitureManager.updateVoiture(boumbo);
                System.out.println("Voiture après modification  : " + boumbo.toString());
            } catch (BllException e) {
                e.printStackTrace();
            }

            // Suppression d'une voiture
            try {
                voitureManager.removeVoiture(boumbo);
                System.out.println(voitureManager.getListVoiture());
            } catch (BllException e) {
                e.printStackTrace();
            }

            // Suppression d'une personne
            try {
                personneManager.removePersonne(henry);
                System.out.println(personneManager.getListPersonne());
            } catch (BllException e) {
                e.printStackTrace();
            }

        } catch (BllException e1) {
            e1.printStackTrace();
        }


    }

}



