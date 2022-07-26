package Onto2DD;

import java.util.logging.Logger;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class OwlApiExtract {
	public static OwlApiOntoLayer onto;
	public static OwlApiLiteral queryEngine;

	public static void main() {
		confirmReadOnto();

		//for Class: all the classes of the Ontology
		//if (isSubConcept(Class , "Intents")) {
			//gather
		//}
		//now make a jsons out of it:::::::::::::::::::::::::::::::::: BUT ONE LEVEL ONLY? no. I want direct subclasses
		String[] Classes=new String[] {"Devices"};
		if (isSubConcept(Classes[0], "Intents")){
			FileGenerator filegenerator= new FileGenerator();
			filegenerator.makejsons(Classes);
		}


		//String[] Classes=new String[] {"I_feel_so_gloomy_today"};
		if (isSubConcept(Classes[0], "TrainingPhrases")){ //or something else
			FileGenerator filegenerator= new FileGenerator();
			filegenerator.makejsons(Classes);
		}

	}

	public static void confirmReadOnto () {
			try {
				onto = new OwlApiOntoLayer("/Users/zeinabnamakizadehesfahani/Documents/Thesis/MiroOnto/MiroOnto.owl");
				OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
				onto.setReasoner(reasonerFactory.createReasoner(onto.getOntology()));


				System.out.println("Ontology ready!");
			} catch (OWLOntologyCreationException e) {
				System.out.println("An error occurred when loading the ontology. Error: "+e.getMessage());
			} catch (Exception e) {
				System.out.println("An unexpected error occurred: "+e.getMessage());
			}

		}

	/**
	 * @param subConceptName Name of the supposed sub-concept.
	 * @param superConceptName Name of the concept to be tested as the super-concept.
	 * @return true if <code>subConceptName</code> is a sub-concept of <code>sueperConceptName</code>, false
	 * otherwise.
	 */
	@OPERATION
	static boolean isSubConcept(String subConceptName, String superConceptName) {
		Boolean isSubConcept;
		queryEngine = new  OwlApiLiteral(onto);
		isSubConcept=queryEngine.getQuery().isSubConceptOf(subConceptName, superConceptName);
		System.out.println(isSubConcept.toString()+"11111111111");
		return isSubConcept;

	}
}
