package de.hterhors.obie.projects.dbpedia.ie;

import de.hterhors.obie.core.ontology.OntologyInitializer;
import de.hterhors.obie.ml.corpus.BigramCorpusProvider;
import de.hterhors.obie.ml.run.param.RunParameter;
import de.hterhors.obie.ml.run.param.RunParameter.Builder;
import de.hterhors.obie.ml.tools.upperbound.UpperBound;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.parameter.DBPediaParameterQuickAccess;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructure;

/**
 * This class contains example code for analyzing a bigram-corpus given the
 * named entity recognition and linking annotations.
 * 
 * The results of this analysis can be seen as upper bound of what a perfect
 * system can reach relying on the NERL annotations.
 * 
 * @author hterhors
 *
 */
public class ComputeUpperBound {

	public static void main(String[] args) {

		OntologyInitializer.initializeOntology(StructureOntologyEnvironment.getInstance());

		/*
		 * Get some standard parameter.
		 */
		final RunParameter param = getStandardParameter().build();

		final BigramCorpusProvider corpusProvider = BigramCorpusProvider.loadCorpusFromFile(param);

		new UpperBound(param, corpusProvider.getTrainingCorpus());

	}

	/**
	 * Set some standard parameter.
	 * 
	 * @return
	 */
	private static Builder getStandardParameter() {

		Builder paramBuilder = DBPediaParameterQuickAccess.getREParameter(IArchitecturalStructure.class);

		paramBuilder.setProjectEnvironment(StructureProjectEnvironment.getInstance());
		paramBuilder.setOntologyEnvironment(StructureOntologyEnvironment.getInstance());

		paramBuilder.setCorpusDistributor(DBPediaParameterQuickAccess.predefinedDistributor.originDist(1.0F));

		return paramBuilder;
	}
}
//SINGLE
//UpperBound = PRF1 [tp=13774.0, fp=0.0, fn=226.0, getF1()=0.991862893353496, getRecall()=0.9838571428571429, getPrecision()=1.0, getJaccard()=0.9838571428571429]
//Failures:
//class de.hterhors.obie.projects.dbpedia.ontology.single.classes.Album=22
//class de.hterhors.obie.projects.dbpedia.ontology.single.classes.MusicalArtist=57
//class de.hterhors.obie.projects.dbpedia.ontology.single.classes.Genre=38
//class de.hterhors.obie.projects.dbpedia.ontology.single.classes.Band=60
//class de.hterhors.obie.projects.dbpedia.ontology.single.classes.Agent=16
//class de.hterhors.obie.projects.dbpedia.ontology.single.classes.Person=30


//Structure
//UpperBound = PRF1 [tp=2239.0, fp=0.0, fn=41.0, getF1()=0.9909271962823633, getRecall()=0.9820175438596491, getPrecision()=1.0, getJaccard()=0.9820175438596491]
//Failures:
//class de.hterhors.obie.projects.dbpedia.ontology.structure.classes.Place=3
//class de.hterhors.obie.projects.dbpedia.ontology.structure.classes.Architect=8
//class de.hterhors.obie.projects.dbpedia.ontology.structure.classes.ArchitecturalStyle=23
