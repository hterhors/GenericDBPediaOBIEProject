package de.hterhors.obie.projects.dbpedia.ontology.food.interfaces;

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
import de.hterhors.obie.projects.dbpedia.ontology.food.classes.*;
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

@AssignableSubInterfaces(get={})

@ImplementationClass(get=Food.class)
 public interface IFood
 extends IFoodThing{

/***/
	public IFood addCreatorOfDishPerson(IPerson person);


/***/
	public IFood addIngredientEukaryote(IEukaryote eukaryote);


/***/
	public IFood addOriginPopulatedPlace(IPopulatedPlace populatedPlace);


/***/
	public IFood addVariantFood(IFood food);


/***/
	public IAlias getAlias();


/***/
	public List<IPerson> getCreatorOfDishPersons();


/***/
	public List<IEukaryote> getIngredientEukaryotes();


/***/
	public List<IPopulatedPlace> getOriginPopulatedPlaces();


/***/
	public List<IFood> getVariantFoods();


/***/
	public IFood setAlias(IAlias alias);


/***/
	public IFood setCreatorOfDishPersons(List<IPerson> creatorOfDishPersons);


/***/
	public IFood setIngredientEukaryotes(List<IEukaryote> ingredientEukaryotes);


/***/
	public IFood setOriginPopulatedPlaces(List<IPopulatedPlace> originPopulatedPlaces);


/***/
	public IFood setVariantFoods(List<IFood> variantFoods);


}
