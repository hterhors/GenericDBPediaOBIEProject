package de.hterhors.obie.projects.dbpedia.ontology.manga.classes;

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
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.*;
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

@AssignableSubClasses(get={})

@DirectInterface(get=IManga.class)

@SuperRootClasses(get={Manga.class, })
 public class Manga implements IManga{

final public static IndividualFactory<MangaIndividual> individualFactory = new IndividualFactory<>();
final public static Class<? extends AbstractIndividual> individualClassType = MangaIndividual.class;
static class MangaIndividual extends AbstractIndividual {

	private static final long serialVersionUID = 1L;		public MangaIndividual(String namespace, String name) {
			super(namespace, name);
		}

		@Override
		public String toString() {
			return "MangaIndividual [name=" + name + ", nameSpace=" + nameSpace + "]";
		}

	}
	public IndividualFactory<MangaIndividual> getIndividualFactory() {
		return individualFactory;
	}

	public final MangaIndividual individual;
	@Override
	public AbstractIndividual getIndividual() {
		return individual;
	}
	@Override
	public InvestigationRestriction getInvestigationRestriction() {
		return investigationRestriction;
	}
	@Override
	public Manga setInvestigationRestriction(InvestigationRestriction investigationRestriction ) {
		this.investigationRestriction = investigationRestriction;
 return this;	}public InvestigationRestriction investigationRestriction;	final static public String ONTOLOGY_NAME = "http://dbpedia.org/ontology/Manga";
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/author")
@RelationTypeCollection
private List<IPerson> authorPersons = new ArrayList<>();
	private Integer characterOffset;
	private Integer characterOnset;
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/illustrator")
@RelationTypeCollection
private List<IPerson> illustratorPersons = new ArrayList<>();
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/magazine")
@RelationTypeCollection
private List<IMagazine> magazines = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/numberOfVolumes")
@DatatypeProperty
private List<INumberOfVolumes> numberOfVolumes = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/publisher")
private List<ICompany> publisherCompanies = new ArrayList<>();
	final static private Map<IOBIEThing, String> resourceFactory = new HashMap<>();
	final static private long serialVersionUID = 2L;
	@TextMention
final private String textMention;


	public Manga(Manga manga)throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{
this.individual = manga.individual;
this.investigationRestriction = manga.investigationRestriction;
for (int j = 0; j < manga.getAuthorPersons().size(); j++) {if (manga.getAuthorPersons().get(j) != null) {authorPersons.add((IPerson) IOBIEThing.getCloneConstructor(manga.getAuthorPersons().get(j).getClass()).newInstance(manga.getAuthorPersons().get(j)));} else {authorPersons.add(null);}}
this.characterOffset = manga.getCharacterOffset();
this.characterOnset = manga.getCharacterOnset();
for (int j = 0; j < manga.getIllustratorPersons().size(); j++) {if (manga.getIllustratorPersons().get(j) != null) {illustratorPersons.add((IPerson) IOBIEThing.getCloneConstructor(manga.getIllustratorPersons().get(j).getClass()).newInstance(manga.getIllustratorPersons().get(j)));} else {illustratorPersons.add(null);}}
for (int j = 0; j < manga.getMagazines().size(); j++) {if (manga.getMagazines().get(j) != null) {magazines.add((IMagazine) IOBIEThing.getCloneConstructor(manga.getMagazines().get(j).getClass()).newInstance(manga.getMagazines().get(j)));} else {magazines.add(null);}}
for (int j = 0; j < manga.getNumberOfVolumes().size(); j++) {if (manga.getNumberOfVolumes().get(j) != null) {numberOfVolumes.add((INumberOfVolumes) IOBIEThing.getCloneConstructor(manga.getNumberOfVolumes().get(j).getClass()).newInstance(manga.getNumberOfVolumes().get(j)));} else {numberOfVolumes.add(null);}}
for (int j = 0; j < manga.getPublisherCompanies().size(); j++) {if (manga.getPublisherCompanies().get(j) != null) {publisherCompanies.add((ICompany) IOBIEThing.getCloneConstructor(manga.getPublisherCompanies().get(j).getClass()).newInstance(manga.getPublisherCompanies().get(j)));} else {publisherCompanies.add(null);}}
this.textMention = manga.getTextMention();
}
	public Manga(String individualURI, InvestigationRestriction investigationRestriction, String textMention){
this.individual = 
				Manga.individualFactory.getIndividualByURI(individualURI);
this.investigationRestriction = investigationRestriction==null?InvestigationRestriction.noRestrictionInstance:investigationRestriction;
this.textMention = textMention;
}
	public Manga(){
this.individual = null;
this.investigationRestriction = InvestigationRestriction.noRestrictionInstance;
this.textMention = null;
}


	/***/
@Override
	public Manga addAuthorPerson(IPerson person){
		this.authorPersons.add(person);
return this;}
	/***/
@Override
	public Manga addIllustratorPerson(IPerson person){
		this.illustratorPersons.add(person);
return this;}
	/***/
@Override
	public Manga addMagazine(IMagazine magazine){
		this.magazines.add(magazine);
return this;}
	/***/
@Override
	public Manga addNumberOfVolumes(INumberOfVolumes numberOfVolumes){
		this.numberOfVolumes.add(numberOfVolumes);
return this;}
	/***/
@Override
	public Manga addPublisherCompany(ICompany company){
		this.publisherCompanies.add(company);
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
Manga other = (Manga) obj;
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
if (characterOffset == null) {
if (other.characterOffset!= null)
return false;
} else if (!characterOffset.equals(other.characterOffset))
return false;
if (authorPersons == null) {
if (other.authorPersons!= null)
return false;
} else if (!authorPersons.equals(other.authorPersons))
return false;
if (publisherCompanies == null) {
if (other.publisherCompanies!= null)
return false;
} else if (!publisherCompanies.equals(other.publisherCompanies))
return false;
if (illustratorPersons == null) {
if (other.illustratorPersons!= null)
return false;
} else if (!illustratorPersons.equals(other.illustratorPersons))
return false;
if (magazines == null) {
if (other.magazines!= null)
return false;
} else if (!magazines.equals(other.magazines))
return false;
if (numberOfVolumes == null) {
if (other.numberOfVolumes!= null)
return false;
} else if (!numberOfVolumes.equals(other.numberOfVolumes))
return false;
return true;
}
	/***/
@Override
	public List<IPerson> getAuthorPersons(){
		return authorPersons;}
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
	public List<IPerson> getIllustratorPersons(){
		return illustratorPersons;}
	/***/
@Override
	public List<IMagazine> getMagazines(){
		return magazines;}
	/***/
@Override
	public List<INumberOfVolumes> getNumberOfVolumes(){
		return numberOfVolumes;}
	/***/
@Override
	public String getONTOLOGY_NAME(){
		return ONTOLOGY_NAME;}
	/***/
@Override
	public List<ICompany> getPublisherCompanies(){
		return publisherCompanies;}
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
return IMangaThing.RDF_MODEL_NAMESPACE + resourceFactory.get(this);
} else {
final String resourceName = getClass().getSimpleName() + "_" + resourceFactory.size();
resourceFactory.put(this, resourceName);
return IMangaThing.RDF_MODEL_NAMESPACE + resourceName;}
}
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
result = prime * result + ((this.investigationRestriction == null) ? 0 : this.investigationRestriction.hashCode());
result = prime * result + ((this.characterOnset == null) ? 0 : this.characterOnset.hashCode());
result = prime * result + ((this.textMention == null) ? 0 : this.textMention.hashCode());
result = prime * result + ((this.characterOffset == null) ? 0 : this.characterOffset.hashCode());
result = prime * result + ((this.authorPersons == null) ? 0 : this.authorPersons.hashCode());
result = prime * result + ((this.publisherCompanies == null) ? 0 : this.publisherCompanies.hashCode());
result = prime * result + ((this.illustratorPersons == null) ? 0 : this.illustratorPersons.hashCode());
result = prime * result + ((this.magazines == null) ? 0 : this.magazines.hashCode());
result = prime * result + ((this.numberOfVolumes == null) ? 0 : this.numberOfVolumes.hashCode());
return result;}
	/***/
