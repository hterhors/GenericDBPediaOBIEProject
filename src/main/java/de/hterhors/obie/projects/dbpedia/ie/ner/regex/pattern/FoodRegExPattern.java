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
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.IFoodThing;

public class FoodRegExPattern extends BasicRegExPattern<IFoodThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<Class<? extends IFoodThing>, Set<Pattern>> handMadepattern = null;
	/**
	 * Common words that appears very often in many different contexts.
	 */
	private static final Set<String> STOP_WORDS = new HashSet<>(
			Arrays.asList("food", "cuisine", "united", "people", "states"));

//	private static final Set<Pattern> YEAR_REG_EXP = new HashSet<>(Arrays.asList(YearInterpreter.PATTERN));
//	private static final Set<Pattern> STATUS_REG_EXP = new HashSet<>(Arrays.asList(StatusInterpreter.PATTERN));

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
		return Collections.emptyMap();
	}

	@Override
	public Map<Class<? extends IFoodThing>, Set<Pattern>> getHandMadePatternForClasses() {

		if (handMadepattern != null) {
			return handMadepattern;
		}

		handMadepattern = new HashMap<Class<? extends IFoodThing>, Set<Pattern>>();

//		handMadepattern.put(OpeningYear.class, YEAR_REG_EXP);
//		handMadepattern.put(BuildingStartYear.class, YEAR_REG_EXP);
//		handMadepattern.put(Status.class, STATUS_REG_EXP);

		return handMadepattern;
	}

}
