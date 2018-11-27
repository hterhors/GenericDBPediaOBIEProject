package de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.manga;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpretation;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.ml.dtinterpreter.INumericInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.manga.classes.NumberOfVolumes;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IMangaThing;

public class MangaDatatypeInterpreter implements IDatatypeInterpreter<IMangaThing> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MangaDatatypeInterpreter instance = null;

	public static MangaDatatypeInterpreter getInstance() {

		if (instance == null)
			instance = new MangaDatatypeInterpreter();

		return instance;
	}

	@Override
	public List<IDatatypeInterpretation> getPossibleInterpretations(String textMention) {
		if (textMention == null || textMention.isEmpty())
			return null;
		/**
		 * TODO: Add more.
		 */
		List<IDatatypeInterpretation> interpretations = new ArrayList<>();

//		INumericInterpreter semantics1 = new BirthDeathYearInterpreter.Builder().interprete(textMention).build()
//				.normalize();
//		if (semantics1.exists()) {
//			interpretations.add(semantics1);
//		}
		INumericInterpreter semantics2 = new NumberOfVolumesInterpreter.Builder().interprete(textMention).build()
				.normalize();
		if (semantics2.exists()) {
			interpretations.add(semantics2);
		}

		return interpretations;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IMangaThing> classType, Matcher matcher) {

		IDatatypeInterpretation semantics = null;
		if (NumberOfVolumes.class == classType) {
			semantics = new NumberOfVolumesInterpreter.Builder().interprete(matcher.group()).build().normalize();
//		} else if (PlayerNumber.class == classType) {
//			semantics = new PlayerNumberInterpreter.Builder().interprete(matcher.group()).build().normalize();
		} else {
			throw new IllegalArgumentException("Unknown data type for: " + classType.getSimpleName());
		}

		if (semantics != null && semantics.exists()) {
			return semantics;
		}
		return semantics;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IMangaThing> classType, final String textMention) {

		final IDatatypeInterpretation semantics;
		if (textMention == null || textMention.isEmpty())
			return null;

//		if (BirthYear.class == classType) {
//			semantics = new BirthDeathYearInterpreter.Builder().interprete(textMention).build().normalize();
//		} else {
		throw new IllegalArgumentException("Unknown data type for(" + textMention + "): " + classType.getSimpleName());
//		}
//		return semantics;
	}

}
