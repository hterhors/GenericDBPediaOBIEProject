package de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces;

import de.hterhors.obie.core.ontology.AbstractIndividual;
import de.hterhors.obie.core.ontology.IndividualFactory;
import de.hterhors.obie.core.ontology.annotations.AssignableSubClasses;
import de.hterhors.obie.core.ontology.annotations.AssignableSubInterfaces;
import de.hterhors.obie.core.ontology.annotations.DatatypeProperty;
import de.hterhors.obie.core.ontology.annotations.DirectInterface;
import de.hterhors.obie.core.ontology.annotations.DirectSiblings;
import de.hterhors.obie.core.ontology.annotations.ImplementationClass;
import de.hterhors.obie.core.ontology.annotations.OntologyModelContent;
import de.hterhors.obie.core.ontology.annotations.RelationTypeCollection;
import de.hterhors.obie.core.ontology.annotations.SuperRootClasses;
import de.hterhors.obie.core.ontology.annotations.TextMention;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.*;
import org.apache.jena.rdf.model.Model;
/**
*
* @author hterhors
*
*
*Nov 6, 2018
*/

@AssignableSubInterfaces(get={IMagazine.class, ICompany.class, IPerson.class, IManga.class, INumberOfVolumes.class, })
 public interface IMangaThing
 extends de.hterhors.obie.core.ontology.interfaces.IOBIEThing{

 public static String RDF_MODEL_NAMESPACE = "http://dbpedia/manga/";

/***/
	public Integer getCharacterOffset();


/***/
	public Integer getCharacterOnset();


/***/
	public String getONTOLOGY_NAME();


/***/
	public Model getRDFModel(String resourceIDPrefix);


/***/
	public String getResourceName();


/***/
	public String getTextMention();


/***/
	public boolean isEmpty();


/***/
	public void setCharacterOnset(Integer onset);


}
