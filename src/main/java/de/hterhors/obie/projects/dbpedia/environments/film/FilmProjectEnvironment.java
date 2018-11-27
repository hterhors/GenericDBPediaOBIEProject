package de.hterhors.obie.projects.dbpedia.environments.film;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilmThing;

public class FilmProjectEnvironment extends AbstractProjectEnvironment<IFilmThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getRootLogger();

	private static FilmProjectEnvironment instance = new FilmProjectEnvironment();
	/**
	 * The corpus name prefix.
	 */
	final private static String corpusPrefix = "4To6Props";

	private final File rawCorpusFile = new File("corpus/raw_corpus_film4To6Prop_v1.bin");

	private final File projectCorpusDirectory = new File("bigram/corpus/");

	public static FilmProjectEnvironment getInstance() {
		log.info("Return instance of " + FilmProjectEnvironment.class.getName());
		return instance;
	}

	@Override
	public Class<? extends IFilmThing> getOntologyThingInterface() {
		return IFilmThing.class;
	}

	@Override
	public File getRawCorpusFile() {
		return rawCorpusFile;
	}

	@Override
	public File getBigramCorpusFileDirectory() {
		return projectCorpusDirectory;
	}

	@Override
	public String getCorpusPrefix() {
		return corpusPrefix;
	}

}
