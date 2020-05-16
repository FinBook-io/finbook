package io.finbook.tester.box;

import io.finbook.tester.Archetype;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		args = updateFrom(args);
		TesterConfiguration testerConfiguration = new TesterConfiguration(args);
		TesterBox box = new TesterBox(testerConfiguration);
		box.start();
		Runtime.getRuntime().addShutdownHook(new Thread(box::stop));
	}

	private static String[] updateFrom(String[] args) {
		Map<String, String> argsMap = getArgsMapFromArray(args);
		Archetype archetype = new Archetype(new TesterConfiguration(args).home());

		argsMap.put("datalake_directory", archetype.datalake().root().getAbsolutePath());
		argsMap.put("terminal_working_directory", archetype.datahub().terminal().getAbsolutePath());

		return getArgsArrayFromMap(argsMap);
	}

	private static String[] getArgsArrayFromMap(Map<String, String> argsMap) {
		return argsMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).toArray(String[]::new);
	}

	private static Map<String, String> getArgsMapFromArray(String[] args) {
		Map<String, String> argsMap = new HashMap<>();
		Arrays.asList(args).forEach(arg -> {
			String[] argSplitted = arg.split("=");
			if(argSplitted.length == 1) argsMap.put(argSplitted[0], "");
			if(argSplitted.length == 2) argsMap.put(argSplitted[0], argSplitted[1]);
		});
		return argsMap;
	}
}