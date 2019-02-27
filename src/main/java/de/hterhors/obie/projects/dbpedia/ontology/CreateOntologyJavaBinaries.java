package de.hterhors.obie.projects.dbpedia.ontology;

import de.hterhors.obie.projects.dbpedia.environments.single.SingleOntologyEnvironment;
import de.hterhors.obie.tools.owl2javabin.OWLToJavaBinaries;

public class CreateOntologyJavaBinaries {

	public static void main(String[] args) throws Exception {

//		OWLToJavaBinaries builder = new OWLToJavaBinaries(MangaOntologyEnvironment.getInstance());
//		OWLToJavaBinaries builder = new OWLToJavaBinaries(DamOntologyEnvironment.getInstance());
//		OWLToJavaBinaries builder = new OWLToJavaBinaries(FoodOntologyEnvironment.getInstance());
//		OWLToJavaBinaries builder = new OWLToJavaBinaries(FilmOntologyEnvironment.getInstance());
//		OWLToJavaBinaries builder = new OWLToJavaBinaries(StructureOntologyEnvironment.getInstance());
		OWLToJavaBinaries builder = new OWLToJavaBinaries(SingleOntologyEnvironment.getInstance());

		builder.buildAndWriteClasses();

		builder.buildAndWriteInterfaces();

	}

}
