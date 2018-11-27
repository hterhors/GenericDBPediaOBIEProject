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
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.manga.NumberOfVolumesInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.manga.classes.NumberOfVolumes;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IMangaThing;

public class MangaRegExPattern extends BasicRegExPattern<IMangaThing> {

	private Map<Class<? extends IMangaThing>, Set<Pattern>> handMadepattern = null;
	/**
	 * Common words that appears very often in many different contexts.
	 */
	private static final Set<String> MANGA_STOP_WORDS = new HashSet<>(
			Arrays.asList("manga", "comic", "magazine", "novel"));

	private static final Set<Pattern> NUMBER_OF_VOLUMES_REG_EXP = new HashSet<>(
			Arrays.asList(NumberOfVolumesInterpreter.PATTERN));
//	private static final Set<Pattern> PLAYER_NUMBER_YEAR_REG_EXP = new HashSet<>(
//			Arrays.asList(PlayerNumberInterpreter.PATTERN));

	@Override
	public Set<String> getAdditionalStopWords() {
		return MANGA_STOP_WORDS;
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
	public Map<Class<? extends IMangaThing>, Set<Pattern>> getHandMadePatternForClasses() {

		if (handMadepattern != null) {
			return handMadepattern;
		}

		handMadepattern = new HashMap<Class<? extends IMangaThing>, Set<Pattern>>();

		handMadepattern.put(NumberOfVolumes.class, NUMBER_OF_VOLUMES_REG_EXP);
//		handMadepattern.put(DeathYear.class, SoccerPlayerRegExPattern.BIRTH_DEATH_YEAR_REG_EXP);
//		handMadepattern.put(PlayerNumber.class, SoccerPlayerRegExPattern.PLAYER_NUMBER_YEAR_REG_EXP);

		return handMadepattern;
	}

}
