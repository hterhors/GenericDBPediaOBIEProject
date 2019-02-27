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
import de.hterhors.obie.ml.activelearning.SingleSlotModelScoreRanker;
import de.hterhors.obie.ml.corpus.BigramInternalCorpus;
import de.hterhors.obie.ml.corpus.distributor.AbstractCorpusDistributor;
import de.hterhors.obie.ml.corpus.distributor.ActiveLearningDistributor;
import de.hterhors.obie.ml.corpus.distributor.ShuffleCorpusDistributor;
import de.hterhors.obie.ml.run.AbstractRunner;
import de.hterhors.obie.ml.run.DefaultSlotFillingRunner;
import de.hterhors.obie.ml.run.eval.EvaluatePrediction;
import de.hterhors.obie.ml.run.param.RunParameter;
import de.hterhors.obie.ml.run.param.RunParameter.Builder;
import de.hterhors.obie.ml.templates.AbstractOBIETemplate;
import de.hterhors.obie.ml.templates.InBetweenContextTemplate;
import de.hterhors.obie.ml.templates.InterTokenTemplate;
import de.hterhors.obie.ml.templates.KnowledgeBaseTemplate;
import de.hterhors.obie.ml.templates.LocalTemplate;
import de.hterhors.obie.ml.templates.SlotIsFilledTemplate;
import de.hterhors.obie.ml.templates.TokenContextTemplate;
import de.hterhors.obie.ml.variables.InstanceTemplateAnnotations;
import de.hterhors.obie.ml.variables.OBIEInstance;
import de.hterhors.obie.ml.variables.OBIEState;
import de.hterhors.obie.projects.dbpedia.environments.dam.DamOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.dam.DamProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.film.FilmProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.single.SingleProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureOntologyEnvironment;
import de.hterhors.obie.projects.dbpedia.environments.structure.StructureProjectEnvironment;
import de.hterhors.obie.projects.dbpedia.ie.ner.regex.GenericDBPediaRegExNEL;
import de.hterhors.obie.projects.dbpedia.ie.parameter.DBPediaParameterQuickAccess;
import de.hterhors.obie.projects.dbpedia.ontology.dam.interfaces.IDam;
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
public class GenericExtraction {

	private static final String DEFAULT_ACTIVE_LEARNING_STRATEGY = "random";
	private static final String DEFAULT_ACTIVE_LEARNING_SEED = "200";
	private static final String DEFAULT_RESULT_FILE_NAME = "tmpResultFile";

	protected static Logger log = LogManager.getRootLogger();
	final private String acMode;
	final private long seed;
	final private ETemplateMode templateMode;
	final private ECorpus corpusType;
	final private File printResults;

	public static void main(String[] args) throws Exception {

		new GenericExtraction(args);

	}

	enum ETemplateMode {
		KB, LING;
	}

	enum EQueryType {
		Q1, Q2, Q3, Q4, Q5, ALL;
	}

	enum ECorpus {
		Film, ArchitecturalStructure, Single, Dam;
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
	EQueryType queryType;