@Override
	public boolean isEmpty(){
		boolean isEmpty = true;
return false;}
	/***/
@Override
	public Manga setAuthorPersons(List<IPerson> persons){
		if(persons==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.authorPersons = persons;
return this;}
	/***/
@Override
	public void setCharacterOnset(Integer onset){
		this.characterOnset = onset;
 this.characterOffset = onset + textMention.length();}
	/***/
@Override
	public Manga setIllustratorPersons(List<IPerson> persons){
		if(persons==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.illustratorPersons = persons;
return this;}
	/***/
@Override
	public Manga setMagazines(List<IMagazine> magazines){
		if(magazines==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.magazines = magazines;
return this;}
	/***/
@Override
	public Manga setNumberOfVolumes(List<INumberOfVolumes> numberOfVolumes){
		if(numberOfVolumes==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.numberOfVolumes = numberOfVolumes;
return this;}
	/***/
@Override
	public Manga setPublisherCompanies(List<ICompany> companies){
		if(companies==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.publisherCompanies = companies;
return this;}


@Override
public String toString(){
return "Manga [individual="+individual+",investigationRestriction="+investigationRestriction.summarize()+",authorPersons="+authorPersons+",characterOffset="+characterOffset+",characterOnset="+characterOnset+",illustratorPersons="+illustratorPersons+",magazines="+magazines+",numberOfVolumes="+numberOfVolumes+",publisherCompanies="+publisherCompanies+",serialVersionUID="+serialVersionUID+",textMention="+textMention+"]";}


}
