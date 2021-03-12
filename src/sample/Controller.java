package sample;

import fr.eni.bll.BllException;
import fr.eni.bll.PersonneManager;
import fr.eni.bll.VoitureManager;
import fr.eni.bo.Personne;
import fr.eni.bo.Voiture;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    PersonneManager personneManager = PersonneManager.getInstance();
    VoitureManager voitureManager = VoitureManager.getInstance();
    Personne personneOnMouseEvent = null;
    Personne conducteurOnMouseEvent = null;
    Voiture voitureOnMouseEvent = null;
    Personne nulle = new Personne();

    //TextField pour modifier et supprimer les voitures et conducteurs
    public TextField nomVoiture;
    public TextField PlaqueVoiture;
    public TextField nomPersonne;
    public TextField prenomPersonne;


    //Tableau de personnes
    @FXML
    public TableView <Personne> tablePersonnes;
    @FXML public TableColumn <Personne, String> nomsPersonnes;
    @FXML public TableColumn <Personne, String> prenomsPersonnes;
    public ObservableList<Personne> listPersonnes = FXCollections.observableArrayList(personneManager.getListPersonne());

    //Tableau de voitures
    @FXML
    public TableView <Voiture> tableVoitures;
    @FXML public TableColumn <Voiture, String> nomsVoitures;
    @FXML public TableColumn <Voiture, String> plaquesVoitures;
    @FXML public TableColumn <Voiture, String> identitesConducteurs;
    public ObservableList<Voiture> listeVoitures = FXCollections.observableArrayList(voitureManager.getListVoiture());

    //Combobox avec liste des conducteurs
    @FXML public ComboBox<Personne> comboBoxConducteurs = new ComboBox<>();





    public Controller() throws BllException {
    }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
        nomsPersonnes.setCellValueFactory(new PropertyValueFactory<Personne, String>("nom"));
        prenomsPersonnes.setCellValueFactory(new PropertyValueFactory<Personne, String>("prenom"));
        tablePersonnes.setItems(listPersonnes);

        nomsVoitures.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNom()));
        plaquesVoitures.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getImmatriculation()));
        identitesConducteurs.setCellValueFactory(c -> new SimpleStringProperty(concatConducteurNom(c.getValue())));
        tableVoitures.setItems(listeVoitures);


        comboBoxConducteurs.getItems().addAll(listPersonnes);
        comboBoxConducteurs.setValue(nulle);

        // Set Converter to the ComboBox
            comboBoxConducteurs.setConverter(new Conducteur());
    }

    private String concatConducteurNom(Voiture voiture) {
        Personne personne = voiture.getConducteur();
        if(personne == null)
        {
            return "";
        } else {
        return personne.getNom() + " " + personne.getPrenom();}
    }


    public void ajouterPersonne(ActionEvent actionEvent) throws BllException {
        Personne personneAAjouter = new Personne(nomPersonne.getText(), prenomPersonne.getText());
        personneManager.addPersonne(personneAAjouter);
        tablePersonnes.refresh();
    }



   public Personne getPersonne(javafx.scene.input.MouseEvent mouseEvent) {

        if (mouseEvent.getClickCount() == 1) //Checking click
        {
            //Quand on clique sur la ligne du tableau, on enregistre le conducteur dans l'instance "personne"
            personneOnMouseEvent = tablePersonnes.getSelectionModel().getSelectedItem();
            System.out.println(personneOnMouseEvent);
        }
        return personneOnMouseEvent;

    }

   public Personne getConducteur(ActionEvent actionEvent) {


            //Quand on clique sur la ligne du combox, on enregistre le conducteur dans l'instance "personne"
            conducteurOnMouseEvent = (Personne) comboBoxConducteurs.getSelectionModel().getSelectedItem();
            System.out.println(conducteurOnMouseEvent);


        return conducteurOnMouseEvent;
    }

    public Voiture getVoiture(javafx.scene.input.MouseEvent mouseEvent) {

        if (mouseEvent.getClickCount() == 1) //Checking click
        {
            //Quand on clique sur la ligne du tableau, on enregistre la voiture dans l'instance "voiture"
            voitureOnMouseEvent = tableVoitures.getSelectionModel().getSelectedItem();
            System.out.println(voitureOnMouseEvent);
        }
        return voitureOnMouseEvent;

    }


    public void modifierPersonne(ActionEvent actionEvent) throws BllException {
        Personne personneAModifier = personneOnMouseEvent;
        personneAModifier.setNom(nomPersonne.getText());
        personneAModifier.setPrenom(prenomPersonne.getText());
        personneManager.updatePersonne(personneAModifier);
        tablePersonnes.refresh();
    }

    public void supprimerPersonne(ActionEvent actionEvent) throws BllException {
        Personne personneASupprimer = personneOnMouseEvent;
        personneManager.removePersonne(personneASupprimer);
        tablePersonnes.refresh();
    }

    public void supprimerVoiture(ActionEvent actionEvent) throws BllException {
        Voiture voitureASupprimer = voitureOnMouseEvent;
        voitureManager.removeVoiture(voitureOnMouseEvent);
        tableVoitures.refresh();
    }

    public void ajouterVoiture(ActionEvent actionEvent) throws BllException {
        Voiture voitureAAjouter = new Voiture(nomVoiture.getText(), PlaqueVoiture.getText(), conducteurOnMouseEvent);
        voitureManager.addVoiture(voitureAAjouter);
        tableVoitures.refresh();
    }

    public void modifierVoiture(ActionEvent actionEvent) throws BllException {
      Voiture voitureAModifier = voitureOnMouseEvent;
        voitureAModifier.setNom(nomVoiture.getText());
        voitureAModifier.setImmatriculation(PlaqueVoiture.getText());
        voitureAModifier.setConducteur(conducteurOnMouseEvent);
        voitureManager.updateVoiture(voitureAModifier);
        tableVoitures.refresh();
    }





    }
