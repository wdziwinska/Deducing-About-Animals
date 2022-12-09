package com.company;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ontology extends OWLApiController{

    private final List<String> animals = new CopyOnWriteArrayList<>();
    private ArrayList<String> mammals;
    public ArrayList<String> species;
    public ArrayList<String> foodTypes;


    public ArrayList<String> getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(ArrayList<String> foodTypes) {
        this.foodTypes = foodTypes;
    }

    public List<String> getAnimals() {
        return animals;
    }

    public void clearAnimalList() {
        animals.clear();
    }

    public void addAnimalToList(String animal) {
        animals.add(animal);
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList<String> species) {
        this.species = species;
    }

    public void ontologyLists() throws OWLOntologyCreationException {

        readOntologyFile();

        ArrayList<String> animalsList = getAllIndividualsBelongingToClass("Zwierze");
        animals.addAll(animalsList);

        ArrayList<String> speciesList = getListOfSubclasses("Gatunek");
        setSpecies(speciesList);

        ArrayList<String> foodTypesList = getAllIndividualsBelongingToClass("Zernosc");
        setFoodTypes(foodTypesList);
    }
}
