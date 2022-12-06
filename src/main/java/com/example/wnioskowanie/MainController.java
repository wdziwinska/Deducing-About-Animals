package com.example.wnioskowanie;

import com.company.OWLApiController;
import com.company.Ontology;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.net.URL;
import java.util.ArrayList;
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
    private CheckBox chbWlosy;

    @FXML
    private CheckBox chbLuzki;

    @FXML
    private CheckBox chbPiora;

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

//    private void animalsListViewData() throws OWLOntologyCreationException {
//        animalsListView.getItems().addAll(ontology.setAnimalsList());
//    }
}