	public GenericExtraction(String[] args) throws Exception {

		if (args == null || args.length == 0)
//			args = new String[] { "varianceResults", "variance", "20", "100", "ArchitecturalStructure"  };
//			args = new String[] { "randomResults", "random", "20", "100", "ArchitecturalStructure" };
//			args = new String[] { "lengthResults", "length", "20", "100", "ArchitecturalStructure"  };
//			args = new String[] { "rndFillerResults", "rndFiller", "20", "100", "ArchitecturalStructure"  };
//			args = new String[] { "entropyResults", "entropy", "20", "100", "ArchitecturalStructure"  };
//			args = new String[] { "entropyAtomicResults", "entropyAtomic", "20", "100", "ArchitecturalStructure"  };
//			args = new String[] { "modelResults", "model", "20", "100", "ArchitecturalStructure" };
//			args = new String[] { "marginResults", "margin", "20", "100", "ArchitecturalStructure" };
//			args = new String[] { "objectiveResults", "objective", "20", "100", "ArchitecturalStructure"  };
			args = new String[] { "filmResults", "objective", "2", "4", "Film", "LING", "ALL" };

		log.info("1) argument: file to store results");
		log.info(
				"2) argument: mode of active learning, \"random\"(default), \"entropy\", \"entropyAtomic\", \"objective\", \"model\", \"margin\", \"length\" or \"variance\"");
		log.info("3) argument: Random inital seed");
		log.info("4) argument: Number of N-best documents for entropy");
		log.info("5) argument: corpus one of \"Film\",\"ArchitecturalStructure\",\"Single\"");
		log.info("6) argument: mode of templates, one of \"SS\", \"PS\", \"BOTH\" or \"NONE\"");

		printResults = new File(args.length == 0 ? DEFAULT_RESULT_FILE_NAME : args[0]);
		acMode = args.length < 2 ? DEFAULT_ACTIVE_LEARNING_STRATEGY : args[1];
//		seed = Long.parseLong(args.length < 3 ? DEFAULT_ACTIVE_LEARNING_SEED : args[2]);
		seed = new Random().nextLong();
		if (args.length >= 4) {
			FullDocumentEntropyRanker.N = Integer.parseInt(args[3]);
		}

		corpusType = ECorpus.valueOf(args[4]);
		templateMode = ETemplateMode.valueOf(args[5]);
		queryType = EQueryType.valueOf(args[6]);

		log.info("Store results into: " + printResults);
		log.info("Active Learning Modus: " + acMode);

		if (printResults.getParentFile() != null && !printResults.getParentFile().exists()) {
			log.error("Parent dir does not exist: " + printResults.getParentFile().getCanonicalPath());
			System.exit(1);
		}

		switch (corpusType) {
		case Dam:
			projectEnvironment = DamProjectEnvironment.getInstance();
			ontologyEnvironment = DamOntologyEnvironment.getInstance();
			searchType = IDam.class;
			break;
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

		/*
		 * Created new standard Relation Extraction runner.
		 */
		AbstractRunner runner = new DefaultSlotFillingRunner(getParameter());

		/**
		 * Whether you want to run the prediction of new texts or train and test a model
		 * on a given corpus.
		 */
		boolean predict = false;

		if (!predict) {

			/**
			 * Whether you want to start active learning procedure or normal training
			 */
			boolean activeLearning = runner.getParameter().corpusDistributor instanceof ActiveLearningDistributor;

			/*
			 * train and/or test on existing corpus.
			 */
//			reverseEngineerACLearning(runner);

			if (activeLearning) {
				activeLearning(runner);
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

	public RunParameter getParameter() {
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
		final AbstractCorpusDistributor corpusDistributor = new ShuffleCorpusDistributor.Builder().setSeed(seed)
				.setTrainingProportion(80).setDevelopmentProportion(0).setCorpusSizeFraction(0.3F).setTestProportion(20)
				.build();

//		final AbstractCorpusDistributor corpusDistributor = DBPediaParameterQuickAccess.predefinedDistributor
//				.originDist(1F);

//		final AbstractCorpusDistributor corpusDistributor = new ActiveLearningDistributor.Builder()
//				.setMode(EMode.ABSOLUT).setBPercentage(0.11F).setBAbsolute(1).setSeed(seed).setCorpusSizeFraction(0.3F)
//				.setInitialTrainingSelectionFraction(0.1f).setTrainingProportion(80).setTestProportion(20).build();

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

//		templates.add(GenericMainTemplatePriorTemplate.class);

		// TODO: Add your own templates

//		templates.add(KnowledgeBaseTemplate.class);
//		templates.add(TrainAsKnowledgeBaseTemplate.class);

//		templates.add(CooccurrenceTemplate.class);
//		templates.add(DocumentClassificationTemplate.class);
//		templates.add(LevenshteinTemplate.class);
		/*
		 * Predefined generic templates:
		 */
//		templates.add(FrequencyTemplate.class);

		if (templateMode == ETemplateMode.KB) {

			templates.add(KnowledgeBaseTemplate.class);

			if (queryType == EQueryType.Q1) {
				KnowledgeBaseTemplate.useQuery1 = false;
			} else if (queryType == EQueryType.Q2) {
				KnowledgeBaseTemplate.useQuery2 = false;
			} else if (queryType == EQueryType.Q3) {
				KnowledgeBaseTemplate.useQuery3 = false;
			} else if (queryType == EQueryType.Q4) {
				KnowledgeBaseTemplate.useQuery4 = false;
			} else if (queryType == EQueryType.Q5) {
				KnowledgeBaseTemplate.useQuery5 = false;
			}
		}

		templates.add(TokenContextTemplate.class);
		templates.add(InterTokenTemplate.class);
		templates.add(InBetweenContextTemplate.class);

		templates.add(LocalTemplate.class);

		/*
		 * Templates that capture the cardinality of slots
		 */
		templates.add(SlotIsFilledTemplate.class);

		/**
		 * TODO: Add new templates or try existing ones. Copy EmptyTemplate as
		 * code-template.
		 */

		/*
		 * DBPedia generic templates:
		 */
//		PRF1 [tp=291.0, fp=228.0, fn=434.0, getF1()=0.4678456591639872, getRecall()=0.4013793103448276, getPrecision()=0.5606936416184971, getJaccard()=0.30535152151101785]
//		templates.add(GenericMainTemplatePriorTemplate.class);
//		if (templateMode == ETemplateMode.SS || templateMode == ETemplateMode.BOTH)

		// TODO: Add your own templates for specific ontologies
//		templates.add(CooccurrenceTemplate.class);
//		ALl empty/null!!! PRF1 [tp=177.0, fp=0.0, fn=567.0, getF1()=0.3843648208469056, getRecall()=0.23790322580645162, getPrecision()=1.0, getJaccard()=0.23790322580645162]
//		if (templateMode == ETemplateMode.PS || templateMode == ETemplateMode.BOTH)

//		GenericMainTemplatePriorTemplate+CooccurrenceTemplate PRF1 [tp=353.0, fp=300.0, fn=353.0, getF1()=0.5194996320824136, getRecall()=0.5, getPrecision()=0.5405819295558959, getJaccard()=0.3508946322067594]

//		templates.add(KnowledgeBaseTemplate.class);
//		SingleSlot + KB PRF1 [tp=337.0, fp=272.0, fn=379.0, getF1()=0.5086792452830188, getRecall()=0.47067039106145253, getPrecision()=0.5533661740558292, getJaccard()=0.3410931174089069]

//	FrequencyTemplate +KnowledgeBaseTemplate	PRF1 [tp=416.0, fp=257.0, fn=287.0, getF1()=0.6046511627906977, getRecall()=0.5917496443812233, getPrecision()=0.6181277860326895, getJaccard()=0.43333333333333335]

//		FrequencyTemplate +KnowledgeBaseTemplate+TokenContextTemplate	PRF1 [tp=524.0, fp=144.0, fn=188.0, getF1()=0.7594202898550725, getRecall()=0.7359550561797753, getPrecision()=0.7844311377245509, getJaccard()=0.6121495327102804]

		/*
		 * Predefined generic templates:
		 */
//		templates.add(FrequencyTemplate.class);
//		PRF1 [tp=335.0, fp=122.0, fn=380.0, getF1()=0.5716723549488054, getRecall()=0.46853146853146854, getPrecision()=0.7330415754923414, getJaccard()=0.4002389486260454]

//		CooccurrenceTemplate+	TokenContextTemplate PRF1 [tp=543.0, fp=292.0, fn=143.0, getF1()=0.7140039447731754, getRecall()=0.7915451895043731, getPrecision()=0.6502994011976048, getJaccard()=0.5552147239263804]
//		KnowledgeBaseTemplate + TokenContextTemplate	PRF1 [tp=493.0, fp=93.0, fn=225.0, getF1()=0.7561349693251532, getRecall()=0.6866295264623955, getPrecision()=0.841296928327645, getJaccard()=0.6078914919852034]

//		templates.add(TokenContextTemplate.class);
//		TokenContextTemplate PRF1 [tp=473.0, fp=75.0, fn=246.0, getF1()=0.7466456195737963, getRecall()=0.6578581363004172, getPrecision()=0.8631386861313869, getJaccard()=0.5957178841309824]

//	TokenContextTemplate + FrequencyTemplate	PRF1 [tp=516.0, fp=125.0, fn=196.0, getF1()=0.762749445676275, getRecall()=0.7247191011235955, getPrecision()=0.8049921996879875, getJaccard()=0.6164874551971327]
//		InterTokenTemplate+TokenContextTemplate	PRF1 [tp=544.0, fp=253.0, fn=148.0, getF1()=0.7306917394224313, getRecall()=0.7861271676300579, getPrecision()=0.6825595984943539, getJaccard()=0.5756613756613757]
//		KnowledgeBaseTemplate	+InterTokenTemplate + TokenContextTemplate PRF1 [tp=492.0, fp=72.0, fn=231.0, getF1()=0.7645687645687645, getRecall()=0.6804979253112033, getPrecision()=0.8723404255319149, getJaccard()=0.6188679245283019]

//		templates.add(InterTokenTemplate.class);
//		InterTokenTemplate PRF1 [tp=301.0, fp=168.0, fn=430.0, getF1()=0.5016666666666667, getRecall()=0.4117647058823529, getPrecision()=0.6417910447761194, getJaccard()=0.3348164627363737]

//		templates.add(InBetweenContextTemplate.class);
//		PRF1 [tp=207.0, fp=183.0, fn=505.0, getF1()=0.37568058076225047, getRecall()=0.2907303370786517, getPrecision()=0.5307692307692308, getJaccard()=0.23128491620111732]

//	InterTokenTemplate+InBetweenContextTemplate	PRF1 [tp=407.0, fp=216.0, fn=310.0, getF1()=0.6074626865671642, getRecall()=0.5676429567642957, getPrecision()=0.6532905296950241, getJaccard()=0.4362272240085745]

//		templates.add(LocalTemplate.class);
//		PRF1 [tp=166.0, fp=97.0, fn=552.0, getF1()=0.3384301732925586, getRecall()=0.23119777158774374, getPrecision()=0.6311787072243346, getJaccard()=0.20368098159509201]

		/*
		 * Templates that capture the cardinality of slots
		 */
//		templates.add(SlotIsFilledTemplate.class);

		paramBuilder.setTemplates(templates);
	}
//	FrequencyTemplate TokenContextTemplate InterTokenTemplate PRF1 [tp=522.0, fp=100.0, fn=197.0, getF1()=0.7785234899328859, getRecall()=0.7260083449235049, getPrecision()=0.8392282958199357, getJaccard()=0.6373626373626373]

//	+SlotIsFilledTemplate PRF1 [tp=512.0, fp=81.0, fn=205.0, getF1()=0.781679389312977, getRecall()=0.7140864714086471, getPrecision()=0.863406408094435, getJaccard()=0.6416040100250626]
//	+SlotIsFilledTemplate+LocalTemplate	PRF1 [tp=519.0, fp=82.0, fn=196.0, getF1()=0.7887537993920973, getRecall()=0.7258741258741259, getPrecision()=0.8635607321131448, getJaccard()=0.6511919698870765]
//	+FrequencyTemplate PRF1 [tp=532.0, fp=99.0, fn=187.0, getF1()=0.7881481481481482, getRecall()=0.7399165507649513, getPrecision()=0.8431061806656102, getJaccard()=0.6503667481662592]
//	PRF1 [tp=553.0, fp=143.0, fn=155.0, getF1()=0.7877492877492878, getRecall()=0.7810734463276836, getPrecision()=0.7945402298850575, getJaccard()=0.6498237367802585]

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
	private void trainTest(AbstractRunner runner) throws Exception {
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

		PrintStream resultPrintStream = new PrintStream(new FileOutputStream(printResults, true));
		resultPrintStream.println("############Performances: " + runID + "############");
		resultPrintStream.println("#Model\tPrecision\tRecall\tF1");
		final String logPerformance = templateMode + "\t" + overallPRF1.getPrecision() + "\t" + overallPRF1.getRecall()
				+ "\t" + overallPRF1.getF1();
		resultPrintStream.println(logPerformance);

		resultPrintStream.close();
		final long tet = (System.currentTimeMillis() - testTime);
		log.info("--------------" + runID + "---------------");

		log.info("Total training time: " + trt + " ms.");
		log.info("Total test time: " + tet + " ms.");
		log.info("Total time: "
				+ Duration.between(Instant.now(), Instant.ofEpochMilli(System.currentTimeMillis() + (trt + tet))));

	}

	private void activeLearning(AbstractRunner runner) throws Exception {

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
		} else if (acMode.equals("single")) {
			ranker = new SingleSlotModelScoreRanker(runner);
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
		PrintStream ps = new PrintStream(new File("pickedInstances"));

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
				newTrainingInstances.forEach(s -> ps.println(c + "_NEW\t" + s.getName()));

				runner.clean(getParameter());
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
		ps.close();
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
	private void reverseEngineerACLearning(AbstractRunner runner) throws Exception {

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
//		runner.corpusProvider.getDevelopCorpus().getInternalInstanceByName("Pioneer_and_Endicott_Buildings");
//		runner.corpusProvider.getDevelopCorpus().getInternalInstanceByName("Stuckman_Cottage");
//
//		runner.clean(getParameter(seed));
//		runner.train();
//
//		Level trainerLevel = LogManager.getFormatterLogger(Trainer.class.getName()).getLevel();
//		Level runnerLevel = LogManager.getFormatterLogger(AbstractRunner.class).getLevel();
//
////		Configurator.setLevel(Trainer.class.getName(), Level.FATAL);
////		Configurator.setLevel(AbstractRunner.class.getName(), Level.FATAL);
//
//		List<SampledInstance<OBIEInstance, InstanceTemplateAnnotations, OBIEState>> predictions = runner.testOnTest();
//
////		Configurator.setLevel(Trainer.class.getName(), trainerLevel);
////		Configurator.setLevel(AbstractRunner.class.getName(), runnerLevel);
//
//		PRF1 prf1 = EvaluatePrediction.evaluateREPredictions(runner.getObjectiveFunction(), predictions,
//				runner.getParameter().evaluator);
//
//		String logPerformance = runner.corpusProvider.getTrainingCorpus().getInternalInstances().size() + "\t"
//				+ prf1.getPrecision() + "\t" + prf1.getRecall() + "\t" + prf1.getF1();
//
//		log.info("-----------------------------");
//		log.info(logPerformance);
//
//		log.info("-----------------------------");

		for (OBIEInstance instance : runner.corpusProvider.getDevelopCorpus().getInternalInstances()) {

//			if (!(instance.getName().equals("Pioneer_and_Endicott_Buildings")
//					|| instance.getName().equals("Stuckman_Cottage")))
//				continue;

			log.info("Add instance: " + instance);

			List<OBIEInstance> trainingInstances = new ArrayList<>();

			trainingInstances.addAll(memTrain);
//		trainingInstances.add(
//				runner.corpusProvider.getDevelopCorpus().getInternalInstanceByName("Pioneer_and_Endicott_Buildings"));
//		trainingInstances.add(runner.corpusProvider.getDevelopCorpus().getInternalInstanceByName("Stuckman_Cottage"));
			trainingInstances.add(instance);

			runner.corpusProvider.trainingCorpus = new BigramInternalCorpus(trainingInstances);

			runner.clean(getParameter());
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

			String logPerformance = runner.corpusProvider.getTrainingCorpus().getInternalInstances().size() + "\t"
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
		PrintStream ps = new PrintStream(new File("plus1Results"));
		log.info("--------------Seed=" + seed + " " + runID + "---------------");
		sortablePerformances.forEach(log::info);
		sortablePerformances.forEach(ps::println);

	}

}
