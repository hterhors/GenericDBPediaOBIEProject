package de.hterhors.obie.projects.dbpedia.examples;

import java.util.List;

import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import de.hterhors.obie.core.tools.corpus.OBIECorpus;
import de.hterhors.obie.core.tools.corpus.OBIECorpus.Instance;
import de.hterhors.obie.ml.utils.OBIEClassFormatter;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.ArchitecturalStructure;

/**
 * This class contains example code for loading the raw corpus file and printing
 * the first instances. This corpus is NOT used in the ML framework. Before
 * this, we need to convert the raw corpus into a bigram corpus using the
 * BigramCorpusCreator.
 * 
 * Doing this the corpus is enriched with information provided by a specified
 * Named Entity Recognition and Linking Tool.
 * 
 * @author hterhors
 *
 * @See BigramCorpusCreator
 */
public class PrintRawCorpus {

	public static void main(String[] args) {

		/**
		 * Load the raw corpus from file system. This corpus contains only the document
		 * content and annotations of the template but NO named entity recognition and
		 * linking annotations!
		 */
//		final OBIECorpus rawCorpus = OBIECorpus
//				.readRawCorpusData(DamProjectEnvironment.getInstance().getRawCorpusFile());
//		final OBIECorpus rawCorpus = OBIECorpus
//				.readRawCorpusData(SingleProjectEnvironment.getInstance().getRawCorpusFile());
//		final OBIECorpus rawCorpus = OBIECorpus
//				.readRawCorpusData(StructureProjectEnvironment.getInstance().getRawCorpusFile());
		final OBIECorpus rawCorpus = OBIECorpus
				.readRawCorpusData(FilmProjectEnvironment.getInstance().getRawCorpusFile());

//		System.out.println(rawCorpus.getInstances().values().size());
		int length = 0;
//		System.exit(1);

		for (Instance instance : rawCorpus.getInstances().values()) {
			length += instance.content.length();

			System.out.println("________" + instance.name + "________");
			System.out.println(instance.content);
			System.out.println("____________Template Annotation(s)____________");

			for (List<IOBIEThing> templateAnnotations : instance.annotations.values()) {
				for (IOBIEThing templateAnnotation : templateAnnotations) {

					System.out.println(OBIEClassFormatter.format(templateAnnotation));

					if (templateAnnotation instanceof ArchitecturalStructure) {
//				
//						IDam d = (IDam) templateAnnotation;
//
//					if (d.getStatus() != null) {
//						System.out.println(d.getStatus());
//					}
					}

				}

			}
		}
		System.out.println("Average length = " + (length / rawCorpus.getInstances().size()));

	}
}
