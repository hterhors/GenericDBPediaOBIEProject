package de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces;

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
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
/**
*
* @author hterhors
*
*
*Nov 6, 2018
*/

@ImplementationClass(get=Dam.class)

@AssignableSubInterfaces(get={})
 public interface IDam
 extends IDamThing{

/***/
	public IDam addBuildingStartYear(IBuildingStartYear buildingStartYear);


/***/
	public IDam addCountry(ICountry country);


/***/
	public IDam addLocationPlace(IPlace place);


/***/
	public IDam addOpeningYear(IOpeningYear openingYear);


/***/
	public IDam addRiver(IRiver river);


/***/
	public List<IBuildingStartYear> getBuildingStartYears();


/***/
	public List<ICountry> getCountries();


/***/
	public List<IPlace> getLocationPlaces();


/***/
	public List<IOpeningYear> getOpeningYears();


/***/
	public List<IRiver> getRivers();


/***/
	public IStatus getStatus();


/***/
	public IDam setBuildingStartYears(List<IBuildingStartYear> buildingStartYears);


/***/
	public IDam setCountries(List<ICountry> countries);


/***/
	public IDam setLocationPlaces(List<IPlace> locationPlaces);


/***/
	public IDam setOpeningYears(List<IOpeningYear> openingYears);


/***/
	public IDam setRivers(List<IRiver> rivers);


/***/
	public IDam setStatus(IStatus status);


}
