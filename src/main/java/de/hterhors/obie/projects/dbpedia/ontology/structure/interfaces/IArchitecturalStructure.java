package de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces;

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
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
/**
*
* @author hterhors
*
*
*Nov 27, 2018
*/

@ImplementationClass(get=ArchitecturalStructure.class)

@AssignableSubInterfaces(get={})
 public interface IArchitecturalStructure
 extends IArchitecturalStructureThing{

/***/
	public IArchitecturalStructure addArchitect(IArchitect architect);


/***/
	public IArchitecturalStructure addArchitecturalStyle(IArchitecturalStyle architecturalStyle);


/***/
	public IArchitecturalStructure addLocationPlace(IPlace place);


/***/
	public IArchitecturalStructure addYearOfConstruction(IYearOfConstruction yearOfConstruction);


/***/
	public List<IArchitect> getArchitects();


/***/
	public List<IArchitecturalStyle> getArchitecturalStyles();


/***/
	public List<IPlace> getLocationPlaces();


/***/
	public IOpeningYear getOpeningYear();


/***/
	public List<IYearOfConstruction> getYearOfConstructions();


/***/
	public IArchitecturalStructure setArchitects(List<IArchitect> architects);


/***/
	public IArchitecturalStructure setArchitecturalStyles(List<IArchitecturalStyle> architecturalStyles);


/***/
	public IArchitecturalStructure setLocationPlaces(List<IPlace> locationPlaces);


/***/
	public IArchitecturalStructure setOpeningYear(IOpeningYear openingYear);


/***/
	public IArchitecturalStructure setYearOfConstructions(List<IYearOfConstruction> yearOfConstructions);


}
