package de.hterhors.obie.projects.dbpedia.ie.baseline;

import de.hterhors.obie.core.evaluation.PRF1;
import de.hterhors.obie.core.ontology.OntologyInitializer;
import de.hterhors.obie.ml.corpus.distributor.FoldCrossCorpusDistributor;
import de.hterhors.obie.ml.run.AbstractRunner;
import de.hterhors.obie.ml.run.DefaultSlotFillingRunner;
import de.hterhors.obie.ml.run.param.RunParameter;
import de.hterhors.obie.ml.run.param.RunParameter.Builder;
import de.hterhors.obie.ml.tools.baseline.HighFrequencyBaseline;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.parameter.DBPediaParameterQuickAccess;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructure;

public class ComputeHighFreqBaseline {

	public static void main(String[] args) throws Exception {
		OntologyInitializer.initializeOntology(StructureOntologyEnvironment.getInstance());

		nFoldCrossValidation();
	}

	private static void nFoldCrossValidation() throws Exception {
		PRF1 mean = new PRF1(0, 0, 0);

		long allTime = System.currentTimeMillis();

		Builder paramBuilder = DBPediaParameterQuickAccess.getREParameter(IArchitecturalStructure.class);

		paramBuilder.setCorpusDistributor(DBPediaParameterQuickAccess.predefinedDistributor.foldCrossDist(1F));

		paramBuilder.setOntologyEnvironment(StructureOntologyEnvironment.getInstance());
		paramBuilder.setProjectEnvironment(StructureProjectEnvironment.getInstance());

		RunParameter param = paramBuilder.build();
		AbstractRunner runner = new DefaultSlotFillingRunner(param);

		while (runner.corpusProvider.nextFold()) {

			System.out.println("#############################");
			System.out.println("New " + ((FoldCrossCorpusDistributor) runner.getParameter().corpusDistributor).n
					+ "-fold cross validation iteration: "
					+ String.valueOf(runner.corpusProvider.getCurrentFoldIndex() + 1));
			long time = System.currentTimeMillis();

			System.out.println("Set test instances to:");
			runner.corpusProvider.getTestCorpus().getInternalInstances().forEach(System.out::println);
			System.out.println("#############################");
			PRF1 prf1 = new HighFrequencyBaseline(param).run(runner.corpusProvider.getTestCorpus());

			mean.add(prf1);
			System.out.println("Time needed: " + (System.currentTimeMillis() - time));

		}
		System.out.println(((FoldCrossCorpusDistributor) runner.getParameter().corpusDistributor).n
				+ " fold cross validation mean: " + mean);
		System.out.println("Time needed: " + (System.currentTimeMillis() - allTime));

	}
}

//Structure
//Time needed: 25
//10 fold cross validation mean: PRF1 [tp=792.0, fp=2327.0, fn=1943.0, getF1()=0.2705842159207379, getRecall()=0.28957952468007314, getPrecision()=0.2539275408784867, getJaccard()=0.15645989727380483]
//Time needed: 6362
