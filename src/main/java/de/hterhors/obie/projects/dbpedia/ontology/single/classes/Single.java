package de.hterhors.obie.projects.dbpedia.ontology.single.classes;

import java.lang.NoSuchMethodException;
import de.hterhors.obie.core.ontology.interfaces.IDatatype;
import de.hterhors.obie.core.ontology.annotations.SuperRootClasses;
import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.*;
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
*Nov 14, 2018
*/

@SuperRootClasses(get={Single.class, })

@DirectInterface(get=ISingle.class)

@DirectSiblings(get={})

@AssignableSubClasses(get={})
 public class Single implements ISingle{

final public static IndividualFactory<SingleIndividual> individualFactory = new IndividualFactory<>();
final public static Class<? extends AbstractIndividual> individualClassType = SingleIndividual.class;
static class SingleIndividual extends AbstractIndividual {

	private static final long serialVersionUID = 1L;		public SingleIndividual(String namespace, String name) {
			super(namespace, name);
		}

		@Override
		public String toString() {
			return "SingleIndividual [name=" + name + ", nameSpace=" + nameSpace + "]";
		}

	}
	public IndividualFactory<SingleIndividual> getIndividualFactory() {
		return individualFactory;
	}

	public final SingleIndividual individual;
	@Override
	public AbstractIndividual getIndividual() {
		return individual;
	}	final static public String ONTOLOGY_NAME = "http://dbpedia.org/ontology/Single";
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/album")
private List<IAlbum> albums = new ArrayList<>();
	private Integer characterOffset;
	private Integer characterOnset;
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/genre")
@RelationTypeCollection
private List<IGenre> genres = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/musicalArtist")
private List<IMusicalArtist> musicalArtists = new ArrayList<>();
	@RelationTypeCollection
@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/musicalBand")
private List<IBand> musicalBands = new ArrayList<>();
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/producer")
@RelationTypeCollection
private List<IAgent> producerAgents = new ArrayList<>();
	final static private Map<IOBIEThing, String> resourceFactory = new HashMap<>();
	final static private long serialVersionUID = 1L;
	@TextMention
final private String textMention;
	@OntologyModelContent(ontologyName="http://dbpedia.org/ontology/writer")
@RelationTypeCollection
private List<IPerson> writerPersons = new ArrayList<>();


	public Single(String individualURI, String textMention){
this.individual = 
				Single.individualFactory.getIndividualByURI(individualURI);
this.textMention = textMention;
}
	public Single(){
this.individual = null;
this.textMention = null;
}
	public Single(Single single)throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,NoSuchMethodException, SecurityException{
this.individual = single.individual;
for (int j = 0; j < single.getAlbums().size(); j++) {if (single.getAlbums().get(j) != null) {albums.add((IAlbum) IOBIEThing.getCloneConstructor(single.getAlbums().get(j).getClass()).newInstance(single.getAlbums().get(j)));} else {albums.add(null);}}
this.characterOffset = single.getCharacterOffset();
this.characterOnset = single.getCharacterOnset();
for (int j = 0; j < single.getGenres().size(); j++) {if (single.getGenres().get(j) != null) {genres.add((IGenre) IOBIEThing.getCloneConstructor(single.getGenres().get(j).getClass()).newInstance(single.getGenres().get(j)));} else {genres.add(null);}}
for (int j = 0; j < single.getMusicalArtists().size(); j++) {if (single.getMusicalArtists().get(j) != null) {musicalArtists.add((IMusicalArtist) IOBIEThing.getCloneConstructor(single.getMusicalArtists().get(j).getClass()).newInstance(single.getMusicalArtists().get(j)));} else {musicalArtists.add(null);}}
for (int j = 0; j < single.getMusicalBands().size(); j++) {if (single.getMusicalBands().get(j) != null) {musicalBands.add((IBand) IOBIEThing.getCloneConstructor(single.getMusicalBands().get(j).getClass()).newInstance(single.getMusicalBands().get(j)));} else {musicalBands.add(null);}}
for (int j = 0; j < single.getProducerAgents().size(); j++) {if (single.getProducerAgents().get(j) != null) {producerAgents.add((IAgent) IOBIEThing.getCloneConstructor(single.getProducerAgents().get(j).getClass()).newInstance(single.getProducerAgents().get(j)));} else {producerAgents.add(null);}}
this.textMention = single.getTextMention();
for (int j = 0; j < single.getWriterPersons().size(); j++) {if (single.getWriterPersons().get(j) != null) {writerPersons.add((IPerson) IOBIEThing.getCloneConstructor(single.getWriterPersons().get(j).getClass()).newInstance(single.getWriterPersons().get(j)));} else {writerPersons.add(null);}}
}


	/***/
@Override
	public Single addAlbum(IAlbum album){
		this.albums.add(album);
return this;}
	/***/
@Override
	public Single addGenre(IGenre genre){
		this.genres.add(genre);
return this;}
	/***/
@Override
	public Single addMusicalArtist(IMusicalArtist musicalArtist){
		this.musicalArtists.add(musicalArtist);
return this;}
	/***/
@Override
	public Single addMusicalBand(IBand band){
		this.musicalBands.add(band);
return this;}
	/***/
@Override
	public Single addProducerAgent(IAgent agent){
		this.producerAgents.add(agent);
return this;}
	/***/
@Override
	public Single addWriterPerson(IPerson person){
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
Single other = (Single) obj;
if (individual == null) {
if (other.individual!= null)
return false;
} else if (!individual.equals(other.individual))
return false;
if (characterOffset == null) {
if (other.characterOffset!= null)
return false;
} else if (!characterOffset.equals(other.characterOffset))
return false;
if (textMention == null) {
if (other.textMention!= null)
return false;
} else if (!textMention.equals(other.textMention))
return false;
if (characterOnset == null) {
if (other.characterOnset!= null)
return false;
} else if (!characterOnset.equals(other.characterOnset))
return false;
if (musicalArtists == null) {
if (other.musicalArtists!= null)
return false;
} else if (!musicalArtists.equals(other.musicalArtists))
return false;
if (genres == null) {
if (other.genres!= null)
return false;
} else if (!genres.equals(other.genres))
return false;
if (producerAgents == null) {
if (other.producerAgents!= null)
return false;
} else if (!producerAgents.equals(other.producerAgents))
return false;
if (albums == null) {
if (other.albums!= null)
return false;
} else if (!albums.equals(other.albums))
return false;
if (musicalBands == null) {
if (other.musicalBands!= null)
return false;
} else if (!musicalBands.equals(other.musicalBands))
return false;
if (writerPersons == null) {
if (other.writerPersons!= null)
return false;
} else if (!writerPersons.equals(other.writerPersons))
return false;
return true;
}
	/***/
@Override
	public List<IAlbum> getAlbums(){
		return albums;}
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
	public List<IGenre> getGenres(){
		return genres;}
	/***/
@Override
	public List<IMusicalArtist> getMusicalArtists(){
		return musicalArtists;}
	/***/
@Override
	public List<IBand> getMusicalBands(){
		return musicalBands;}
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
return ISingleThing.RDF_MODEL_NAMESPACE + resourceFactory.get(this);
} else {
final String resourceName = getClass().getSimpleName() + "_" + resourceFactory.size();
resourceFactory.put(this, resourceName);
return ISingleThing.RDF_MODEL_NAMESPACE + resourceName;}
}
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
result = prime * result + ((this.characterOffset == null) ? 0 : this.characterOffset.hashCode());
result = prime * result + ((this.textMention == null) ? 0 : this.textMention.hashCode());
result = prime * result + ((this.characterOnset == null) ? 0 : this.characterOnset.hashCode());
result = prime * result + ((this.musicalArtists == null) ? 0 : this.musicalArtists.hashCode());
result = prime * result + ((this.genres == null) ? 0 : this.genres.hashCode());
result = prime * result + ((this.producerAgents == null) ? 0 : this.producerAgents.hashCode());
result = prime * result + ((this.albums == null) ? 0 : this.albums.hashCode());
result = prime * result + ((this.musicalBands == null) ? 0 : this.musicalBands.hashCode());
result = prime * result + ((this.writerPersons == null) ? 0 : this.writerPersons.hashCode());
return result;}
	/***/
