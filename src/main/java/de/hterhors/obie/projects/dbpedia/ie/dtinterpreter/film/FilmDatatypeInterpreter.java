package de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.film;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpretation;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.ml.dtinterpreter.INumericInterpreter;
import de.hterhors.obie.ml.dtinterpreter.IStringInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.generic.YearInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.BuildingStartYear;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.OpeningYear;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.Status;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDamThing;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilmThing;

public class FilmDatatypeInterpreter implements IDatatypeInterpreter<IFilmThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static FilmDatatypeInterpreter instance = null;

	public static FilmDatatypeInterpreter getInstance() {

		if (instance == null)
			instance = new FilmDatatypeInterpreter();

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
	public IDatatypeInterpretation interpret(Class<? extends IFilmThing> classType, Matcher matcher) {

		IDatatypeInterpretation semantics = null;
		return semantics;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IFilmThing> classType, final String textMention) {

		if (textMention == null || textMention.isEmpty())
			return null;

		return null;
	}

}
