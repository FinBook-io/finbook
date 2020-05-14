package io.finbook.invoicehandler.box;

import io.finbook.invoicehandler.Archetype;
import io.intino.magritte.framework.Graph;
import io.intino.magritte.framework.stores.FileSystemStore;
import io.intino.magritte.io.Stash;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static final String Finbook = "Finbook";

	public static void main(String[] args) {
		args = updateFrom(args);

		InvoiceHandlerConfiguration configuration = new InvoiceHandlerConfiguration(args);
		InvoiceHandlerBox box = new InvoiceHandlerBox(configuration);
		Graph graph = new Graph(store(box.archetype().datamart().root()));

		box.put(graph).start();
		Runtime.getRuntime().addShutdownHook(new Thread(box::stop));
	}

	private static String[] updateFrom(String[] args) {
		Map<String, String> argsMap = getArgsMapFromArray(args);
		Archetype archetype = new Archetype(new InvoiceHandlerConfiguration(args).home());

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

	private static FileSystemStore store(File datamartFolder) {
		return new FileSystemStore(datamartFolder) {
			@Override
			public Stash stashFrom(String path) {
				Stash stash = super.stashFrom(path);
				if (stash != null && stash.language == null) stash.language = Finbook;
				return stash;
			}

			@Override
			public void writeStash(Stash stash, String path) {
				stash.language = stash.language == null || stash.language.isEmpty() ? Finbook : stash.language;
				super.writeStash(stash, path);
			}
		};
	}
}