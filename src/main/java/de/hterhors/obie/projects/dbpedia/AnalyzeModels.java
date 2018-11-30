package de.hterhors.obie.projects.dbpedia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AnalyzeModels {

	public static void main(String[] args) throws Exception {

		File model1 = new File(
				"bigram/models/development/ActiveLearning/IArchitecturalStructure/full/randomRun-1771714170/randomRun-1771714170_ActiveLearning0_epoch_5");

		File betterModel = new File(
				"bigram/models/development/ActiveLearning/IArchitecturalStructure/full/randomRun489622050/randomRun489622050_ActiveLearning0_epoch_5");

		File diffModel = new File("bigram/diffs/-1771714170_489622050/");
		diffModel.mkdirs();
		for (String templateName : model1.list()) {

			Map<String, Double> values1 = readTemplate(new File(model1, templateName));
			Map<String, Double> betterValues = readTemplate(new File(betterModel, templateName));

			Set<String> keys = new HashSet<>();
			keys.addAll(values1.keySet());
			keys.addAll(betterValues.keySet());

			Map<String, Double> diffValues = new HashMap<>();

			for (String key : keys) {

				final String x = (values1.containsKey(key) && betterValues.containsKey(key) ? key
						: (values1.containsKey(key) ? ("ONLY_IN_1" + key) : ("ONLY_IN_2" + key)));
				diffValues.put(x, betterValues.getOrDefault(key, 0D) - values1.getOrDefault(key, 0D));
			}

			PrintStream ps = new PrintStream(new File(diffModel, templateName));

			List<Entry<String, Double>> sortKeys = new ArrayList<>();

			sortKeys.addAll(diffValues.entrySet());

			Collections.sort(sortKeys, new Comparator<Entry<String, Double>>() {

				@Override
				public int compare(Entry<String, Double> o1, Entry<String, Double> o2) {
					return -Double.compare(o1.getValue(), o2.getValue());
				}
			});

			for (Entry<String, Double> diffValue : sortKeys) {
				ps.println(diffValue.getKey() + "\t" + diffValue.getValue());
			}

			ps.close();
		}

	}

	private static Map<String, Double> readTemplate(File file) throws FileNotFoundException, IOException {
		Map<String, Double> values = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";

		while ((line = br.readLine()) != null) {

			final String[] data = line.split("\t");
			final String name = data[0];
			final Double value = Double.valueOf(data[1]);

			values.put(name, value);

		}

		br.close();
		return values;
	}

}