@Override
	public boolean isEmpty(){
		boolean isEmpty = true;
return false;}
	/***/
@Override
	public Single setAlbums(List<IAlbum> albums){
		if(albums==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.albums = albums;
return this;}
	/***/
@Override
	public void setCharacterOnset(Integer onset){
		this.characterOnset = onset;
 this.characterOffset = onset + textMention.length();}
	/***/
@Override
	public Single setGenres(List<IGenre> genres){
		if(genres==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.genres = genres;
return this;}
	/***/
@Override
	public Single setMusicalArtists(List<IMusicalArtist> musicalArtists){
		if(musicalArtists==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.musicalArtists = musicalArtists;
return this;}
	/***/
@Override
	public Single setMusicalBands(List<IBand> bands){
		if(bands==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.musicalBands = bands;
return this;}
	/***/
@Override
	public Single setProducerAgents(List<IAgent> agents){
		if(agents==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.producerAgents = agents;
return this;}
	/***/
@Override
	public Single setWriterPersons(List<IPerson> persons){
		if(persons==null){throw new IllegalArgumentException("Can not set list objects to null.");}this.writerPersons = persons;
return this;}


@Override
public String toString(){
return "Single [individual="+individual+",albums="+albums+",characterOffset="+characterOffset+",characterOnset="+characterOnset+",genres="+genres+",musicalArtists="+musicalArtists+",musicalBands="+musicalBands+",producerAgents="+producerAgents+",serialVersionUID="+serialVersionUID+",textMention="+textMention+",writerPersons="+writerPersons+"]";}


}
