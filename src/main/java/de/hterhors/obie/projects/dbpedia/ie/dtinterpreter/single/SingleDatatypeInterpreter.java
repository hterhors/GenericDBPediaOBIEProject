package de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.single;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpretation;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingleThing;

public class SingleDatatypeInterpreter implements IDatatypeInterpreter<ISingleThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static SingleDatatypeInterpreter instance = null;

	public static SingleDatatypeInterpreter getInstance() {

		if (instance == null)
			instance = new SingleDatatypeInterpreter();

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

		return interpretations;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends ISingleThing> classType, Matcher matcher) {

		IDatatypeInterpretation semantics = null;
		return semantics;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends ISingleThing> classType, final String textMention) {

		if (textMention == null || textMention.isEmpty())
			return null;

		return null;
	}

}
