package io.finbook.datahub.box;

import io.intino.alexandria.core.Box;
import io.intino.datahub.graph.NessGraph;
import io.intino.magritte.framework.Graph;

import java.util.Arrays;

public class Main {

    private static final String[] Stashes = {"Solution"};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        NessGraph nessGraph = new Graph().loadStashes(Stashes).as(NessGraph.class);
        Box box = new io.intino.datahub.box.DataHubBox(args).put(nessGraph.core$()).start();
        Runtime.getRuntime().addShutdownHook(new Thread(box::stop));
    }
}
