package Onto2DD;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import cartago.OPERATION;
import cartago.OpFeedbackParam;
import cartago.manual.syntax.Literal;

public class OwlApiExtract {
	public static OwlApiOntoLayer onto;
	//public static OwlApiQueryLayer ontoQuery;
	public static OwlApiLiteral queryEngine;



	public static String confirmReadOnto (String fileAddress) {
		String msg;
		try {
			onto = new OwlApiOntoLayer(fileAddress);
			OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
			onto.setReasoner(reasonerFactory.createReasoner(onto.getOntology()));
			msg = "And Ontology Successfully read!";
			//System.out.println("Ontology ready!");
		} catch (OWLOntologyCreationException e) {
			//System.out.println("An error occurred when loading the ontology. Error: "+e.getMessage());
			msg = "But an error occurred when loading the ontology. Error: "+e.getMessage();
		} catch (Exception e) {
			msg = "But an unexpected error occurred: " +e.getMessage();
			//System.out.println("An unexpected error occurred: "+e.getMessage());
		}
		return msg;

	}
	
//	//unused, but can be used instead of character checking method
//	static String[] getClassNames() {
//		List<Object> fetchClasses;
//		queryEngine = new  OwlApiLiteral(onto);
//		fetchClasses=queryEngine.getClassNames();
//		String[] classNames = fetchClasses.toArray(new String[fetchClasses.size()]);
//		System.out.println("classes are:"+classNames);
//		return classNames;
//
//	}
	
	
	static List<String> getEntityValues(String ConceptName) {
		List<String> EntLst = new ArrayList<String>();
		for (String Entity : queryEngine.getIndividuals(ConceptName))
		{
			EntLst.add(Entity);
		}
		return EntLst;
	}
		
	
	static List<String> getRanges (String domain, String propertyName) {
		List<String> individuals = new ArrayList<String>();
		for(OWLNamedIndividual individual : queryEngine.getQuery().getObjectPropertyValues(domain, propertyName)) {
			individuals.add(individual.getIRI().toString().substring(individual.getIRI().toString().indexOf('#')+1));
		}
		return individuals;
	}
	//given a certain intent(class), hands out the list of its property values of a certain property
	static List<String> getRangeLst(String domainClass, String objectProperty){
		queryEngine = new  OwlApiLiteral(onto);
		List<String> allResult = new ArrayList<String>();
		for (String domain : queryEngine.getIndividuals(domainClass))
		{
			List<String> ranges = queryEngine.getRanges(domain,objectProperty);
			allResult.addAll(ranges);
		}
		return allResult;
	}
	
	
	//given a certain training phrase(individual), hands out the list of entities
	static Set<String> getRangeTrainingLst(String domainName, String objectProperty){
		queryEngine = new OwlApiLiteral(onto);
		String TPhU = domainName.substring(0,1).toUpperCase() + domainName.substring(1);
		TPhU = TPhU.replaceAll(" ","_");
		Set<String> Entities = new HashSet<>(getRanges(TPhU,objectProperty));
		return Entities;
	}
	
	
	//unused:
	static List<String> getTrainingPhrases() {
		queryEngine = new  OwlApiLiteral(onto);
		List<String> trainingPhrases = queryEngine.getIndividuals("TrainingPhrases");
		return trainingPhrases;
	}
	

//	 //unused
//	 public static void blacklistmaker() { //from net https://stackoverflow.com/questions/46502153/super-classes-of-one-classe-owl-api
//	       for (OWLClass clss : onto.ontology.getClassesInSignature()) 
//	       {
//	    	   if(onto.reasoner.getSuperClasses(clss, false).getFlattened().size()>2) // if the class has more than one mother class 
//	           {
//	    		   System.out.println(" \n ---------------- : \n");
//	    		   System.out.println("\n  class "+clss.getIRI().getFragment()+" has more than one mother classes : \n");
//	    		   for(OWLClass parent: onto.reasoner.getSuperClasses(clss, true).getFlattened())
//	    			   System.out.println(parent.getIRI().getFragment()); //the classes with whom should not make intent
//	           }
//	       }
//	 }
	 
	
	static boolean isSubConcept(String subConceptName, String superConceptName) {
		Boolean isSubConcept;
		queryEngine = new  OwlApiLiteral(onto);
		isSubConcept=queryEngine.getQuery().isSubConceptOf(subConceptName, superConceptName);
		//System.out.println(subConceptName +" is a SubConcept of " + superConceptName +"?" +isSubConcept.toString());
		return isSubConcept;

	}
	
	
	static boolean isIntent(String ConceptName) {
		boolean isIntent = false;
		List<String> subClasses;
		queryEngine = new  OwlApiLiteral(onto);
		subClasses = queryEngine.getSubClassList(ConceptName, false);
		boolean IntentSub = isSubConcept(ConceptName, "Intents");
		boolean NoSub = subClasses.toArray().length <= 1;
		return IntentSub && NoSub;
	}
	
	
	static boolean isEntity(String ConceptName) {
		boolean isIntent = false;
		List<String> subClasses;
		queryEngine = new  OwlApiLiteral(onto);
		subClasses = queryEngine.getSubClassList(ConceptName, false);
		boolean EntitySub = isSubConcept(ConceptName, "Entities");
		boolean NoSub = subClasses.toArray().length <= 1; //to be developed for synonyms of an entity in the future
		return EntitySub && NoSub;
	}
	
	
	static boolean isIndividualOf(String Individual, String ConceptName)
	{
		queryEngine = new  OwlApiLiteral(onto);
		boolean IsInd = queryEngine.getQuery().isInstanceOf(Individual, ConceptName);
		return IsInd;
	}
	
}
