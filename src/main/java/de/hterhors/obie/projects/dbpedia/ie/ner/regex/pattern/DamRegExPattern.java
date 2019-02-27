package de.hterhors.obie.projects.dbpedia.ie.ner.regex.pattern;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import de.hterhors.obie.core.ontology.AbstractIndividual;
import de.hterhors.obie.ml.ner.regex.BasicRegExPattern;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.dam.StatusInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.generic.YearInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.BuildingStartYear;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.OpeningYear;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.Status;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDamThing;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IMangaThing;

public class DamRegExPattern extends BasicRegExPattern<IDamThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Map<Class<? extends IDamThing>, Set<Pattern>> handMadeClassPattern;
	private final Map<AbstractIndividual, Set<Pattern>> handMadeIndividualPattern;

	/**
	 * Common words that appears very often in many different contexts.
	 */
	private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList("dam","river","dams","rivers"));

	private static final Set<Pattern> YEAR_REG_EXP = new HashSet<>(Arrays.asList(YearInterpreter.PATTERN));
	private static final Set<Pattern> STATUS_REG_EXP = new HashSet<>(Arrays.asList(StatusInterpreter.PATTERN));

	public DamRegExPattern() {
		handMadeClassPattern = new HashMap<Class<? extends IDamThing>, Set<Pattern>>();

		handMadeClassPattern.put(OpeningYear.class, YEAR_REG_EXP);
		handMadeClassPattern.put(BuildingStartYear.class, YEAR_REG_EXP);
		handMadeClassPattern.put(Status.class, STATUS_REG_EXP);

		handMadeIndividualPattern = Collections.emptyMap();
	}

	@Override
	public Set<String> getAdditionalStopWords() {
		return STOP_WORDS;
	}

	@Override
	public int getMinTokenlength() {
		return 3;
	}

	@Override
	public Map<AbstractIndividual, Set<Pattern>> getHandMadePatternForIndividuals() {
		return handMadeIndividualPattern;
	}

	@Override
	public Map<Class<? extends IDamThing>, Set<Pattern>> getHandMadePatternForClasses() {
		return handMadeClassPattern;
	}

}
