package de.hterhors.obie.projects.dbpedia.environments.structure;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructureThing;

public class StructureProjectEnvironment extends AbstractProjectEnvironment<IArchitecturalStructureThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected static Logger log = LogManager.getRootLogger();

	private static StructureProjectEnvironment instance = new StructureProjectEnvironment();
	/**
	 * The corpus name prefix.
	 */
	final private static String corpusPrefix = "4To6Props";

	private final File rawCorpusFile = new File("src/main/resources/corpus/raw/raw_corpus_architecturalstructure4To6Prop_v3.bin");

	private final File projectCorpusDirectory = new File("src/main/resources/corpus/bigram/");

	public static StructureProjectEnvironment getInstance() {
		log.info("Return instance of " + StructureProjectEnvironment.class.getName());
		return instance;
	}

	@Override
	public Class<? extends IArchitecturalStructureThing> getOntologyThingInterface() {
		return IArchitecturalStructureThing.class;
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
