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
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.generic.YearInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.OpeningYear;
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.YearOfConstruction;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructureThing;

public class ArchitecturalStructureRegExPattern extends BasicRegExPattern<IArchitecturalStructureThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Map<Class<? extends IArchitecturalStructureThing>, Set<Pattern>> handMadeClassPattern;
	private final Map<AbstractIndividual, Set<Pattern>> handMadeIndividualPattern;

	/**
	 * Common words that appears very often in many different contexts.
	 */
	private static final Set<String> STOP_WORDS = new HashSet<>(
			Arrays.asList("structure", "house", "courthouse", "architecture", "building", "film"));

	private static final Set<Pattern> YEAR_REG_EXP = new HashSet<>(Arrays.asList(YearInterpreter.PATTERN));

	public ArchitecturalStructureRegExPattern() {
		handMadeClassPattern = new HashMap<Class<? extends IArchitecturalStructureThing>, Set<Pattern>>();

		handMadeClassPattern.put(OpeningYear.class, YEAR_REG_EXP);
		handMadeClassPattern.put(YearOfConstruction.class, YEAR_REG_EXP);

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
	public Map<Class<? extends IArchitecturalStructureThing>, Set<Pattern>> getHandMadePatternForClasses() {
		return handMadeClassPattern;
	}

}
