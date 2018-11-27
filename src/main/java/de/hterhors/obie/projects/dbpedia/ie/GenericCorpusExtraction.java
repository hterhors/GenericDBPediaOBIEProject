package de.hterhors.obie.projects.dbpedia.ie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import corpus.SampledInstance;
import de.hterhors.obie.core.evaluation.PRF1;
import de.hterhors.obie.core.ontology.AbstractOntologyEnvironment;
import de.hterhors.obie.core.ontology.OntologyInitializer;
import de.hterhors.obie.core.ontology.interfaces.IOBIEThing;
import de.hterhors.obie.core.projects.AbstractProjectEnvironment;
import de.hterhors.obie.ml.activelearning.FullDocumentAtomicChangeEntropyRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentEntropyRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentLengthRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentMarginBasedRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentModelScoreRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentObjectiveScoreRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentRandFillerRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentRandomRanker;
import de.hterhors.obie.ml.activelearning.FullDocumentVarianceRanker;
import de.hterhors.obie.ml.activelearning.IActiveLearningDocumentRanker;
import de.hterhors.obie.ml.corpus.BigramInternalCorpus;
import de.hterhors.obie.ml.corpus.distributor.AbstractCorpusDistributor;
import de.hterhors.obie.ml.corpus.distributor.ActiveLearningDistributor;
import de.hterhors.obie.ml.corpus.distributor.ActiveLearningDistributor.Builder.EMode;
import de.hterhors.obie.ml.run.AbstractRunner;
import de.hterhors.obie.ml.run.StandardRERunner;
import de.hterhors.obie.ml.run.eval.EvaluatePrediction;
import de.hterhors.obie.ml.run.param.RunParameter;
import de.hterhors.obie.ml.run.param.RunParameter.Builder;
import de.hterhors.obie.ml.templates.AbstractOBIETemplate;
import de.hterhors.obie.ml.templates.CooccurrenceTemplate;
import de.hterhors.obie.ml.templates.FrequencyTemplate;
import de.hterhors.obie.ml.templates.GenericMainTemplatePriorTemplate;
import de.hterhors.obie.ml.templates.InBetweenContextTemplate;
import de.hterhors.obie.ml.templates.InterTokenTemplate;
import de.hterhors.obie.ml.templates.LocalTemplate;
import de.hterhors.obie.ml.templates.SlotIsFilledTemplate;
import de.hterhors.obie.ml.templates.TokenContextTemplate;
import de.hterhors.obie.ml.variables.InstanceTemplateAnnotations;
import de.hterhors.obie.ml.variables.OBIEInstance;
import de.hterhors.obie.ml.variables.OBIEState;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.GenericDBPediaRegExNEL;
import de.hterhors.obie.projects.dbpedia.ie.parameter.DBPediaParameterQuickAccess;
import de.hterhors.obie.projects.dbpedia.ontology.film.interfaces.IFilm;
import de.hterhors.obie.projects.dbpedia.ontology.manga.interfaces.IManga;
import de.hterhors.obie.projects.dbpedia.ontology.single.interfaces.ISingle;
import de.hterhors.obie.projects.dbpedia.ontology.structure.interfaces.IArchitecturalStructure;
import learning.Trainer;

/**
 * 
 * Read README.md for more and detailed information.
 * 
 *
 * Preferred VM run-parameter: -Xmx12g -XX:+UseG1GC -XX:+UseStringDeduplication
 *
 * 
 * -XX:+PrintStringDeduplicationStatistics
 * 
 * @author hterhors
 *
 */
public class GenericCorpusExtraction {

	private static final String DEFAULT_ACTIVE_LEARNING_STRATEGY = "random";
	private static final String DEFAULT_ACTIVE_LEARNING_SEED = "200";
	private static final String DEFAULT_RESULT_FILE_NAME = "tmpResultFile";

	protected static Logger log = LogManager.getRootLogger();

