package de.hterhors.obie.projects.dbpedia.rawcorpus;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import de.hterhors.dbpedia.obie.corpus.GenericCorpusExtractor;
import de.hterhors.dbpedia.obie.corpus.GenericCorpusExtractor.IInstanceRestrictionFilter;
import de.hterhors.dbpedia.obie.infobox.DBPediaInfoBoxReaderConfig;
import de.hterhors.dbpedia.obie.wikipage.WikiPageReaderConfig;
import de.hterhors.obie.core.ontology.AbstractOntologyEnvironment;
import de.hterhors.obie.core.ontology.annotations.RelationTypeCollection;
import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.ml.utils.ReflectionUtils;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.single.SingleDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.structure.ArchitecturalStructureDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilmThing;
import de.hterhors.obie.projects.dbpedia.ontology.single.classes.Single;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingleThing;
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.ArchitecturalStructure;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructureThing;

public class GenericRawCorpusExtractor<T extends IOBIEThing> {

	public static void main(String[] args) throws Exception {
//		IInterpreter<IDamThing> interpreter = DamInterpreterProvider.getInstance();
//		AbstractOntologyEnvironment ontologyEnvironment = DamOntologyEnvironment.getInstance();
//		Class<? extends IDamThing> mainResourceClass = Dam.class;

//		IInterpreter<IFoodThing> interpreter = FoodInterpreterProvider.getInstance();
//		AbstractOntologyEnvironment ontologyEnvironment = FoodOntologyEnvironment.getInstance();
//		Class<? extends IFoodThing> mainResourceClass = Food.class;
//
//		IDatatypeInterpreter<IMangaThing> interpreter = MangaDatatypeInterpreter.getInstance();
//		AbstractOntologyEnvironment ontologyEnvironment = MangaOntologyEnvironment.getInstance();
//		Class<? extends IMangaThing> mainResourceClass = Manga.class;

//		IDatatypeInterpreter<IFilmThing> interpreter = FilmDatatypeInterpreter.getInstance();
//		AbstractOntologyEnvironment ontologyEnvironment = FilmOntologyEnvironment.getInstance();
//		Class<? extends IFilmThing> mainResourceClass = Film.class;

//		IDatatypeInterpreter<ISingleThing> interpreter = SingleDatatypeInterpreter.getInstance();
//		AbstractOntologyEnvironment ontologyEnvironment = SingleOntologyEnvironment.getInstance();
//		Class<? extends ISingleThing> mainResourceClass = Single.class;

		IDatatypeInterpreter<IArchitecturalStructureThing> interpreter = ArchitecturalStructureDatatypeInterpreter
				.getInstance();
		AbstractOntologyEnvironment ontologyEnvironment = StructureOntologyEnvironment.getInstance();
		Class<? extends IArchitecturalStructureThing> mainResourceClass = ArchitecturalStructure.class;

		new GenericRawCorpusExtractor<IArchitecturalStructureThing>(ontologyEnvironment, interpreter,
				mainResourceClass);
	}

	final private static String directoryPrefix = "/home/hterhors/git/DBPediaOBIECorpusExtractor/";

	public GenericRawCorpusExtractor(AbstractOntologyEnvironment ontologyEnvironment,
			IDatatypeInterpreter<T> interpreter, Class<? extends T> mainResourceClass) throws Exception {

		DBPediaInfoBoxReaderConfig infoBoxConfig = new DBPediaInfoBoxReaderConfig(
				new File(directoryPrefix + "data/infobox/ontology_properties_sorted.nt"),
				new File(directoryPrefix + "data/infobox/properties_index.tsv"), "\t");
		WikiPageReaderConfig wikiPageConfig = new WikiPageReaderConfig(new File(directoryPrefix + "data/en-json"),
				new File(directoryPrefix + "data/en-json/index.tsv"), "\t");

		/*
		 * TODO: define
		 */
		final int MAX_NUMBER_OF_ELEMENTS = 3;

		IInstanceRestrictionFilter instanceRestrictionFilter = new IInstanceRestrictionFilter() {

			@SuppressWarnings("unchecked")
			@Override
			public boolean applyFilter(IOBIEThing thing) {
				for (Field field : ReflectionUtils.getSlots(thing.getClass())) {
					if (field.isAnnotationPresent(RelationTypeCollection.class)) {
						try {
							if (((List<IOBIEThing>) field.get(thing)).size() > MAX_NUMBER_OF_ELEMENTS) {
								return false;
							}
						} catch (Exception e) {
							return false;
						}
					}
				}
				return true;
			}
		};

		final GenericCorpusExtractor<T> c = new GenericCorpusExtractor<>(ontologyEnvironment, interpreter,
				mainResourceClass, infoBoxConfig, wikiPageConfig);

		c.instanceRestrictionFilter = instanceRestrictionFilter;

		c.extractCorpus(new File(
				directoryPrefix + "data/looseSelectionOutput4To6/" + mainResourceClass.getSimpleName() + ".txt"));

		int maxnumberOfInstances = 3000;

		c.distributeInstances(new Random(100L), 80, 20, 20, maxnumberOfInstances);

		c.storeCorpusJavaSerialization(
				new File("corpus/raw_corpus_" + mainResourceClass.getSimpleName().toLowerCase() + "4To6Prop_v"
						+ ontologyEnvironment.getOntologyVersion() + ".bin"),
				mainResourceClass.getSimpleName()
						+ " corpus with 4 To 6 properties. Maximum number of elements in list-type slot = "
						+ MAX_NUMBER_OF_ELEMENTS);
	}

}
