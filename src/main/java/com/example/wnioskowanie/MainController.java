package com.example.wnioskowanie;

import com.company.Ontology;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Label infoAboutAnimals;

    @FXML
    private ListView animalsListView;

    @FXML
    private ChoiceBox choiceBoxGatunek;

    @FXML
    private ChoiceBox choiceBoxRodzajPozywienia;

    @FXML
    private ListView animalsBySpeciesListView;

    @FXML
    private ListView animalsByCriteriaListView;

    @FXML
    private ListView zernoscListView;

    @FXML
    private ListView srodowiskoListView;

    @FXML
    private ListView polujeNaListView;

    @FXML
    private ListView polowanyPrzezListView;

    @FXML
    private Label gatunekLabel;

    @FXML
    private CheckBox chbWlosy;

    @FXML
    private CheckBox chbLuski;

    @FXML
    private CheckBox chbPiora;

    @FXML
    private CheckBox chbNogi;

    @FXML
    private CheckBox chbSkrzydla;

    @FXML
    private CheckBox chbPletwy;

    @FXML
    private CheckBox chbLad;

    @FXML
    private CheckBox chbJezioro;

    @FXML
    private CheckBox chbRzeka;

    @FXML
    private CheckBox chbMorze;

    @FXML
    private CheckBox chbOcean;


    @FXML
    private Button btnWyszukajChoiceBox;

    @FXML
    private Button btnWyszukajCheckBox;

    Ontology ontology = new Ontology();

   // ArrayList<String> animalsList = ontology.getAnimals();

    String[] anim = {"pies, kot, kaczka"};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1){
        try {
            ontology.ontologyLists();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        animalsListView.getItems().addAll(ontology.getAnimals());
        choiceBoxGatunek.getItems().addAll(ontology.getSpecies());
        choiceBoxRodzajPozywienia.getItems().addAll(ontology.getFoodTypes());


//        System.out.println("Animals list view from initialize");
//        System.out.println(ontology.getAnimals());
//        animalsListView.getItems().addAll(ontology.getAnimalsList());
    }

    public void onBtnWyszukajChoiceBoxClick(ActionEvent actionEvent) {
        animalsBySpeciesListView.getItems().clear();

        String chosenSpecies = (String) choiceBoxGatunek.getSelectionModel().getSelectedItem();
        ArrayList<String> animalsBySpecies = ontology.getAllIndividualsBelongingToClass(chosenSpecies);

        String chosenFoodType = (String) choiceBoxRodzajPozywienia.getSelectionModel().getSelectedItem();
        ArrayList<String> animalsByFoodType = new ArrayList<String>();

        if (Objects.equals(chosenFoodType, "miesozernosc")){
            animalsByFoodType = ontology.getAllIndividualsBelongingToClass("Miesozerca");
        } else if (Objects.equals(chosenFoodType, "roslinozernosc")){
            animalsByFoodType = ontology.getAllIndividualsBelongingToClass("Roslinozerca");
        } else {
            animalsByFoodType = ontology.getAllIndividualsBelongingToClass("Wszystkozerca");
        }

        ArrayList<String> animals = new ArrayList<String>(animalsBySpecies);
        animals.retainAll(animalsByFoodType);

        animalsBySpeciesListView.getItems().addAll(animals);

    }

    public void onbtnWyszukajCheckBoxClick(ActionEvent actionEvent) {
        //System.out.println("Test2");
        animalsByCriteriaListView.getItems().clear();
        ArrayList<String> criteria = new ArrayList<String>();
        if (chbWlosy.isSelected()){
            criteria.add("wlosy");
        }
        if (chbLuski.isSelected()){
            criteria.add("luski");
        }
        if (chbPiora.isSelected()){
            criteria.add("piora");
        }
        if (chbNogi.isSelected()){
            criteria.add("nogi");
        }
        if (chbSkrzydla.isSelected()){
            criteria.add("skrzydla");
        }
        if (chbPletwy.isSelected()){
            criteria.add("pletwy");
        }
        if (chbLad.isSelected()){
            criteria.add("lad");
        }
        if (chbJezioro.isSelected()){
            criteria.add("jezioro");
        }
        if (chbRzeka.isSelected()){
            criteria.add("rzeka");
        }
        if (chbMorze.isSelected()){
            criteria.add("morze");
        }
        if (chbOcean.isSelected()){
            criteria.add("ocean");
        }


        ArrayList<String> animals = ontology.getAnimalsByCriteria(criteria);
        animalsByCriteriaListView.getItems().addAll(animals);
    }

    public void onAnimalsListViewClicked(){
        zernoscListView.getItems().clear();
        srodowiskoListView.getItems().clear();
        polujeNaListView.getItems().clear();
        polowanyPrzezListView.getItems().clear();

        String chosenAnimal = (String) animalsListView.getSelectionModel().getSelectedItem();
        HashMap<String,ArrayList<String>> infoAboutChosenAnimal = ontology.getAllObjectPropertiesAboutIndividual(chosenAnimal);
        gatunekLabel.setText("Gatunek: " + ontology.getSpeciesOfIndividual(chosenAnimal));
        if (infoAboutChosenAnimal.get("posiadaZernosc")!=null){
            zernoscListView.getItems().addAll(infoAboutChosenAnimal.get("posiadaZernosc"));
        }
        if (infoAboutChosenAnimal.get("posiadaSrodowisko")!=null){
            srodowiskoListView.getItems().addAll(infoAboutChosenAnimal.get("posiadaSrodowisko"));
        }
        if (infoAboutChosenAnimal.get("polujeNa")!=null){
            polujeNaListView.getItems().addAll(infoAboutChosenAnimal.get("polujeNa"));
        }
        if (infoAboutChosenAnimal.get("polowanyPrzez")!=null){
            polowanyPrzezListView.getItems().addAll(infoAboutChosenAnimal.get("polowanyPrzez"));
        }






    }

//    private void animalsListViewData() throws OWLOntologyCreationException {
//        animalsListView.getItems().addAll(ontology.setAnimalsList());
//    }




}