	public static void main(String[] args) throws Exception {

		if (args == null || args.length == 0)
//			args = new String[] { "varianceResults", "variance", "20", "100", "Single"  };
			args = new String[] { "randomResults", "random", "20", "100", "ArchitecturalStructure" };
//			args = new String[] { "lengthResults", "length", "20", "100", "Single"  };
//			args = new String[] { "rndFillerResults", "rndFiller", "20", "100", "Single"  };
//			args = new String[] { "entropyResults", "entropy", "20", "100", "Single"  };
//			args = new String[] { "entropyAtomicResults", "entropyAtomic", "20", "100", "Single"  };
//			args = new String[] { "modelResults", "model", "20", "100", "Single"  };
//			args = new String[] { "marginResults", "margin", "20", "100", "ArchitecturalStructure" };
//			args = new String[] { "objectiveResults", "objective", "20", "100", "Single"  };

		log.info("1) argument: file to store results");
		log.info(
				"2) argument: mode of active learning, \"random\"(default), \"entropy\", \"entropyAtomic\", \"objective\", \"model\", \"margin\", \"length\" or \"variance\"");
		log.info("3) argument: Random inital seed");
		log.info("4) argument: Number of N-best documents for entropy");
		log.info("5) argument: corpus one of \"Film\",\"ArchitecturalStructure\",\"Single\"");

		final File printResults = new File(args.length == 0 ? DEFAULT_RESULT_FILE_NAME : args[0]);
		final String acMode = args.length < 2 ? DEFAULT_ACTIVE_LEARNING_STRATEGY : args[1];
		final long seed = Long.parseLong(args.length < 3 ? DEFAULT_ACTIVE_LEARNING_SEED : args[2]);

		if (args.length >= 4) {
			FullDocumentEntropyRanker.N = Integer.parseInt(args[3]);
		}
		final ECorpus corpusType = ECorpus.valueOf(args[4]);

		log.info("Store results into: " + printResults);
		log.info("Active Learning Modus: " + acMode);

		if (printResults.getParentFile() != null && !printResults.getParentFile().exists()) {
			log.error("Parent dir does not exist: " + printResults.getParentFile().getCanonicalPath());
			System.exit(1);
		}

		new GenericCorpusExtraction(acMode, printResults, seed, corpusType);

	}

	enum ECorpus {
		Film, ArchitecturalStructure, Single;
	}

	/**
	 * The runID. This serves as an identifier for locating and saving the model. If
	 * anything was changed during the development the runID should be reset.
	 */
//	private static String runID = "randomRun280376657";
	private static String runID = "randomRun" + new Random().nextInt();

	/**
	 * The project environment.
	 */
	private final AbstractProjectEnvironment<?> projectEnvironment;

	/**
	 * The ontology environment.
	 */
	private final AbstractOntologyEnvironment ontologyEnvironment;

	final Class<? extends IOBIEThing> searchType;

	public GenericCorpusExtraction(String acMode, File printResults, final long seed, ECorpus corpusType)
			throws Exception {

		switch (corpusType) {
		case ArchitecturalStructure:
			projectEnvironment = StructureProjectEnvironment.getInstance();
			ontologyEnvironment = StructureOntologyEnvironment.getInstance();
			searchType = IArchitecturalStructure.class;

			break;
		case Film:

			projectEnvironment = FilmProjectEnvironment.getInstance();
			ontologyEnvironment = FilmOntologyEnvironment.getInstance();
			searchType = IFilm.class;
			break;
		case Single:
			projectEnvironment = SingleProjectEnvironment.getInstance();
			ontologyEnvironment = SingleOntologyEnvironment.getInstance();
			searchType = ISingle.class;
			break;
		default:
			throw new IllegalArgumentException();
		}

		OntologyInitializer.initializeOntology(ontologyEnvironment);

		log.info("Current run id = " + runID);

		/*
		 * Build parameter.
		 */
		RunParameter parameter = getParameter(seed);

		/*
		 * Created new standard Relation Extraction runner.
		 */
		AbstractRunner runner = new StandardRERunner(parameter);

		/**
		 * Whether you want to run the prediction of new texts or train and test a model
		 * on a given corpus.
		 */
		boolean predict = false;

		if (!predict) {

			/**
			 * Whether you want to start active learning procedure or normal training
			 */
			boolean activeLearning = parameter.corpusDistributor instanceof ActiveLearningDistributor;

			/*
			 * train and/or test on existing corpus.
			 */
//			reverseEngeneerACLearning(runner,seed);

			if (activeLearning) {
				activeLearning(runner, acMode, printResults, seed);
			} else {
				trainTest(runner);
			}

		} else {
			/*
			 * predict on a new documents.
			 */
			predict(runner, Arrays.asList(new File("predict/predict01.txt"), new File("predict/predict02.txt")));
		}

	}

