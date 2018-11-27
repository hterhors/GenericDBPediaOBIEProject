package de.hterhors.obie.projects.dbpedia.ontology.film.classes;

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
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.*;
import de.hterhors.obie.core.ontology.AbstractIndividual;

/**
*
* @author hterhors
*
*
*Nov 8, 2018
*/

@DirectInterface(get=IFilm.class)

@SuperRootClasses(get={Film.class, })

@DirectSiblings(get={})

@AssignableSubClasses(get={})
 public class Film implements IFilm{

final public static IndividualFactory<FilmIndividual> individualFactory = new IndividualFactory<>();
final public static Class<? extends AbstractIndividual> individualClassType = FilmIndividual.class;
static class FilmIndividual extends AbstractIndividual {

	private static final long serialVersionUID = 1L;		public FilmIndividual(String namespace, String name) {
			super(namespace, name);
		}

		@Override
		public String toString() {
			return "FilmIndividual [name=" + name + ", nameSpace=" + nameSpace + "]";
		}

	}
	public IndividualFactory<FilmIndividual> getIndividualFactory() {
		return individualFactory;
	}

	public final FilmIndividual individual;
	@Override
	public AbstractIndividual getIndividual() {
		return individual;
	}	final static public String ONTOLOGY_NAME = "http://dbpedia.org/ontology/Film";
	private Integer characterOffset;
	private Integer characterOnset;
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/director")
@RelationTypeCollection
private List<IPerson> directorPersons = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/distributor")
private List<IOrganisation> distributorOrganisations = new ArrayList<>();
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/musicComposer")
@RelationTypeCollection
private List<IMusicalArtist> musicComposerMusicalArtists = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/producer")
private List<IAgent> producerAgents = new ArrayList<>();
	final static private Map<IOBIEThing, String> resourceFactory = new HashMap<>();
	final static private long serialVersionUID = 1L;
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/starring")
private List<IActor> starringActors = new ArrayList<>();
	@TextMention
final private String textMention;
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/writer")
private List<IPerson> writerPersons = new ArrayList<>();


	public Film(String individualURI, String textMention){
this.individual = 
				Film.individualFactory.getIndividualByURI(individualURI);
this.textMention = textMention;
}
	public Film(){
this.individual = null;
this.textMention = null;
}
	public Film(Film film)throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{
this.individual = film.individual;
this.characterOffset = film.getCharacterOffset();
this.characterOnset = film.getCharacterOnset();
for (int j = 0; j < film.getDirectorPersons().size(); j++) {if (film.getDirectorPersons().get(j) != null) {directorPersons.add((IPerson) IOBIEThing.getCloneConstructor(film.getDirectorPersons().get(j).getClass()).newInstance(film.getDirectorPersons().get(j)));} else {directorPersons.add(null);}}
for (int j = 0; j < film.getDistributorOrganisations().size(); j++) {if (film.getDistributorOrganisations().get(j) != null) {distributorOrganisations.add((IOrganisation) IOBIEThing.getCloneConstructor(film.getDistributorOrganisations().get(j).getClass()).newInstance(film.getDistributorOrganisations().get(j)));} else {distributorOrganisations.add(null);}}
for (int j = 0; j < film.getMusicComposerMusicalArtists().size(); j++) {if (film.getMusicComposerMusicalArtists().get(j) != null) {musicComposerMusicalArtists.add((IMusicalArtist) IOBIEThing.getCloneConstructor(film.getMusicComposerMusicalArtists().get(j).getClass()).newInstance(film.getMusicComposerMusicalArtists().get(j)));} else {musicComposerMusicalArtists.add(null);}}
for (int j = 0; j < film.getProducerAgents().size(); j++) {if (film.getProducerAgents().get(j) != null) {producerAgents.add((IAgent) IOBIEThing.getCloneConstructor(film.getProducerAgents().get(j).getClass()).newInstance(film.getProducerAgents().get(j)));} else {producerAgents.add(null);}}
for (int j = 0; j < film.getStarringActors().size(); j++) {if (film.getStarringActors().get(j) != null) {starringActors.add((IActor) IOBIEThing.getCloneConstructor(film.getStarringActors().get(j).getClass()).newInstance(film.getStarringActors().get(j)));} else {starringActors.add(null);}}
this.textMention = film.getTextMention();
for (int j = 0; j < film.getWriterPersons().size(); j++) {if (film.getWriterPersons().get(j) != null) {writerPersons.add((IPerson) IOBIEThing.getCloneConstructor(film.getWriterPersons().get(j).getClass()).newInstance(film.getWriterPersons().get(j)));} else {writerPersons.add(null);}}
}


	/***/
@Override
	public Film addDirectorPerson(IPerson person){
		this.directorPersons.add(person);
return this;}
	/***/
@Override
	public Film addDistributorOrganisation(IOrganisation organisation){
		this.distributorOrganisations.add(organisation);
return this;}
	/***/
@Override
	public Film addMusicComposerMusicalArtist(IMusicalArtist musicalArtist){
		this.musicComposerMusicalArtists.add(musicalArtist);
return this;}
	/***/
@Override
	public Film addProducerAgent(IAgent agent){
		this.producerAgents.add(agent);
return this;}
	/***/
@Override
	public Film addStarringActor(IActor actor){
		this.starringActors.add(actor);
return this;}
	/***/
@Override
	public Film addWriterPerson(IPerson person){
		this.writerPersons.add(person);
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
Film other = (Film) obj;
if (individual == null) {
if (other.individual!= null)
return false;
} else if (!individual.equals(other.individual))
return false;
if (directorPersons == null) {
if (other.directorPersons!= null)
return false;
} else if (!directorPersons.equals(other.directorPersons))
return false;
if (writerPersons == null) {
if (other.writerPersons!= null)
return false;
} else if (!writerPersons.equals(other.writerPersons))
return false;
if (producerAgents == null) {
if (other.producerAgents!= null)
return false;
} else if (!producerAgents.equals(other.producerAgents))
return false;
if (distributorOrganisations == null) {
if (other.distributorOrganisations!= null)
return false;
} else if (!distributorOrganisations.equals(other.distributorOrganisations))
return false;
if (characterOnset == null) {
if (other.characterOnset!= null)
return false;
} else if (!characterOnset.equals(other.characterOnset))
return false;
if (characterOffset == null) {
if (other.characterOffset!= null)
return false;
} else if (!characterOffset.equals(other.characterOffset))
return false;
if (musicComposerMusicalArtists == null) {
if (other.musicComposerMusicalArtists!= null)
return false;
} else if (!musicComposerMusicalArtists.equals(other.musicComposerMusicalArtists))
return false;
if (textMention == null) {
if (other.textMention!= null)
return false;
} else if (!textMention.equals(other.textMention))
return false;
if (starringActors == null) {
if (other.starringActors!= null)
return false;
} else if (!starringActors.equals(other.starringActors))
return false;
return true;
}
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
	public List<IPerson> getDirectorPersons(){
		return directorPersons;}
	/***/
@Override
	public List<IOrganisation> getDistributorOrganisations(){
		return distributorOrganisations;}
	/***/
@Override
	public List<IMusicalArtist> getMusicComposerMusicalArtists(){
		return musicComposerMusicalArtists;}
	/***/
@Override
	public String getONTOLOGY_NAME(){
		return ONTOLOGY_NAME;}
	/***/
@Override
	public List<IAgent> getProducerAgents(){
		return producerAgents;}
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
return IFilmThing.RDF_MODEL_NAMESPACE + resourceFactory.get(this);
} else {
final String resourceName = getClass().getSimpleName() + "_" + resourceFactory.size();
resourceFactory.put(this, resourceName);
return IFilmThing.RDF_MODEL_NAMESPACE + resourceName;}
}
	/***/
