package Onto2DD;

import java.io.File;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;



import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import jason.asSyntax.ASSyntax;
import jason.asSyntax.Literal;
import jason.asSyntax.Term;


public class OwlApiLiteral {
	private final String FunctorInstance 	       = "instance"; 	      // instance(Concept,Instance,InstanceNameForJason)
	private final String FunctorConcept 	       = "concept"; 	      // concept(Concept,ConceptNameForJason)
	private final String FunctorObjectProperty     = "objectProperty";    // objectProperty(ObjectPropertyName,objectPropertyNameForJason)
	private final String FunctorAnnotationProperty = "annotationProperty";// annotationProperty(AnnotationProperty,annotationPropertyNameForJason)
	private final String FunctorDataProperty       = "dataProperty";      // dataProperty(DataProperty,dataPropertyNameForJason)


	private OwlApiQueryLayer ontoQuery;

	public OwlApiLiteral(OwlApiOntoLayer ontology) {
		this.ontoQuery = new OwlApiQueryLayer(ontology);
	}

	public OwlApiQueryLayer getQuery() {
	    return this.ontoQuery;
	}
    
	
	// based on line 61 QueryLayer (version1):
    public List<Object> getSubClassNames(String subConceptName, boolean onlyDirectSub)  {
		List<Object> subClassNames = new ArrayList<Object>();
		try {
			for (OWLClass ontoClass : this.ontoQuery.getSubClasses(subConceptName, false)) {
	            Literal l = ASSyntax.createLiteral(this.FunctorConcept, ASSyntax.createString(ontoClass.getIRI().getFragment()));
				l.addTerm(ASSyntax.createAtom(getNameForJason(ontoClass.getIRI().getFragment())));
				subClassNames.add(l);
	        }
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
        return subClassNames;
	}
    
	// based on line 61 QueryLayer (version2):
	public List<String> getSubClassList(String subConceptName, boolean onlyDirectSub)  {
		List<String> subClassList = new ArrayList<String>(); //also to get strings of the names of any possible subclass
		try {
			for (OWLClass ontoClass : this.ontoQuery.getSubClasses(subConceptName, false)) {
				subClassList.add(ontoClass.getIRI().getFragment()); // added
	        }
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
        return subClassList;
	}
	
	
	
    public static String removeAccents(String str) {
        str = Normalizer.normalize(str, Normalizer.Form.NFD);
        str = str.replaceAll("[^\\p{ASCII}]", "");
        str = str.replaceAll("-", "_");
        return str;
    }

    public static String getNameForJason(String str) {
        str = removeAccents(str.substring(0,1).toLowerCase() + str.substring(1));
        return str;
    }

    public List<Object> getClassNames() {
		List<Object> classNames = new ArrayList<Object>();
		try {
			for (OWLClass ontoClass : this.ontoQuery.getOntology().getClasses()) {
	            Literal l = ASSyntax.createLiteral(this.FunctorConcept, ASSyntax.createString(ontoClass.getIRI().getFragment()));
				l.addTerm(ASSyntax.createAtom(getNameForJason(ontoClass.getIRI().getFragment())));
				classNames.add(l);
	        }
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
        return classNames;
	}

    public List<Object> getObjectPropertyNames() {
		List<Object> objectProperties = new ArrayList<Object>();
		try {
			for (OWLObjectProperty objectProperty : this.ontoQuery.getOntology().getObjectProperties()) {
				if (objectProperty.isOWLBottomObjectProperty()) continue;
				Literal l = ASSyntax.createLiteral(this.FunctorObjectProperty, ASSyntax.createString(objectProperty.asOWLObjectProperty().getIRI().getFragment()));
				l.addTerm(ASSyntax.createAtom(getNameForJason(objectProperty.asOWLObjectProperty().getIRI().getFragment())));
				objectProperties.add(l);
	        }
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
        return objectProperties;
	}

	public List<Object> getDataPropertyNames() {
		List<Object> dataProperties = new ArrayList<Object>();
		try {
	        for (OWLDataProperty dataProperty : this.ontoQuery.getOntology().getDataProperties()) {
	            if (dataProperty.isOWLBottomObjectProperty()) continue;
	            Literal l = ASSyntax.createLiteral(this.FunctorDataProperty, ASSyntax.createString(dataProperty.getIRI().getFragment()));
				l.addTerm(ASSyntax.createAtom(getNameForJason(dataProperty.getIRI().getFragment())));
				dataProperties.add(l);
	        }
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
        return dataProperties;
	}

	public List<Object> getAnnotationPropertyNames() {
		List<Object> annotationProperties = new ArrayList<Object>();
		try {
			for (OWLAnnotationProperty annotationProperty : this.ontoQuery.getOntology().getAnnotationProperties()) {
				if (annotationProperty.isBottomEntity()) continue;
				Literal l = ASSyntax.createLiteral(this.FunctorAnnotationProperty, ASSyntax.createString(annotationProperty.getIRI().getFragment()));
				l.addTerm(ASSyntax.createAtom(getNameForJason(annotationProperty.getIRI().getFragment())));
				annotationProperties.add(l);
	        }
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
        return annotationProperties;
	}

	public List<Object> getIndividualNames(String conceptName) {
		List<Object> individuals = new ArrayList<Object>();
		try {
			Term concept = ASSyntax.createString(conceptName);

			for(OWLNamedIndividual individual : ontoQuery.getInstances(conceptName)){
				Literal l = ASSyntax.createLiteral(this.FunctorInstance, concept);
				l.addTerm(ASSyntax.createString(individual.getIRI().getFragment()));
				l.addTerm(ASSyntax.createAtom(getNameForJason(individual.getIRI().getFragment())));
				individuals.add(l);
			}
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
		return individuals;
	}
	
	
	//my version of getIndividualNames to have the sentence:
	public List<String> getIndividuals(String conceptName) {
		List<String> individuals = new ArrayList<String>();
		try {
			for(OWLNamedIndividual individual : ontoQuery.getInstances(conceptName)){
				String l = getNameForJason(individual.getIRI().getFragment()).toString();
				individuals.add(l);
			}
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
		return individuals;
	}

	
	//added:
	public List<String> getRanges(String domain, String propertyName) {
		List<String> indNs = new ArrayList<String>();
		try {
			for(OWLNamedIndividual indN : ontoQuery.getObjectPropertyValues(domain,propertyName)){
				String l = getNameForJason(indN.getIRI().getFragment()).toString().replace("_", " ");
				indNs.add(l);
			}
		}
		catch(Exception e) {
			System.out.println("failed to parse: "+e.getMessage());
		}
		return indNs;
	}
	
}
