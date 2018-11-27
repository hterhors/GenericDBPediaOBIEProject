package de.hterhors.obie.projects.dbpedia.environments.dam;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDamThing;

public class DamProjectEnvironment extends AbstractProjectEnvironment<IDamThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getRootLogger();

	private static DamProjectEnvironment instance = new DamProjectEnvironment();
	/**
	 * The corpus name prefix.
	 */
	final private static String corpusPrefix = "4To6Props";

	private final File rawCorpusFile = new File("corpus/raw_corpus_dam4To6Prop_v1.bin");

	private final File projectCorpusDirectory = new File("bigram/corpus/");

	public static DamProjectEnvironment getInstance() {
		log.info("Return instance of " + DamProjectEnvironment.class.getName());
		return instance;
	}

	@Override
	public Class<? extends IDamThing> getOntologyThingInterface() {
		return IDamThing.class;
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
