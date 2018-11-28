package de.hterhors.obie.projects.dbpedia.environments.food;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import de.hterhors.obie.core.ontology.AbstractOntologyEnvironment;
import de.hterhors.obie.core.owlreader.IClassFilter;
import de.hterhors.obie.core.owlreader.container.OntologyClass;

public class FoodOntologyEnvironment extends AbstractOntologyEnvironment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static FoodOntologyEnvironment instance = new FoodOntologyEnvironment();

	private final String ontologyBasePackage = "de.hterhors.obie.projects.dbpedia.ontology.food.";

	final public static int version = 1;
	public static final File ONTOLOGY_FILE = new File("src/main/resources/owl/food/Food_autoGen_v" + version + ".owl");
	public static final String DEFAULT_DATA_NAMESPACE = "http://dbpedia/food/";
	public static final String ONTOLOGY_NAME = "Food";

	@Override
	public int getOntologyVersion() {
		return version;
	}

	@Override
	public String getDataNameSpace() {
		return DEFAULT_DATA_NAMESPACE;
	}

	@Override
	public File getOntologyFile() {
		return ONTOLOGY_FILE;
	}

	public static FoodOntologyEnvironment getInstance() {
		return instance;
	}

	@Override
	public String getOntologyName() {
		return ONTOLOGY_NAME;
	}

	@Override
	public IClassFilter getOwlClassFilter() {
		return new IClassFilter() {
			@Override
			public boolean matchesCondition(OntologyClass ocd) {
				return true;
			}
		};
	}

	@Override
	public Set<String> getCollectiveClasses() {
		return Collections.emptySet();
	}

	@Override
	public String getBasePackage() {
		return ontologyBasePackage;
	}

	@Override
	public String getAdditionalPrefixes() {
		return "";
	}

	@Override
	public List<String> getAdditionalPropertyNames() {
		return Collections.emptyList();
	}

}
