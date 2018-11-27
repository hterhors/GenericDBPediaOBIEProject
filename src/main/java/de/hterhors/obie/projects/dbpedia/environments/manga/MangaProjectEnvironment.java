package de.hterhors.obie.projects.dbpedia.environments.manga;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IMangaThing;

public class MangaProjectEnvironment extends AbstractProjectEnvironment<IMangaThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getRootLogger();

	private static MangaProjectEnvironment instance = new MangaProjectEnvironment();
	/**
	 * The corpus name prefix.
	 */
	final private static String corpusPrefix = "4To6Props";

	private final File rawCorpusFile = new File("corpus/raw_corpus_manga4To6Prop_v2.bin");

	private final File projectCorpusDirectory = new File("bigram/corpus/");

	public static MangaProjectEnvironment getInstance() {
		log.info("Return instance of " + MangaProjectEnvironment.class.getName());
		return instance;
	}

	@Override
	public Class<? extends IMangaThing> getOntologyThingInterface() {
		return IMangaThing.class;
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
