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
import de.hterhors.obie.core.ontology.interfaces.IDatatype;
import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import de.hterhors.obie.projects.dbpedia.ontology.manga.classes.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
/**
*
* @author hterhors
*
*
*Dec 12, 2018
*/

@ImplementationClass(get=Manga.class)

@AssignableSubInterfaces(get={})
 public interface IManga
 extends IMangaThing{

/***/
	public IManga addAuthorPerson(IPerson person);


/***/
	public IManga addIllustratorPerson(IPerson person);


/***/
	public IManga addMagazine(IMagazine magazine);


/***/
	public IManga addNumberOfVolumes(INumberOfVolumes numberOfVolumes);


/***/
	public IManga addPublisherCompany(ICompany company);


/***/
	public List<IPerson> getAuthorPersons();


/***/
	public List<IPerson> getIllustratorPersons();


/***/
	public List<IMagazine> getMagazines();


/***/
	public List<INumberOfVolumes> getNumberOfVolumes();


/***/
	public List<ICompany> getPublisherCompanies();


/***/
	public IManga setAuthorPersons(List<IPerson> authorPersons);


/***/
	public IManga setIllustratorPersons(List<IPerson> illustratorPersons);


/***/
	public IManga setMagazines(List<IMagazine> magazines);


/***/
	public IManga setNumberOfVolumes(List<INumberOfVolumes> numberOfVolumes);


/***/
	public IManga setPublisherCompanies(List<ICompany> publisherCompanies);


}