	public RunParameter getParameter(final long seed) {
		/**
		 * This parameterBuilder contains standard configurations of the system that are
		 * used for Relation Extraction tasks. You can but may not change the parameter
		 * predefined in here unless you know what you are doing!
		 */
		final Builder paramBuilder = DBPediaParameterQuickAccess.getREParameter(searchType);

//		InvestigationRestriction investigationRestriction = new InvestigationRestriction(ISoccerPlayer.class, false);
//
//		paramBuilder.setInvestigationRestriction(investigationRestriction);

		/*
		 * Add parameter...
		 */

		/**
		 * The number of epochs that the system should be trained.
		 */
		final int epochs = 10;

		/**
		 * The distribution of the documents in the corpus. Origin takes training ,
		 * development and test data as they are. Use shufflDist() for shuffle the
		 * documents before and redistribute to train (80%), dev(0%) and test(20%). (You
		 * may change that distribution by building your own distributor...
		 */
//		final AbstractCorpusDistributor corpusDistributor = DBPediaParameterQuickAccess.predefinedDistributor
//				.originDist(1F);

		final AbstractCorpusDistributor corpusDistributor = new ActiveLearningDistributor.Builder()
				.setMode(EMode.ABSOLUT).setBPercentage(0.11F).setBAbsolute(1).setSeed(seed).setCorpusSizeFraction(1F)
				.setInitialTrainingSelectionFraction(0.1f).setTrainingProportion(80).setTestProportion(20).build();

//		final AbstractCorpusDistributor corpusDistributor = new ActiveLearningDistributor.Builder().setB(25)
//				.setSeed(200L).setCorpusSizeFraction(1F).setInitialTrainingSelectionFraction(0.0855f)
//				.setTrainingProportion(80).setTestProportion(20).build();

//		final AbstractCorpusDistributor corpusDistributor = ByNameDist.corpusDistributor;

		/*
		 * Set parameter
		 */
		paramBuilder.setCorpusDistributor(corpusDistributor);
		paramBuilder.setRunID(runID);
		paramBuilder.setProjectEnvironment(projectEnvironment);
		paramBuilder.setOntologyEnvironment(ontologyEnvironment);
		paramBuilder.setEpochs(epochs);

		/*
		 * Add factor-graph-templates.
		 */
		addTemplates(paramBuilder);

		return paramBuilder.build();

	}

	/**
	 * Add templates to the parameter builder.
	 * 
	 * @param paramBuilder
	 */
	private void addTemplates(Builder paramBuilder) {

		final Set<Class<? extends AbstractOBIETemplate<?>>> templates = new HashSet<>();
		/**
		 * TODO: Add new templates or try existing ones. Copy EmptyTemplate as
		 * code-template.
		 */

		/*
		 * DBPedia generic templates:
		 */
//		templates.add(GenericMainTemplatePriorTemplate.class);

		// TODO: Add your own templates for specific ontologies
		templates.add(CooccurrenceTemplate.class);

		/*
		 * Predefined generic templates:
		 */
//		templates.add(FrequencyTemplate.class);
		templates.add(TokenContextTemplate.class);
		templates.add(InterTokenTemplate.class);
		templates.add(InBetweenContextTemplate.class);
		templates.add(LocalTemplate.class);

		/*
		 * Templates that capture the cardinality of slots
		 */
		templates.add(SlotIsFilledTemplate.class);

		paramBuilder.setTemplates(templates);
	}

