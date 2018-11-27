package de.hterhors.obie.projects.dbpedia.ontology.dam.classes;

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
import java.lang.SecurityException;
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
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.*;
import org.apache.jena.rdf.model.ModelFactory;
import de.hterhors.obie.core.ontology.AbstractIndividual;

/**
*
* @author hterhors
*
*
*Nov 6, 2018
*/

@SuperRootClasses(get={Dam.class, })

@DirectInterface(get=IDam.class)

@AssignableSubClasses(get={})

@DirectSiblings(get={})
 public class Dam implements IDam{

final public static IndividualFactory<DamIndividual> individualFactory = new IndividualFactory<>();
final public static Class<? extends AbstractIndividual> individualClassType = DamIndividual.class;
static class DamIndividual extends AbstractIndividual {

	private static final long serialVersionUID = 1L;		public DamIndividual(String namespace, String name) {
			super(namespace, name);
		}

		@Override
		public String toString() {
			return "DamIndividual [name=" + name + ", nameSpace=" + nameSpace + "]";
		}

	}
	public IndividualFactory<DamIndividual> getIndividualFactory() {
		return individualFactory;
	}

	public final DamIndividual individual;
	@Override
	public AbstractIndividual getIndividual() {
		return individual;
	}	final static public String ONTOLOGY_NAME = "http://dbpedia.org/ontology/Dam";
	@DatatypeProperty
@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/buildingStartYear")
private List<IBuildingStartYear> buildingStartYears = new ArrayList<>();
	private Integer characterOffset;
	private Integer characterOnset;
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/country")
private List<ICountry> countries = new ArrayList<>();
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/location")
@RelationTypeCollection
private List<IPlace> locationPlaces = new ArrayList<>();
	@DatatypeProperty
@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/openingYear")
private List<IOpeningYear> openingYears = new ArrayList<>();
	final static private Map<IOBIEThing, String> resourceFactory = new HashMap<>();
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/river")
@RelationTypeCollection
private List<IRiver> rivers = new ArrayList<>();
	final static private long serialVersionUID = 1L;
	@DatatypeProperty
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/status")
private IStatus status;
	@TextMention
final private String textMention;


	public Dam(Dam dam)throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{
this.individual = dam.individual;
for (int j = 0; j < dam.getBuildingStartYears().size(); j++) {if (dam.getBuildingStartYears().get(j) != null) {buildingStartYears.add((IBuildingStartYear) IOBIEThing.getCloneConstructor(dam.getBuildingStartYears().get(j).getClass()).newInstance(dam.getBuildingStartYears().get(j)));} else {buildingStartYears.add(null);}}
this.characterOffset = dam.getCharacterOffset();
this.characterOnset = dam.getCharacterOnset();
for (int j = 0; j < dam.getCountries().size(); j++) {if (dam.getCountries().get(j) != null) {countries.add((ICountry) IOBIEThing.getCloneConstructor(dam.getCountries().get(j).getClass()).newInstance(dam.getCountries().get(j)));} else {countries.add(null);}}
for (int j = 0; j < dam.getLocationPlaces().size(); j++) {if (dam.getLocationPlaces().get(j) != null) {locationPlaces.add((IPlace) IOBIEThing.getCloneConstructor(dam.getLocationPlaces().get(j).getClass()).newInstance(dam.getLocationPlaces().get(j)));} else {locationPlaces.add(null);}}
for (int j = 0; j < dam.getOpeningYears().size(); j++) {if (dam.getOpeningYears().get(j) != null) {openingYears.add((IOpeningYear) IOBIEThing.getCloneConstructor(dam.getOpeningYears().get(j).getClass()).newInstance(dam.getOpeningYears().get(j)));} else {openingYears.add(null);}}
for (int j = 0; j < dam.getRivers().size(); j++) {if (dam.getRivers().get(j) != null) {rivers.add((IRiver) IOBIEThing.getCloneConstructor(dam.getRivers().get(j).getClass()).newInstance(dam.getRivers().get(j)));} else {rivers.add(null);}}
if(dam.getStatus()!=null)this.status = new Status((Status)dam.getStatus());
this.textMention = dam.getTextMention();
}
	public Dam(){
this.individual = null;
this.textMention = null;
}
	public Dam(String individualURI, String textMention){
this.individual = 
				Dam.individualFactory.getIndividualByURI(individualURI);
this.textMention = textMention;
}


	/***/
@Override
	public Dam addBuildingStartYear(IBuildingStartYear buildingStartYear){
		this.buildingStartYears.add(buildingStartYear);
return this;}
	/***/
@Override
	public Dam addCountry(ICountry country){
		this.countries.add(country);
return this;}
	/***/
@Override
	public Dam addLocationPlace(IPlace place){
		this.locationPlaces.add(place);
return this;}
	/***/
@Override
	public Dam addOpeningYear(IOpeningYear openingYear){
		this.openingYears.add(openingYear);
return this;}
	/***/
@Override
	public Dam addRiver(IRiver river){
		this.rivers.add(river);
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
Dam other = (Dam) obj;
if (individual == null) {
if (other.individual!= null)
return false;
} else if (!individual.equals(other.individual))
return false;
if (status == null) {
if (other.status!= null)
return false;
} else if (!status.equals(other.status))
return false;
if (characterOffset == null) {
if (other.characterOffset!= null)
return false;
} else if (!characterOffset.equals(other.characterOffset))
return false;
if (buildingStartYears == null) {
if (other.buildingStartYears!= null)
return false;
} else if (!buildingStartYears.equals(other.buildingStartYears))
return false;
if (characterOnset == null) {
if (other.characterOnset!= null)
return false;
} else if (!characterOnset.equals(other.characterOnset))
return false;
if (openingYears == null) {
if (other.openingYears!= null)
return false;
} else if (!openingYears.equals(other.openingYears))
return false;
if (locationPlaces == null) {
if (other.locationPlaces!= null)
return false;
} else if (!locationPlaces.equals(other.locationPlaces))
return false;
if (rivers == null) {
if (other.rivers!= null)
return false;
} else if (!rivers.equals(other.rivers))
return false;
if (countries == null) {
if (other.countries!= null)
return false;
} else if (!countries.equals(other.countries))
return false;
if (textMention == null) {
if (other.textMention!= null)
return false;
} else if (!textMention.equals(other.textMention))
return false;
return true;
}
	/***/
@Override
	public List<IBuildingStartYear> getBuildingStartYears(){
		return buildingStartYears;}
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
	public List<ICountry> getCountries(){
		return countries;}
	/***/
@Override
	public List<IPlace> getLocationPlaces(){
		return locationPlaces;}
	/***/
@Override
	public String getONTOLOGY_NAME(){
		return ONTOLOGY_NAME;}
	/***/
@Override
	public List<IOpeningYear> getOpeningYears(){
		return openingYears;}
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
return IDamThing.RDF_MODEL_NAMESPACE + resourceFactory.get(this);
} else {
final String resourceName = getClass().getSimpleName() + "_" + resourceFactory.size();
resourceFactory.put(this, resourceName);
return IDamThing.RDF_MODEL_NAMESPACE + resourceName;}
}
	/***/
