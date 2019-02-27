package de.hterhors.obie.projects.dbpedia.ie.baseline;

import java.util.Random;

import de.hterhors.obie.core.evaluation.PRF1;
import de.hterhors.obie.core.ontology.OntologyInitializer;
import de.hterhors.obie.ml.corpus.distributor.FoldCrossCorpusDistributor;
import de.hterhors.obie.ml.run.AbstractRunner;
import de.hterhors.obie.ml.run.DefaultSlotFillingRunner;
import de.hterhors.obie.ml.run.param.RunParameter;
import de.hterhors.obie.ml.run.param.RunParameter.Builder;
import de.hterhors.obie.ml.tools.baseline.RandomBaseline;
import de.hterhors.obie.projects.dbpedia.environments.dam.DamOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.dam.DamProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.parameter.DBPediaParameterQuickAccess;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDam;

public class ComputeRandomBaseline {

	public static void main(String[] args) throws Exception {

		OntologyInitializer.initializeOntology(DamOntologyEnvironment.getInstance());

		nFoldCrossValidation();
	}

	private static void nFoldCrossValidation() throws Exception {
		PRF1 mean = new PRF1(0, 0, 0);

		long allTime = System.currentTimeMillis();

		Builder paramBuilder = DBPediaParameterQuickAccess.getREParameter(IDam.class);

		paramBuilder.setCorpusDistributor(
				new FoldCrossCorpusDistributor.Builder().setN(10).setSeed(12345L).setCorpusSizeFraction(1F).build());

		paramBuilder.setOntologyEnvironment(DamOntologyEnvironment.getInstance());
		paramBuilder.setProjectEnvironment(DamProjectEnvironment.getInstance());

		RunParameter param = paramBuilder.build();
		AbstractRunner runner = new DefaultSlotFillingRunner(param);

		final long initSeed = 200L;
		Random r = new Random(initSeed);
//		Random r = new Random();
		while (runner.corpusProvider.nextFold()) {

			long seed = r.nextLong();

			System.out.println("#############################");
			System.out.println("New " + ((FoldCrossCorpusDistributor) runner.getParameter().corpusDistributor).n
					+ "-fold cross validation iteration: "
					+ String.valueOf(runner.corpusProvider.getCurrentFoldIndex() + 1));
			long time = System.currentTimeMillis();

//			System.out.println("Set test instances to:");
//			runner.corpusProvider.getTestCorpus().getInternalInstances().forEach(System.out::println);
			System.out.println("#############################");
			PRF1 prf1 = new RandomBaseline(param, seed).run(runner.corpusProvider.getFullCorpus());

			mean.add(prf1);
			System.out.println("Time needed: " + (System.currentTimeMillis() - time));

		}
		System.out.println(((FoldCrossCorpusDistributor) runner.getParameter().corpusDistributor).n
				+ " fold cross validation mean: ");
		System.out.println("p: " + mean.getPrecision() + "\tr: " + mean.getRecall() + "\tf1: " + mean.getF1());
		System.out.println("Time needed: " + (System.currentTimeMillis() - allTime));

	}
}

//Structure
//Time needed: 22
//10 fold cross validation mean: 
//p: 0.18091779465170024	r: 0.19599427753934193	f1: 0.18815450643776827
//Time needed: 6729
