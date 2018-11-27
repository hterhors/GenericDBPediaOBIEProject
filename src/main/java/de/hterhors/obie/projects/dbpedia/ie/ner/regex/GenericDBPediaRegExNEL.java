package de.hterhors.obie.projects.dbpedia.ie.ner.regex;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.hterhors.obie.core.ontology.AbstractIndividual;
import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.ml.ner.INamedEntitityLinker;
import de.hterhors.obie.ml.ner.regex.AbstractRegExNERL;
import de.hterhors.obie.ml.ner.regex.BasicRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.dam.DamDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.film.FilmDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.food.FoodDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.manga.MangaDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.single.SingleDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.structure.ArchitecturalStructureDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern.ArchitecturalStructureRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern.DamRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern.FilmRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern.FoodRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern.MangaRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern.SingleRegExPattern;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDam;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDamThing;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilm;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilmThing;
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.IFood;
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.IFoodThing;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IManga;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IMangaThing;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingle;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingleThing;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructure;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructureThing;

public class GenericDBPediaRegExNEL<T extends IOBIEThing> extends AbstractRegExNERL<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int MIN_NER_LENGTH = 2;

	public static Logger log = LogManager.getFormatterLogger(GenericDBPediaRegExNEL.class.getSimpleName());

	public static INamedEntitityLinker getMangaInstance() {
		return new GenericDBPediaRegExNEL<IMangaThing>(IManga.class, new MangaRegExPattern(),
				MangaDatatypeInterpreter.getInstance());
	}

	public static INamedEntitityLinker getFoodInstance() {
		return new GenericDBPediaRegExNEL<IFoodThing>(IFood.class, new FoodRegExPattern(),
				FoodDatatypeInterpreter.getInstance());
	}

	public static INamedEntitityLinker getDamInstance() {
		return new GenericDBPediaRegExNEL<IDamThing>(IDam.class, new DamRegExPattern(),
				DamDatatypeInterpreter.getInstance());
	}

	public static INamedEntitityLinker getFilmInstance() {
		return new GenericDBPediaRegExNEL<IFilmThing>(IFilm.class, new FilmRegExPattern(),
				FilmDatatypeInterpreter.getInstance());
	}

	public static INamedEntitityLinker getSingleInstance() {
		return new GenericDBPediaRegExNEL<ISingleThing>(ISingle.class, new SingleRegExPattern(),
				SingleDatatypeInterpreter.getInstance());
	}

	public static INamedEntitityLinker getArchitecturalStructureInstance() {
		return new GenericDBPediaRegExNEL<IArchitecturalStructureThing>(IArchitecturalStructure.class,
				new ArchitecturalStructureRegExPattern(), ArchitecturalStructureDatatypeInterpreter.getInstance());
	}

	private GenericDBPediaRegExNEL(Class<? extends T> rootClass, BasicRegExPattern<T> basicRegExPattern,
			IDatatypeInterpreter<T> interpreter) {
		this(new HashSet<>(Arrays.asList(rootClass)), basicRegExPattern, interpreter);
	}

	public GenericDBPediaRegExNEL(Set<Class<? extends T>> rootClasses, BasicRegExPattern<T> basicRegExPattern,
			IDatatypeInterpreter<T> interpreter) {
		super(rootClasses, basicRegExPattern, interpreter, MIN_NER_LENGTH);
	}

	@Override
	protected Map<Class<? extends T>, Set<Pattern>> getAdditionalPatternForClasses(Class<? extends T> rootClassType) {
		return Collections.emptyMap();
	}

	@Override
	protected Map<AbstractIndividual, Set<Pattern>> getAdditionalPatternForIndividuals(
			Class<? extends T> rootClassType) {
		return Collections.emptyMap();
	}

}
