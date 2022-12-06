package com.example.wnioskowanie;

import com.company.Ontology;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.IOException;
import java.util.ArrayList;

public class AnimalsApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AnimalsApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wnioskownie");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws OWLOntologyCreationException {
        Ontology ontology = new Ontology();
        ontology.ontologyLists();
        launch();
    }
}