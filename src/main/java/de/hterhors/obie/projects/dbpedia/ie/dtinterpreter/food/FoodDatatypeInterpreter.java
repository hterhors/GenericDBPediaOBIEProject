package de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.food;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import de.hterhors.obie.core.ontology.interfaces.IDatatype;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpretation;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.food.interfaces.IFoodThing;

public class FoodDatatypeInterpreter implements IDatatypeInterpreter<IFoodThing> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static FoodDatatypeInterpreter instance = null;

	public static FoodDatatypeInterpreter getInstance() {

		if (instance == null)
			instance = new FoodDatatypeInterpreter();

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

//		IStringInterpreter semantics1 = new StatusInterpreter.Builder().interprete(textMention).build().normalize();
//		if (semantics1.exists()) {
//			interpretations.add(semantics1);
//		}
//		INumericInterpreter semantics2 = new YearInterpreter.Builder().interprete(textMention).build().normalize();
//		if (semantics2.exists()) {
//			interpretations.add(semantics2);
//		}

		return interpretations;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IFoodThing> classType, Matcher matcher) {

		IDatatypeInterpretation semantics = null;
//		if (OpeningYear.class == classType || BuildingStartYear.class == classType) {
//			semantics = new YearInterpreter.Builder().interprete(matcher.group()).build().normalize();
//		} else if (Status.class == classType) {
//			semantics = new StatusInterpreter.Builder().interprete(matcher.group()).build().normalize();
//		} else {
		throw new IllegalArgumentException("Unknown data type for: " + classType.getSimpleName());
//		}
//
//		if (semantics != null && semantics.exists()) {
//			return semantics;
//		}
//		return semantics;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IFoodThing> classType, final String textMention) {

		final IDatatypeInterpretation semantics;
		if (textMention == null || textMention.isEmpty())
			return null;

//		if (OpeningYear.class == classType || BuildingStartYear.class == classType) {
//			semantics = new YearInterpreter.Builder().interprete(textMention).build().normalize();
//		} else if (Status.class == classType) {
//			semantics = new StatusInterpreter.Builder().interprete(textMention).build().normalize();
//		} else {
		throw new IllegalArgumentException("Unknown data type for(" + textMention + "): " + classType.getSimpleName());
//		}
//		return semantics;
	}

}
