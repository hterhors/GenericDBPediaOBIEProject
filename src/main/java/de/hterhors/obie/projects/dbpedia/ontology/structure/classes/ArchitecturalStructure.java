package de.hterhors.obie.projects.dbpedia.ontology.structure.classes;

import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.*;
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
import org.apache.jena.rdf.model.ModelFactory;
import de.hterhors.obie.core.ontology.AbstractIndividual;

/**
*
* @author hterhors
*
*
*Nov 27, 2018
*/

@DirectInterface(get=IArchitecturalStructure.class)

@DirectSiblings(get={})

@AssignableSubClasses(get={})

@SuperRootClasses(get={ArchitecturalStructure.class, })
 public class ArchitecturalStructure implements IArchitecturalStructure{

final public static IndividualFactory<ArchitecturalStructureIndividual> individualFactory = new IndividualFactory<>();
final public static Class<? extends AbstractIndividual> individualClassType = ArchitecturalStructureIndividual.class;
static class ArchitecturalStructureIndividual extends AbstractIndividual {

	private static final long serialVersionUID = 1L;		public ArchitecturalStructureIndividual(String namespace, String name) {
			super(namespace, name);
		}

		@Override
		public String toString() {
			return "ArchitecturalStructureIndividual [name=" + name + ", nameSpace=" + nameSpace + "]";
		}

	}
	public IndividualFactory<ArchitecturalStructureIndividual> getIndividualFactory() {
		return individualFactory;
	}

	public final ArchitecturalStructureIndividual individual;
	@Override
	public AbstractIndividual getIndividual() {
		return individual;
	}	final static public String ONTOLOGY_NAME = "http://dbpedia.org/ontology/ArchitecturalStructure";
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/architect")
@RelationTypeCollection
private List<IArchitect> architects = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/architecturalStyle")
private List<IArchitecturalStyle> architecturalStyles = new ArrayList<>();
	private Integer characterOffset;
	private Integer characterOnset;
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/location")
@RelationTypeCollection
private List<IPlace> locationPlaces = new ArrayList<>();
	@DatatypeProperty
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/openingYear")
private IOpeningYear openingYear;
	final static private Map<IOBIEThing, String> resourceFactory = new HashMap<>();
	final static private long serialVersionUID = 3L;
	@TextMention
final private String textMention;
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/yearOfConstruction")
@DatatypeProperty
private List<IYearOfConstruction> yearOfConstructions = new ArrayList<>();


	public ArchitecturalStructure(String individualURI, String textMention){
this.individual = 
				ArchitecturalStructure.individualFactory.getIndividualByURI(individualURI);
this.textMention = textMention;
}
	public ArchitecturalStructure(){
this.individual = null;
this.textMention = null;
}
	public ArchitecturalStructure(ArchitecturalStructure architecturalStructure)throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{
this.individual = architecturalStructure.individual;
for (int j = 0; j < architecturalStructure.getArchitects().size(); j++) {if (architecturalStructure.getArchitects().get(j) != null) {architects.add((IArchitect) IOBIEThing.getCloneConstructor(architecturalStructure.getArchitects().get(j).getClass()).newInstance(architecturalStructure.getArchitects().get(j)));} else {architects.add(null);}}
for (int j = 0; j < architecturalStructure.getArchitecturalStyles().size(); j++) {if (architecturalStructure.getArchitecturalStyles().get(j) != null) {architecturalStyles.add((IArchitecturalStyle) IOBIEThing.getCloneConstructor(architecturalStructure.getArchitecturalStyles().get(j).getClass()).newInstance(architecturalStructure.getArchitecturalStyles().get(j)));} else {architecturalStyles.add(null);}}
this.characterOffset = architecturalStructure.getCharacterOffset();
this.characterOnset = architecturalStructure.getCharacterOnset();
for (int j = 0; j < architecturalStructure.getLocationPlaces().size(); j++) {if (architecturalStructure.getLocationPlaces().get(j) != null) {locationPlaces.add((IPlace) IOBIEThing.getCloneConstructor(architecturalStructure.getLocationPlaces().get(j).getClass()).newInstance(architecturalStructure.getLocationPlaces().get(j)));} else {locationPlaces.add(null);}}
if(architecturalStructure.getOpeningYear()!=null)this.openingYear = new OpeningYear((OpeningYear)architecturalStructure.getOpeningYear());
this.textMention = architecturalStructure.getTextMention();
for (int j = 0; j < architecturalStructure.getYearOfConstructions().size(); j++) {if (architecturalStructure.getYearOfConstructions().get(j) != null) {yearOfConstructions.add((IYearOfConstruction) IOBIEThing.getCloneConstructor(architecturalStructure.getYearOfConstructions().get(j).getClass()).newInstance(architecturalStructure.getYearOfConstructions().get(j)));} else {yearOfConstructions.add(null);}}
}


	/***/
@Override
	public ArchitecturalStructure addArchitect(IArchitect architect){
		this.architects.add(architect);
return this;}
	/***/
@Override
	public ArchitecturalStructure addArchitecturalStyle(IArchitecturalStyle architecturalStyle){
		this.architecturalStyles.add(architecturalStyle);
return this;}
	/***/
@Override
	public ArchitecturalStructure addLocationPlace(IPlace place){
		this.locationPlaces.add(place);
return this;}
	/***/
@Override
	public ArchitecturalStructure addYearOfConstruction(IYearOfConstruction yearOfConstruction){
		this.yearOfConstructions.add(yearOfConstruction);
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
ArchitecturalStructure other = (ArchitecturalStructure) obj;
if (individual == null) {
if (other.individual!= null)
return false;
} else if (!individual.equals(other.individual))
return false;
if (yearOfConstructions == null) {
if (other.yearOfConstructions!= null)
return false;
} else if (!yearOfConstructions.equals(other.yearOfConstructions))
return false;
if (characterOnset == null) {
if (other.characterOnset!= null)
return false;
} else if (!characterOnset.equals(other.characterOnset))
return false;
if (textMention == null) {
if (other.textMention!= null)
return false;
} else if (!textMention.equals(other.textMention))
return false;
if (locationPlaces == null) {
if (other.locationPlaces!= null)
return false;
} else if (!locationPlaces.equals(other.locationPlaces))
return false;
if (characterOffset == null) {
if (other.characterOffset!= null)
return false;
} else if (!characterOffset.equals(other.characterOffset))
return false;
if (architects == null) {
if (other.architects!= null)
return false;
} else if (!architects.equals(other.architects))
return false;
if (openingYear == null) {
if (other.openingYear!= null)
return false;
} else if (!openingYear.equals(other.openingYear))
return false;
if (architecturalStyles == null) {
if (other.architecturalStyles!= null)
return false;
} else if (!architecturalStyles.equals(other.architecturalStyles))
return false;
return true;
}
	/***/
@Override
	public List<IArchitect> getArchitects(){
		return architects;}
	/***/
@Override
	public List<IArchitecturalStyle> getArchitecturalStyles(){
		return architecturalStyles;}
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
	public List<IPlace> getLocationPlaces(){
		return locationPlaces;}
	/***/
@Override
	public String getONTOLOGY_NAME(){
		return ONTOLOGY_NAME;}
	/***/
@Override
	public IOpeningYear getOpeningYear(){
		return openingYear;}
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
return IArchitecturalStructureThing.RDF_MODEL_NAMESPACE + resourceFactory.get(this);
} else {
final String resourceName = getClass().getSimpleName() + "_" + resourceFactory.size();
resourceFactory.put(this, resourceName);
return IArchitecturalStructureThing.RDF_MODEL_NAMESPACE + resourceName;}
}
	/***/
