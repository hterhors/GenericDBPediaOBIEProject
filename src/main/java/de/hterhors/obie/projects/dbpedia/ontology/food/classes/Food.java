package de.hterhors.obie.projects.dbpedia.ontology.food.classes;

import java.lang.NoSuchMethodException;
import de.hterhors.obie.core.ontology.interfaces.IDatatype;
import de.hterhors.obie.core.ontology.annotations.SuperRootClasses;
import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import java.util.HashMap;
import de.hterhors.obie.core.ontology.annotations.OntologyModelContent;
import java.util.ArrayList;
import org.apache.jena.rdf.model.Model;
import de.hterhors.obie.core.ontology.annotations.AssignableSubInterfaces;
import de.hterhors.obie.core.ontology.annotations.ImplementationClass;
import org.apache.jena.rdf.model.Resource;
import java.util.Map;
import java.lang.InstantiationException;
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.*;
import java.lang.SecurityException;
import de.hterhors.obie.core.ontology.InvestigationRestriction;
import de.hterhors.obie.core.ontology.annotations.DirectSiblings;
import java.lang.IllegalAccessException;
import de.hterhors.obie.core.ontology.annotations.AssignableSubClasses;
import de.hterhors.obie.core.ontology.IndividualFactory;
import de.hterhors.obie.core.ontology.annotations.DirectInterface;
import de.hterhors.obie.core.ontology.annotations.RelationTypeCollection;
import de.hterhors.obie.core.ontology.annotations.DatatypeProperty;
import java.lang.IllegalArgumentException;
import de.hterhors.obie.core.ontology.annotations.TextMention;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import org.apache.jena.rdf.model.ModelFactory;
import de.hterhors.obie.core.ontology.AbstractIndividual;

/**
*
* @author hterhors
*
*
*Dec 12, 2018
*/

@DirectSiblings(get={})

@DirectInterface(get=IFood.class)

@AssignableSubClasses(get={})

