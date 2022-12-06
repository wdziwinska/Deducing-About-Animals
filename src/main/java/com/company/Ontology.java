package com.company;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.util.ArrayList;

public class Ontology extends OWLApiController{

    public ArrayList<String> animals;
    private ArrayList<String> mammals;
    public ArrayList<String> species;
    public ArrayList<String> foodTypes;


    public ArrayList<String> getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(ArrayList<String> foodTypes) {
        this.foodTypes = foodTypes;
    }

    public ArrayList<String> getAnimals() {
        return animals;
    }

    public void setAnimals(ArrayList<String> animals) {
        this.animals = animals;
    }

    public ArrayList<String> getSpecies() {
        return species;
    }

    public void setSpecies(ArrayList<String> species) {
        this.species = species;
    }

    public void ontologyLists() throws OWLOntologyCreationException {

        readOntologyFile();

        ArrayList<String> animalsList = getAllIndividualsBelongingToClass("Zwierze");
        setAnimals(animalsList);

        ArrayList<String> speciesList = getListOfSubclasses("Gatunek");
        setSpecies(speciesList);

        ArrayList<String> foodTypesList = getAllIndividualsBelongingToClass("Zernosc");
        setFoodTypes(foodTypesList);


//        System.out.println("Lista zwierzat: ");
//        for (String s : animalsList){
//            System.out.println(s);
//        }

//        System.out.println("Lista ssakow: ");
//        mammals = getAllIndividualsBelongingToClass("Ssak");
//        for (String s : mammals){
//            System.out.println(s);
//        }
//
//        ArrayList<String> preyOfLion = getSpecificObjectPropertyAboutIndividual("lew","polujeNa");
//
//        System.out.println("\nLew poluje na: ");
//        for (String s : preyOfLion){
//            System.out.println(s);
//        }
//
//        ArrayList<String> hippoEnvironment = getSpecificObjectPropertyAboutIndividual("hipopotam","posiadaSrodowisko");
//        System.out.println("\nSrodowiska hipopotama: ");
//        for (String s : hippoEnvironment){
//            System.out.println(s);
//        }
//
//        System.out.println("\nWszystkie objectProprty dla lwa: ");
//        getAllObjectPropertiesAboutIndividual("lew");
    }
}