@Override
	public List<IActor> getStarringActors(){
		return starringActors;}
	/***/
@Override
	public String getTextMention(){
		return textMention;}
	/***/
@Override
	public List<IPerson> getWriterPersons(){
		return writerPersons;}
	/***/
@Override
	public int hashCode(){
		final int prime = 31;
int result = 1;
result = prime * result + ((this.individual == null) ? 0 : this.individual.hashCode());
result = prime * result + ((this.directorPersons == null) ? 0 : this.directorPersons.hashCode());
result = prime * result + ((this.writerPersons == null) ? 0 : this.writerPersons.hashCode());
result = prime * result + ((this.producerAgents == null) ? 0 : this.producerAgents.hashCode());
result = prime * result + ((this.distributorOrganisations == null) ? 0 : this.distributorOrganisations.hashCode());
result = prime * result + ((this.characterOnset == null) ? 0 : this.characterOnset.hashCode());
result = prime * result + ((this.characterOffset == null) ? 0 : this.characterOffset.hashCode());
result = prime * result + ((this.musicComposerMusicalArtists == null) ? 0 : this.musicComposerMusicalArtists.hashCode());
result = prime * result + ((this.textMention == null) ? 0 : this.textMention.hashCode());
result = prime * result + ((this.starringActors == null) ? 0 : this.starringActors.hashCode());
return result;}
	/***/
@Override
	public boolean isEmpty(){
		boolean isEmpty = true;
return false;}
	/***/
@Override
	public void setCharacterOnset(Integer onset){
		this.characterOnset = onset;
 this.characterOffset = onset + textMention.length();}
	/***/
@Override
	public Film setDirectorPersons(List<IPerson> persons){
		if(persons==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.directorPersons = persons;
return this;}
	/***/
@Override
	public Film setDistributorOrganisations(List<IOrganisation> organisations){
		if(organisations==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.distributorOrganisations = organisations;
return this;}
	/***/
@Override
	public Film setMusicComposerMusicalArtists(List<IMusicalArtist> musicalArtists){
		if(musicalArtists==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.musicComposerMusicalArtists = musicalArtists;
return this;}
	/***/
@Override
	public Film setProducerAgents(List<IAgent> agents){
		if(agents==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.producerAgents = agents;
return this;}
	/***/
@Override
	public Film setStarringActors(List<IActor> actors){
		if(actors==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.starringActors = actors;
return this;}
	/***/
@Override
	public Film setWriterPersons(List<IPerson> persons){
		if(persons==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.writerPersons = persons;
return this;}


@Override
public String toString(){
return "Film [individual="+individual+",characterOffset="+characterOffset+",characterOnset="+characterOnset+",directorPersons="+directorPersons+",distributorOrganisations="+distributorOrganisations+",musicComposerMusicalArtists="+musicComposerMusicalArtists+",producerAgents="+producerAgents+",serialVersionUID="+serialVersionUID+",starringActors="+starringActors+",textMention="+textMention+",writerPersons="+writerPersons+"]";}


}
