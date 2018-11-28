package de.hterhors.obie.projects.dbpedia.environments.food;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.IFoodThing;

public class FoodProjectEnvironment extends AbstractProjectEnvironment<IFoodThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getRootLogger();

	private static FoodProjectEnvironment instance = new FoodProjectEnvironment();
	/**
	 * The corpus name prefix.
	 */
	final private static String corpusPrefix = "4To6Props";

	private final File rawCorpusFile = new File("src/main/resources/corpus/raw/raw_corpus_food4To6Prop_v1.bin");

	private final File projectCorpusDirectory = new File("src/main/resources/corpus/bigram/");

	public static FoodProjectEnvironment getInstance() {
		log.info("Return instance of " + FoodProjectEnvironment.class.getName());
		return instance;
	}

	@Override
	public Class<? extends IFoodThing> getOntologyThingInterface() {
		return IFoodThing.class;
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
