package de.hterhors.obie.projects.dbpedia.ontology.film.interfaces;

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
import de.hterhors.obie.projects.dbpedia.ontology.film.classes.*;
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

@ImplementationClass(get=Film.class)

@AssignableSubInterfaces(get={})
 public interface IFilm
 extends IFilmThing{

/***/
	public IFilm addDirectorPerson(IPerson person);


/***/
	public IFilm addDistributorOrganisation(IOrganisation organisation);


/***/
	public IFilm addMusicComposerMusicalArtist(IMusicalArtist musicalArtist);


/***/
	public IFilm addProducerAgent(IAgent agent);


/***/
	public IFilm addStarringActor(IActor actor);


/***/
	public IFilm addWriterPerson(IPerson person);


/***/
	public List<IPerson> getDirectorPersons();


/***/
	public List<IOrganisation> getDistributorOrganisations();


/***/
	public List<IMusicalArtist> getMusicComposerMusicalArtists();


/***/
	public List<IAgent> getProducerAgents();


/***/
	public List<IActor> getStarringActors();


/***/
	public List<IPerson> getWriterPersons();


/***/
	public IFilm setDirectorPersons(List<IPerson> directorPersons);


/***/
	public IFilm setDistributorOrganisations(List<IOrganisation> distributorOrganisations);


/***/
	public IFilm setMusicComposerMusicalArtists(List<IMusicalArtist> musicComposerMusicalArtists);


/***/
	public IFilm setProducerAgents(List<IAgent> producerAgents);


/***/
	public IFilm setStarringActors(List<IActor> starringActors);


/***/
	public IFilm setWriterPersons(List<IPerson> writerPersons);


}
