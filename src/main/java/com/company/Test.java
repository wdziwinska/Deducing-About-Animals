package com.company;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.util.ArrayList;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) throws OWLOntologyCreationException {
        Ontology ontology = new Ontology();
        //ontology.ontologyLists();
        OWLApiController owl = new OWLApiController();
        owl.readOntologyFile();

        //System.out.println("Gatunki zwierzat:" + owl.getListOfSubclasses("Gatunek"));
        //System.out.println(owl.getSpecificObjectPropertyAboutIndividual("jelen","posiadaWytworySkory"));
        ArrayList<String> criteria=new ArrayList<String>();
        criteria.add("nogi");
        criteria.add("skrzydla");
        criteria.add("piora");
        System.out.println(owl.getIndividualsByCriteria(criteria));
    }
}
