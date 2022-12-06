package com.company;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import java.io.File;
import java.util.*;

public abstract class OWLApiController {

    OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
    OWLOntology owlOntology = null;
    Collection<OWLClass> collection;
    OWLDataFactory owlDataFactory;
    File file;
    String prefix="http://www.semanticweb.org/student/ontologies/2022/10/untitled-ontology-8";
    IRI iri;
    OWLReasoner owlReasoner;


    public OWLApiController(){
        iri = IRI.create(prefix+"#");
        owlDataFactory = ontologyManager.getOWLDataFactory();
        file = new File("ontologia_zwierzatek.owl");

    }

    public void readOntologyFile() throws OWLOntologyCreationException {
        owlOntology = ontologyManager.loadOntologyFromOntologyDocument(file);
        //System.out.println("Czytanie ontologi");
        owlReasoner= new Reasoner.ReasonerFactory().createReasoner(owlOntology);
    }

    public void readOntologyData(){
        collection = owlOntology.getClassesInSignature();
        // Klasy
        //collection.stream().map(OWLClass::getIRI).forEach(System.out::println);

        // ObjectProperties wszystkie (polujeNa, posiadaSrodowisko etc)
        Collection<OWLObjectProperty> owlObjectProperties = owlOntology.getObjectPropertiesInSignature();
        //owlObjectProperties.stream().map(OWLObjectProperty::getIRI).forEach(System.out::println);
    }

    // Zwraca jedna klase,na przyklad Drapeznik, Ssak,
    private OWLClass getOntologyClass(String klasa){
        //System.out.println(zwierzeClass);
        return owlDataFactory.getOWLClass(IRI.create(iri+klasa));
    }

    // Zwraca jeden individual na przyklad lew, delfin
    private OWLNamedIndividual getOntologyIndividual(String element){
        return owlDataFactory.getOWLNamedIndividual(IRI.create(iri+element));
    }

    // Zwraca jeden OWLObjectPropety na przyklad polujeNa, posiadaKonczyny
    private OWLObjectProperty getOntologyObjectProperty(String element){
        return owlDataFactory.getOWLObjectProperty(IRI.create(iri+element));
    }

    // Zwraca wszystko co nalezy do danej owl:class na przyklad Ssak, Drapieznik
    public ArrayList<String> getAllIndividualsBelongingToClass(String ontologyClass){
        ArrayList<String> individuals = new ArrayList<String>();

        // Get ontology class
        OWLClass owlClass = getOntologyClass(ontologyClass);

        // Get all individual belonging to that class
        NodeSet<OWLNamedIndividual> instances = owlReasoner.getInstances(owlClass,false);

        // Add them to ArrayList
        for (OWLNamedIndividual instance: instances.getFlattened()){
            //System.out.println(instance.getIRI());

            // Dodanie samych instancji
            individuals.add(instance.getIRI().getFragment());
        }

        return individuals;
    }

    // Zwaraca informacje o jednym konkretnym indiwiduum i jego jednym ObjectProperty, na przyklad lew i polujeNa
    public ArrayList<String> getSpecificObjectPropertyAboutIndividual(String individual, String objectProperty){
        ArrayList<String> values = new ArrayList<String>();

        // Get individual
        OWLNamedIndividual owlIndividual = getOntologyIndividual(individual);

        // Get object property
        OWLObjectProperty owlObjectProperty = getOntologyObjectProperty(objectProperty);

        // Get all values for that property
        NodeSet<OWLNamedIndividual> individualObjectPropertyValues = owlReasoner.getObjectPropertyValues(owlIndividual,owlObjectProperty);

        // Add those values to ArrayList
        for (OWLNamedIndividual instance: individualObjectPropertyValues.getFlattened()){
            values.add(instance.getIRI().getFragment());
        }
        return values;
    }

    public HashMap<String,ArrayList<String>> getAllObjectPropertiesAboutIndividual(String individual){
        HashMap<String, ArrayList<String>> allProperties = new HashMap<String,ArrayList<String>>();

        ArrayList<String> valuesOfOneProperty = new ArrayList<String>();
        String propertyName;
        Properties properties;

        // Get all objectProperties in ontology
        collection = owlOntology.getClassesInSignature();
        Collection<OWLObjectProperty> owlObjectProperties = owlOntology.getObjectPropertiesInSignature();

        // For each existing property
        for (OWLObjectProperty objectProperty: owlObjectProperties){
            // Get the name of the current property
            propertyName=objectProperty.getIRI().getFragment();


            // Get values of that property, for specified individual
            valuesOfOneProperty = getSpecificObjectPropertyAboutIndividual(individual,propertyName);


            ArrayList<String> temp = new ArrayList<String>(valuesOfOneProperty);

            allProperties.put(propertyName,temp);

        }
//        System.out.println("Poza petla: ");
//        System.out.println(allProperties);
        return allProperties;
    }

    // Z internetu, wypisuje property ktore musi miec dana klasa
    public void test(){
        printProperties(ontologyManager,owlOntology,owlReasoner,getOntologyClass("Ssak"));
    }
    private static boolean hasProperty(OWLOntologyManager man, OWLReasoner reasoner,
                                       OWLClass cls, OWLObjectPropertyExpression prop) {

        OWLDataFactory dataFactory = man.getOWLDataFactory();
        OWLClassExpression restriction = dataFactory.getOWLObjectSomeValuesFrom(prop,
                dataFactory.getOWLThing());
        OWLClassExpression complement = dataFactory.getOWLObjectComplementOf(restriction);
        OWLClassExpression intersection = dataFactory.getOWLObjectIntersectionOf(cls,
                complement);
        return !reasoner.isSatisfiable(intersection);
    }

    private static void printProperties(OWLOntologyManager man, OWLOntology ont,
                                        OWLReasoner reasoner, OWLClass cls) {
        if (!ont.containsClassInSignature(cls.getIRI())) {
            throw new RuntimeException("Class not in signature of the ontology");
        }
        System.out.println("Properties of " + cls);
        for (OWLObjectPropertyExpression prop : ont.getObjectPropertiesInSignature()) {
            boolean sat = hasProperty(man, reasoner, cls, prop);
            if (sat) {
                System.out.println("Instances of " + cls
                        + " necessarily have the property " + prop);
            }
        }
    }


    public abstract void initialize();
}