@Override
	public List<IRiver> getRivers(){
		return rivers;}
	/***/
@Override
	public IStatus getStatus(){
		return status;}
	/***/
@Override
	public String getTextMention(){
		return textMention;}
	/***/
@Override
	public int hashCode(){
		final int prime = 31;
int result = 1;
result = prime * result + ((this.individual == null) ? 0 : this.individual.hashCode());
result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
result = prime * result + ((this.characterOffset == null) ? 0 : this.characterOffset.hashCode());
result = prime * result + ((this.buildingStartYears == null) ? 0 : this.buildingStartYears.hashCode());
result = prime * result + ((this.characterOnset == null) ? 0 : this.characterOnset.hashCode());
result = prime * result + ((this.openingYears == null) ? 0 : this.openingYears.hashCode());
result = prime * result + ((this.locationPlaces == null) ? 0 : this.locationPlaces.hashCode());
result = prime * result + ((this.rivers == null) ? 0 : this.rivers.hashCode());
result = prime * result + ((this.countries == null) ? 0 : this.countries.hashCode());
result = prime * result + ((this.textMention == null) ? 0 : this.textMention.hashCode());
return result;}
	/***/
@Override
	public boolean isEmpty(){
		boolean isEmpty = true;
return false;}
	/***/
@Override
	public Dam setBuildingStartYears(List<IBuildingStartYear> buildingStartYears){
		if(buildingStartYears==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.buildingStartYears = buildingStartYears;
return this;}
	/***/
@Override
	public void setCharacterOnset(Integer onset){
		this.characterOnset = onset;
 this.characterOffset = onset + textMention.length();}
	/***/
@Override
	public Dam setCountries(List<ICountry> countries){
		if(countries==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.countries = countries;
return this;}
	/***/
@Override
	public Dam setLocationPlaces(List<IPlace> places){
		if(places==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.locationPlaces = places;
return this;}
	/***/
@Override
	public Dam setOpeningYears(List<IOpeningYear> openingYears){
		if(openingYears==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.openingYears = openingYears;
return this;}
	/***/
@Override
	public Dam setRivers(List<IRiver> rivers){
		if(rivers==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.rivers = rivers;
return this;}
	/***/
@Override
	public Dam setStatus(IStatus status){
		this.status = status;
return this;}


@Override
public String toString(){
return "Dam [individual="+individual+",buildingStartYears="+buildingStartYears+",characterOffset="+characterOffset+",characterOnset="+characterOnset+",countries="+countries+",locationPlaces="+locationPlaces+",openingYears="+openingYears+",rivers="+rivers+",serialVersionUID="+serialVersionUID+",status="+status+",textMention="+textMention+"]";}


}
