package de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpretation;
import de.hterhors.obie.ml.dtinterpreter.IDatatypeInterpreter;
import de.hterhors.obie.ml.dtinterpreter.INumericInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.dam.StatusInterpreter;
import de.hterhors.obie.projects.dbpedia.ie.dtinterpreter.generic.YearInterpreter;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.BuildingStartYear;
import de.hterhors.obie.projects.dbpedia.ontology.dam.classes.Status;
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.OpeningYear;
import de.hterhors.obie.projects.dbpedia.ontology.structure.classes.YearOfConstruction;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructureThing;

public class ArchitecturalStructureDatatypeInterpreter implements IDatatypeInterpreter<IArchitecturalStructureThing> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ArchitecturalStructureDatatypeInterpreter instance = null;

	public static ArchitecturalStructureDatatypeInterpreter getInstance() {

		if (instance == null)
			instance = new ArchitecturalStructureDatatypeInterpreter();

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
		INumericInterpreter semantics2 = new YearInterpreter.Builder().interprete(textMention).build().normalize();
		if (semantics2.exists()) {
			interpretations.add(semantics2);
		}
		return interpretations;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IArchitecturalStructureThing> classType, Matcher matcher) {
		IDatatypeInterpretation semantics = null;
		if (OpeningYear.class == classType || YearOfConstruction.class == classType) {
			semantics = new YearInterpreter.Builder().interprete(matcher.group()).build().normalize();
		} else {
			throw new IllegalArgumentException("Unknown data type for: " + classType.getSimpleName());
		}
		return semantics;
	}

	@Override
	public IDatatypeInterpretation interpret(Class<? extends IArchitecturalStructureThing> classType,
			final String textMention) {

		if (textMention == null || textMention.isEmpty())
			return null;

		final IDatatypeInterpretation semantics;
		if (OpeningYear.class == classType || YearOfConstruction.class == classType) {
			semantics = new YearInterpreter.Builder().interprete(textMention).build().normalize();
		} else {
			throw new IllegalArgumentException(
					"Unknown data type for(" + textMention + "): " + classType.getSimpleName());
		}
		return semantics;
	}

}