@Override
	public String getTextMention(){
		return textMention;}
	/***/
@Override
	public List<IYearOfConstruction> getYearOfConstructions(){
		return yearOfConstructions;}
	/***/
@Override
	public int hashCode(){
		final int prime = 31;
int result = 1;
result = prime * result + ((this.individual == null) ? 0 : this.individual.hashCode());
result = prime * result + ((this.yearOfConstructions == null) ? 0 : this.yearOfConstructions.hashCode());
result = prime * result + ((this.characterOnset == null) ? 0 : this.characterOnset.hashCode());
result = prime * result + ((this.textMention == null) ? 0 : this.textMention.hashCode());
result = prime * result + ((this.locationPlaces == null) ? 0 : this.locationPlaces.hashCode());
result = prime * result + ((this.characterOffset == null) ? 0 : this.characterOffset.hashCode());
result = prime * result + ((this.architects == null) ? 0 : this.architects.hashCode());
result = prime * result + ((this.openingYear == null) ? 0 : this.openingYear.hashCode());
result = prime * result + ((this.architecturalStyles == null) ? 0 : this.architecturalStyles.hashCode());
return result;}
	/***/
@Override
	public boolean isEmpty(){
		boolean isEmpty = true;
return false;}
	/***/
@Override
	public ArchitecturalStructure setArchitects(List<IArchitect> architects){
		if(architects==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.architects = architects;
return this;}
	/***/
@Override
	public ArchitecturalStructure setArchitecturalStyles(List<IArchitecturalStyle> architecturalStyles){
		if(architecturalStyles==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.architecturalStyles = architecturalStyles;
return this;}
	/***/
@Override
	public void setCharacterOnset(Integer onset){
		this.characterOnset = onset;
 this.characterOffset = onset + textMention.length();}
	/***/
@Override
	public ArchitecturalStructure setLocationPlaces(List<IPlace> places){
		if(places==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.locationPlaces = places;
return this;}
	/***/
@Override
	public ArchitecturalStructure setOpeningYear(IOpeningYear openingYear){
		this.openingYear = openingYear;
return this;}
	/***/
@Override
	public ArchitecturalStructure setYearOfConstructions(List<IYearOfConstruction> yearOfConstructions){
		if(yearOfConstructions==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.yearOfConstructions = yearOfConstructions;
return this;}


@Override
public String toString(){
return "ArchitecturalStructure [individual="+individual+",architects="+architects+",architecturalStyles="+architecturalStyles+",characterOffset="+characterOffset+",characterOnset="+characterOnset+",locationPlaces="+locationPlaces+",openingYear="+openingYear+",serialVersionUID="+serialVersionUID+",textMention="+textMention+",yearOfConstructions="+yearOfConstructions+"]";}


}