@SuperRootClasses(get={Food.class, })
 public class Food implements IFood{

final public static IndividualFactory<FoodIndividual> individualFactory = new IndividualFactory<>();
final public static Class<? extends AbstractIndividual> individualClassType = FoodIndividual.class;
static class FoodIndividual extends AbstractIndividual {

	private static final long serialVersionUID = 1L;		public FoodIndividual(String namespace, String name) {
			super(namespace, name);
		}

		@Override
		public String toString() {
			return "FoodIndividual [name=" + name + ", nameSpace=" + nameSpace + "]";
		}

	}
	public IndividualFactory<FoodIndividual> getIndividualFactory() {
		return individualFactory;
	}

	public final FoodIndividual individual;
	@Override
	public AbstractIndividual getIndividual() {
		return individual;
	}
	@Override
	public InvestigationRestriction getInvestigationRestriction() {
		return investigationRestriction;
	}
	@Override
	public Food setInvestigationRestriction(InvestigationRestriction investigationRestriction ) {
		this.investigationRestriction = investigationRestriction;
 return this;	}public InvestigationRestriction investigationRestriction;	final static public String ONTOLOGY_NAME = "http://dbpedia.org/ontology/Food";
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/alias")
@DatatypeProperty
private IAlias alias;
	private Integer characterOffset;
	private Integer characterOnset;
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/creatorOfDish")
private List<IPerson> creatorOfDishPersons = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/ingredient")
private List<IEukaryote> ingredientEukaryotes = new ArrayList<>();
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/origin")
@RelationTypeCollection
private List<IPopulatedPlace> originPopulatedPlaces = new ArrayList<>();
	final static private Map<IOBIEThing, String> resourceFactory = new HashMap<>();
	final static private long serialVersionUID = 1L;
	@TextMention
final private String textMention;
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/hasVariant")
private List<IFood> variantFoods = new ArrayList<>();


	public Food(String individualURI, InvestigationRestriction investigationRestriction, String textMention){
this.individual = 
				Food.individualFactory.getIndividualByURI(individualURI);
this.investigationRestriction = investigationRestriction==null?InvestigationRestriction.noRestrictionInstance:investigationRestriction;
this.textMention = textMention;
}
	public Food(Food food)throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{
this.individual = food.individual;
this.investigationRestriction = food.investigationRestriction;
if(food.getAlias()!=null)this.alias = new Alias((Alias)food.getAlias());
this.characterOffset = food.getCharacterOffset();
this.characterOnset = food.getCharacterOnset();
for (int j = 0; j < food.getCreatorOfDishPersons().size(); j++) {if (food.getCreatorOfDishPersons().get(j) != null) {creatorOfDishPersons.add((IPerson) IOBIEThing.getCloneConstructor(food.getCreatorOfDishPersons().get(j).getClass()).newInstance(food.getCreatorOfDishPersons().get(j)));} else {creatorOfDishPersons.add(null);}}
for (int j = 0; j < food.getIngredientEukaryotes().size(); j++) {if (food.getIngredientEukaryotes().get(j) != null) {ingredientEukaryotes.add((IEukaryote) IOBIEThing.getCloneConstructor(food.getIngredientEukaryotes().get(j).getClass()).newInstance(food.getIngredientEukaryotes().get(j)));} else {ingredientEukaryotes.add(null);}}
for (int j = 0; j < food.getOriginPopulatedPlaces().size(); j++) {if (food.getOriginPopulatedPlaces().get(j) != null) {originPopulatedPlaces.add((IPopulatedPlace) IOBIEThing.getCloneConstructor(food.getOriginPopulatedPlaces().get(j).getClass()).newInstance(food.getOriginPopulatedPlaces().get(j)));} else {originPopulatedPlaces.add(null);}}
this.textMention = food.getTextMention();
for (int j = 0; j < food.getVariantFoods().size(); j++) {if (food.getVariantFoods().get(j) != null) {variantFoods.add((IFood) IOBIEThing.getCloneConstructor(food.getVariantFoods().get(j).getClass()).newInstance(food.getVariantFoods().get(j)));} else {variantFoods.add(null);}}
}
	public Food(){
this.individual = null;
this.investigationRestriction = InvestigationRestriction.noRestrictionInstance;
this.textMention = null;
}


	/***/
@Override
	public Food addCreatorOfDishPerson(IPerson person){
		this.creatorOfDishPersons.add(person);
return this;}
	/***/
@Override
	public Food addIngredientEukaryote(IEukaryote eukaryote){
		this.ingredientEukaryotes.add(eukaryote);
return this;}
	/***/
@Override
	public Food addOriginPopulatedPlace(IPopulatedPlace populatedPlace){
		this.originPopulatedPlaces.add(populatedPlace);
return this;}
	/***/
@Override
	public Food addVariantFood(IFood food){
		this.variantFoods.add(food);
return this;}
	/***/
@Override
	public boolean equals(Object obj){
		if (this == obj)
return true;
if (obj == null)
return false;
if (getClass() != obj.getClass())
return false;
Food other = (Food) obj;
if (individual == null) {
if (other.individual!= null)
return false;
} else if (!individual.equals(other.individual))
return false;
if (investigationRestriction == null) {
if (other.investigationRestriction!= null)
return false;
} else if (!investigationRestriction.equals(other.investigationRestriction))
return false;
if (textMention == null) {
if (other.textMention!= null)
return false;
} else if (!textMention.equals(other.textMention))
return false;
if (originPopulatedPlaces == null) {
if (other.originPopulatedPlaces!= null)
return false;
} else if (!originPopulatedPlaces.equals(other.originPopulatedPlaces))
return false;
if (creatorOfDishPersons == null) {
if (other.creatorOfDishPersons!= null)
return false;
} else if (!creatorOfDishPersons.equals(other.creatorOfDishPersons))
return false;
if (characterOnset == null) {
if (other.characterOnset!= null)
return false;
} else if (!characterOnset.equals(other.characterOnset))
return false;
if (alias == null) {
if (other.alias!= null)
return false;
} else if (!alias.equals(other.alias))
return false;
if (ingredientEukaryotes == null) {
if (other.ingredientEukaryotes!= null)
return false;
} else if (!ingredientEukaryotes.equals(other.ingredientEukaryotes))
return false;
if (variantFoods == null) {
if (other.variantFoods!= null)
return false;
} else if (!variantFoods.equals(other.variantFoods))
return false;
if (characterOffset == null) {
if (other.characterOffset!= null)
return false;
} else if (!characterOffset.equals(other.characterOffset))
return false;
return true;
}
	/***/
@Override
	public IAlias getAlias(){
		return alias;}
	/***/
@Override
	public Integer getCharacterOffset(){
		return characterOffset;}
	/***/
@Override
	public Integer getCharacterOnset(){
		return characterOnset;}
	/***/
@Override
	public List<IPerson> getCreatorOfDishPersons(){
		return creatorOfDishPersons;}
	/***/
@Override
	public List<IEukaryote> getIngredientEukaryotes(){
		return ingredientEukaryotes;}
	/***/
@Override
	public String getONTOLOGY_NAME(){
		return ONTOLOGY_NAME;}
	/***/
@Override
	public List<IPopulatedPlace> getOriginPopulatedPlaces(){
		return originPopulatedPlaces;}
	/***/
@Override
	public Model getRDFModel(String resourceIDPrefix){
		Model model = ModelFactory.createDefaultModel();
Resource group = model.createResource(getResourceName());
model.add(group, model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"),model.createResource(ONTOLOGY_NAME));
return model;
}
	/***/
@Override
	public String getResourceName(){
		if (resourceFactory.containsKey(this)) {
return IFoodThing.RDF_MODEL_NAMESPACE + resourceFactory.get(this);
} else {
final String resourceName = getClass().getSimpleName() + "_" + resourceFactory.size();
resourceFactory.put(this, resourceName);
return IFoodThing.RDF_MODEL_NAMESPACE + resourceName;}
}
	/***/
@Override
	public String getTextMention(){
		return textMention;}
	/***/
@Override
	public List<IFood> getVariantFoods(){
		return variantFoods;}
	/***/
@Override
	public int hashCode(){
		final int prime = 31;
int result = 1;
result = prime * result + ((this.individual == null) ? 0 : this.individual.hashCode());
result = prime * result + ((this.investigationRestriction == null) ? 0 : this.investigationRestriction.hashCode());
result = prime * result + ((this.textMention == null) ? 0 : this.textMention.hashCode());
result = prime * result + ((this.originPopulatedPlaces == null) ? 0 : this.originPopulatedPlaces.hashCode());
result = prime * result + ((this.creatorOfDishPersons == null) ? 0 : this.creatorOfDishPersons.hashCode());
result = prime * result + ((this.characterOnset == null) ? 0 : this.characterOnset.hashCode());
result = prime * result + ((this.alias == null) ? 0 : this.alias.hashCode());
result = prime * result + ((this.ingredientEukaryotes == null) ? 0 : this.ingredientEukaryotes.hashCode());
result = prime * result + ((this.variantFoods == null) ? 0 : this.variantFoods.hashCode());
result = prime * result + ((this.characterOffset == null) ? 0 : this.characterOffset.hashCode());
return result;}
	/***/
@Override
	public boolean isEmpty(){
		boolean isEmpty = true;
return false;}
	/***/
@Override
	public Food setAlias(IAlias alias){
		this.alias = alias;
return this;}
	/***/
@Override
	public void setCharacterOnset(Integer onset){
		this.characterOnset = onset;
 this.characterOffset = onset + textMention.length();}
	/***/
@Override
	public Food setCreatorOfDishPersons(List<IPerson> persons){
		if(persons==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.creatorOfDishPersons = persons;
return this;}
	/***/
@Override
	public Food setIngredientEukaryotes(List<IEukaryote> eukaryotes){
		if(eukaryotes==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.ingredientEukaryotes = eukaryotes;
return this;}
	/***/
@Override
	public Food setOriginPopulatedPlaces(List<IPopulatedPlace> populatedPlaces){
		if(populatedPlaces==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.originPopulatedPlaces = populatedPlaces;
return this;}
	/***/
@Override
	public Food setVariantFoods(List<IFood> foods){
		if(foods==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.variantFoods = foods;
return this;}


@Override
public String toString(){
return "Food [individual="+individual+",investigationRestriction="+investigationRestriction.summarize()+",alias="+alias+",characterOffset="+characterOffset+",characterOnset="+characterOnset+",creatorOfDishPersons="+creatorOfDishPersons+",ingredientEukaryotes="+ingredientEukaryotes+",originPopulatedPlaces="+originPopulatedPlaces+",serialVersionUID="+serialVersionUID+",textMention="+textMention+",variantFoods="+variantFoods+"]";}


}
