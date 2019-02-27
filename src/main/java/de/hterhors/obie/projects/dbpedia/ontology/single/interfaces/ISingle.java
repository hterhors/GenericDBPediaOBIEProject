package de.hterhors.obie.projects.dbpedia.ontology.single.interfaces;

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
import de.hterhors.obie.projects.dbpedia.ontology.single.classes.*;
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

@ImplementationClass(get=Single.class)

@AssignableSubInterfaces(get={})
 public interface ISingle
 extends ISingleThing{

/***/
	public ISingle addAlbum(IAlbum album);


/***/
	public ISingle addGenre(IGenre genre);


/***/
	public ISingle addMusicalArtist(IMusicalArtist musicalArtist);


/***/
	public ISingle addMusicalBand(IBand band);


/***/
	public ISingle addProducerAgent(IAgent agent);


/***/
	public ISingle addWriterPerson(IPerson person);


/***/
	public List<IAlbum> getAlbums();


/***/
	public List<IGenre> getGenres();


/***/
	public List<IMusicalArtist> getMusicalArtists();


/***/
	public List<IBand> getMusicalBands();


/***/
	public List<IAgent> getProducerAgents();


/***/
	public List<IPerson> getWriterPersons();


/***/
	public ISingle setAlbums(List<IAlbum> albums);


/***/
	public ISingle setGenres(List<IGenre> genres);


/***/
	public ISingle setMusicalArtists(List<IMusicalArtist> musicalArtists);


/***/
	public ISingle setMusicalBands(List<IBand> musicalBands);


/***/
	public ISingle setProducerAgents(List<IAgent> producerAgents);


/***/
	public ISingle setWriterPersons(List<IPerson> writerPersons);


}
