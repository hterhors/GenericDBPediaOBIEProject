package de.hterhors.obie.projects.dbpedia.environments.single;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingleThing;

public class SingleProjectEnvironment extends AbstractProjectEnvironment<ISingleThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getRootLogger();

	private static SingleProjectEnvironment instance = new SingleProjectEnvironment();
	/**
	 * The corpus name prefix.
	 */
	final private static String corpusPrefix = "4To6Props";

	private final File rawCorpusFile = new File("corpus/raw/raw_corpus_single4To6Prop_v1.bin");

	private final File projectCorpusDirectory = new File("corpus/bigram/");

	public static SingleProjectEnvironment getInstance() {
		log.info("Return instance of " + SingleProjectEnvironment.class.getName());
		return instance;
	}

	@Override
	public Class<? extends ISingleThing> getOntologyThingInterface() {
		return ISingleThing.class;
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