	private void predict(AbstractRunner runner, final List<File> filesToPredict) throws IOException {
		log.info("Start prediction of new documents...");
		/*
		 * Load model if exists
		 */
		if (!runner.modelExists()) {
			log.warn("Model does not exists, abort prediction!");
			return;
		}

		try {
			runner.loadModel();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/**
		 * Build instances...
		 */
		final List<OBIEInstance> instancesToPredict = new ArrayList<>();

		for (File file : filesToPredict) {
			OBIEInstance i = new OBIEInstance(file.getName(), Files.lines(file.toPath()).reduce("", String::concat),
					null, new HashSet<>(Arrays.asList(IManga.class)));
			instancesToPredict.add(i);
		}

		/**
		 * Start prediction...
		 */
		List<OBIEState> finalStates = runner.predictInstancesBatch(instancesToPredict,
				new HashSet<>(Arrays.asList(GenericDBPediaRegExNEL.getMangaInstance())));

	}

	/**
	 * Run the system with the specifications and configurations.
	 * 
	 * @param runner
	 * @throws Exception
	 */
	private static void trainTest(AbstractRunner runner) throws Exception {
		log.info("Start training / testing of a model with a given corpus...");
		final long testTime;
		final long trainingTime;
		final long trt;

		if (runner.modelExists()) {
			/*
			 * If the model exists, load the model from the file system. The model location
			 * is specified in the parameter and the environment.
			 */
			runner.loadModel();
			trt = 0;
		} else {
			/*
			 * If the model does not exists train. The model is automatically stored to the
			 * file system to the given model location!
			 */
			trainingTime = System.currentTimeMillis();
			runner.train();
			trt = (System.currentTimeMillis() - trainingTime);
			log.info("Total training time: " + trt + " ms.");
		}

		testTime = System.currentTimeMillis();
		/**
		 * Get predictions that can be evaluated for full evaluation and
		 * perSlotEvaluation.
		 */
		final List<SampledInstance<OBIEInstance, InstanceTemplateAnnotations, OBIEState>> predictions = runner
				.testOnTest();

		/**
		 * Evaluate the trained model on the test data. This is equal to predictOnTest
		 * and apply the results to an evaluator.
		 *
		 * Same as:
		 *
		 * // final PRF1Container overallPRF1 = runner.evaluateOnTest();
		 */
		final PRF1 overallPRF1 = EvaluatePrediction.evaluateREPredictions(runner.objectiveFunction, predictions,
				runner.getParameter().evaluator);

		log.info("Evaluation results on test data:\n" + overallPRF1);

		log.info("Evaluate predictions per slot:");

		/**
		 * Whether the output for each slot should be shown detailed or not. (Might
		 * generate large output)
		 */
		boolean detailedOutput = false;

		/**
		 * Evaluate the trained model on the test data for each slot individually.
		 *
		 * Same as:
		 *
		 * // runner.evaluatePerSlotOnTest(detailedOutput);
		 */
		EvaluatePrediction.evaluatePerSlotPredictions(runner.objectiveFunction, predictions,
				runner.getParameter().evaluator, detailedOutput);

		final long tet = (System.currentTimeMillis() - testTime);
		log.info("--------------" + runID + "---------------");

		log.info("Total training time: " + trt + " ms.");
		log.info("Total test time: " + tet + " ms.");
		log.info("Total time: "
				+ Duration.between(Instant.now(), Instant.ofEpochMilli(System.currentTimeMillis() + (trt + tet))));

	}

	private void activeLearning(AbstractRunner runner, String acMode, File printResults, final long seed)
			throws Exception {

		runID = acMode + new Random().nextInt();

		long allTime = System.currentTimeMillis();

		int i = 1;

		final IActiveLearningDocumentRanker ranker;

		if (acMode.equals("random")) {
			ranker = new FullDocumentRandomRanker(runner);
		} else if (acMode.equals("entropy")) {
			ranker = new FullDocumentEntropyRanker(runner);
		} else if (acMode.equals("rndFiller")) {
			ranker = new FullDocumentRandFillerRanker(runner);
		} else if (acMode.equals("entropyAtomic")) {
			ranker = new FullDocumentAtomicChangeEntropyRanker(runner);
		} else if (acMode.equals("variance")) {
			ranker = new FullDocumentVarianceRanker(runner);
		} else if (acMode.equals("objective")) {
			ranker = new FullDocumentObjectiveScoreRanker(runner);
		} else if (acMode.equals("model")) {
			ranker = new FullDocumentModelScoreRanker(runner);
		} else if (acMode.equals("length")) {
			ranker = new FullDocumentLengthRanker(runner);
		} else if (acMode.equals("margin")) {
			ranker = new FullDocumentMarginBasedRanker(runner);
		} else {
			ranker = null;
			log.error("unkown active learning mode");
			System.exit(1);
		}

		PrintStream resultPrintStream = new PrintStream(new FileOutputStream(printResults, true));
		resultPrintStream.println("############Active Learning Performances: " + runID + "############");
		resultPrintStream.println("#Iteration\t#TrainData\tPrecision\tRecall\tF1");

		List<OBIEInstance> newTrainingInstances = new ArrayList<>();

		final int maxNumberOfIterations = 50;
		int iterationCounter = 0;

		List<String> performances = new ArrayList<>();

		do {

			if (++iterationCounter > maxNumberOfIterations) {
				log.info("#############################");
				log.info("Reached maximum number of iterations: " + maxNumberOfIterations);
				log.info("#############################");
				break;
			}

			final int c = iterationCounter;

			log.info("#############################");
			log.info("New active learning iteration: " + (i));
			log.info("#############################");

			long time = System.currentTimeMillis();

			if (newTrainingInstances.isEmpty()) {
				runner.train();
			} else {
				log.info("New instances:");
				newTrainingInstances.forEach(s -> log.info(c + "_NEW\t" + s.getName()));

				runner.clean(getParameter(seed));
				runner.train();
			}

			log.info("Apply current model to test data...");

			Level trainerLevel = LogManager.getFormatterLogger(Trainer.class.getName()).getLevel();
			Level runnerLevel = LogManager.getFormatterLogger(AbstractRunner.class).getLevel();

			Configurator.setLevel(Trainer.class.getName(), Level.FATAL);
			Configurator.setLevel(AbstractRunner.class.getName(), Level.FATAL);

			List<SampledInstance<OBIEInstance, InstanceTemplateAnnotations, OBIEState>> predictions = runner
					.testOnTest();

			log.info("Training instances:");
			runner.corpusProvider.getTrainingCorpus().getInternalInstances()
					.forEach(s -> log.info(c + "_TRAIN\t" + s.getName()));

			log.info("Test instances:");
			runner.corpusProvider.getTestCorpus().getInternalInstances()
					.forEach(s -> log.info(c + "_TEST\t" + s.getName()));

			Configurator.setLevel(Trainer.class.getName(), trainerLevel);
			Configurator.setLevel(AbstractRunner.class.getName(), runnerLevel);

			PRF1 prf1 = EvaluatePrediction.evaluateREPredictions(runner.getObjectiveFunction(), predictions,
					runner.getParameter().evaluator);

			final String logPerformance = iterationCounter + "\t"
					+ runner.corpusProvider.getTrainingCorpus().getInternalInstances().size() + "\t"
					+ prf1.getPrecision() + "\t" + prf1.getRecall() + "\t" + prf1.getF1();

			performances.add(logPerformance);
			resultPrintStream.println(logPerformance);

			log.info("############Active Learning performances: " + runID + "############");
			performances.forEach(log::info);

			log.info("Time needed: " + (System.currentTimeMillis() - time));

		} while (!(newTrainingInstances = runner.corpusProvider.updateActiveLearning(runner, ranker)).isEmpty());

		log.info("--------------" + runID + "---------------");

		log.info("Total time needed: " + (System.currentTimeMillis() - allTime));

		log.info("Print results to: " + printResults);

		resultPrintStream.close();

	}

	/**
	 * Test every single document and sort
	 * 
	 * @param runner
	 * @throws Exception
	 */
	private void reverseEngineerACLearning(AbstractRunner runner, final long seed) throws Exception {

		final List<OBIEInstance> memTrain = new ArrayList<>(
				runner.corpusProvider.getTrainingCorpus().getInternalInstances());

		class X implements Comparable<X> {
			InstanceTemplateAnnotations thing;
			double f1Score;
			String name;

			public X(InstanceTemplateAnnotations instanceTemplateAnnotations, double f1Score, String name) {
				super();
				this.thing = instanceTemplateAnnotations;
				this.f1Score = f1Score;
				this.name = name;
			}

			@Override
			public String toString() {
				return "X [thing=" + thing + ", f1Score=" + f1Score + ", name=" + name + "]";
			}

			@Override
			public int compareTo(X o) {
				return -Double.compare(f1Score, o.f1Score);
			}

		}
		final List<X> sortablePerformances = new ArrayList<>();

		for (OBIEInstance instance : runner.corpusProvider.getDevelopCorpus().getInternalInstances()) {

			log.info("Add instance: " + instance);

			List<OBIEInstance> trainingInstances = new ArrayList<>();

			trainingInstances.addAll(memTrain);
			trainingInstances.add(instance);

			runner.corpusProvider.trainingCorpus = new BigramInternalCorpus(trainingInstances);

			runner.clean(getParameter(seed));
			runner.train();

			Level trainerLevel = LogManager.getFormatterLogger(Trainer.class.getName()).getLevel();
			Level runnerLevel = LogManager.getFormatterLogger(AbstractRunner.class).getLevel();

			Configurator.setLevel(Trainer.class.getName(), Level.FATAL);
			Configurator.setLevel(AbstractRunner.class.getName(), Level.FATAL);

			List<SampledInstance<OBIEInstance, InstanceTemplateAnnotations, OBIEState>> predictions = runner
					.testOnTest();

			Configurator.setLevel(Trainer.class.getName(), trainerLevel);
			Configurator.setLevel(AbstractRunner.class.getName(), runnerLevel);

			PRF1 prf1 = EvaluatePrediction.evaluateREPredictions(runner.getObjectiveFunction(), predictions,
					runner.getParameter().evaluator);

			final String logPerformance = runner.corpusProvider.getTrainingCorpus().getInternalInstances().size() + "\t"
					+ prf1.getPrecision() + "\t" + prf1.getRecall() + "\t" + prf1.getF1();

			log.info("-----------------------------");
			log.info(logPerformance);

			log.info("-----------------------------");

			sortablePerformances.add(new X(instance.getGoldAnnotation(), prf1.getF1(), instance.getName()));
			Collections.sort(sortablePerformances);
			sortablePerformances.forEach(log::info);
			log.info("-----------------------------");
			System.gc();

		}

		log.info("--------------" + runID + "---------------");
		sortablePerformances.forEach(log::info);

	}

}
