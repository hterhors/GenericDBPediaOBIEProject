package de.hterhors.obie.projects.dbpedia.ie.corpus;

import de.hterhors.obie.core.ontology.AbstractOntologyEnvironment;
import de.hterhors.obie.core.ontology.OntologyInitializer;
import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.ml.tools.BigramCorpusBuilder;
import de.hterhors.obie.projects.dbpedia.environments.dam.DamOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.dam.DamProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.food.FoodOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.food.FoodProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.manga.MangaOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.manga.MangaProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.GenericDBPediaRegExNEL;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDamThing;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilmThing;
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.IFoodThing;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IMangaThing;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingleThing;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructureThing;

public class BigramCorpusCreator {

	public static void main(String[] args) throws Exception {

		BigramCorpusBuilder.overrideCorpusFileIfExists = true;

//		buildDam();

		buildFood();

		buildManga();

		buildFilm();

		buildSingle();

		buildStructure();

	}

	private static void buildStructure() throws Exception {
		AbstractOntologyEnvironment ontologyEnvironment = StructureOntologyEnvironment.getInstance();
		AbstractProjectEnvironment<IArchitecturalStructureThing> projectEnvironment = StructureProjectEnvironment
				.getInstance();
		OntologyInitializer.initializeOntology(ontologyEnvironment);
		new BigramCorpusBuilder(projectEnvironment, ontologyEnvironment,
				GenericDBPediaRegExNEL.getArchitecturalStructureInstance());
	}

	private static void buildSingle() throws Exception {
		AbstractOntologyEnvironment ontologyEnvironment = SingleOntologyEnvironment.getInstance();
		AbstractProjectEnvironment<ISingleThing> projectEnvironment = SingleProjectEnvironment.getInstance();
		OntologyInitializer.initializeOntology(ontologyEnvironment);
		new BigramCorpusBuilder(projectEnvironment, ontologyEnvironment, GenericDBPediaRegExNEL.getSingleInstance());
	}

	private static void buildFilm() throws Exception {
		AbstractOntologyEnvironment ontologyEnvironment = FilmOntologyEnvironment.getInstance();
		AbstractProjectEnvironment<IFilmThing> projectEnvironment = FilmProjectEnvironment.getInstance();
		OntologyInitializer.initializeOntology(ontologyEnvironment);
		new BigramCorpusBuilder(projectEnvironment, ontologyEnvironment, GenericDBPediaRegExNEL.getFilmInstance());
	}

	private static void buildManga() throws Exception {
		AbstractOntologyEnvironment ontologyEnvironment = MangaOntologyEnvironment.getInstance();
		AbstractProjectEnvironment<IMangaThing> projectEnvironment = MangaProjectEnvironment.getInstance();
		OntologyInitializer.initializeOntology(ontologyEnvironment);
		new BigramCorpusBuilder(projectEnvironment, ontologyEnvironment, GenericDBPediaRegExNEL.getMangaInstance());
	}

	private static void buildFood() throws Exception {
		AbstractOntologyEnvironment ontologyEnvironment = FoodOntologyEnvironment.getInstance();
		AbstractProjectEnvironment<IFoodThing> projectEnvironment = FoodProjectEnvironment.getInstance();
		OntologyInitializer.initializeOntology(ontologyEnvironment);
		new BigramCorpusBuilder(projectEnvironment, ontologyEnvironment, GenericDBPediaRegExNEL.getFoodInstance());
	}

	private static void buildDam() throws Exception {
		AbstractOntologyEnvironment ontologyEnvironment = DamOntologyEnvironment.getInstance();
		AbstractProjectEnvironment<IDamThing> projectEnvironment = DamProjectEnvironment.getInstance();
		OntologyInitializer.initializeOntology(ontologyEnvironment);
		new BigramCorpusBuilder(projectEnvironment, ontologyEnvironment, GenericDBPediaRegExNEL.getDamInstance());
	}

}
