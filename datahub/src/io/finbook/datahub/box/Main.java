package io.finbook.datahub.box;

import io.finbook.datahub.Archetype;
import io.intino.datahub.DataHub;
import io.intino.datahub.graph.NessGraph;
import io.intino.magritte.framework.Graph;

public class Main {

    private static final String[] Stashes = {"Solution", "Finbook"};

    public static void main(String[] args) {
        DataHubBox box = new DataHubBox(args);
        NessGraph nessGraph = nessGraph(box);

        DataHub dataHub = new DataHub(nessGraph, box.archetype().datahub().stage().getAbsoluteFile());
        dataHub.start();

        box.put(nessGraph).put(dataHub).start();
        Runtime.getRuntime().addShutdownHook(new Thread(box::stop));
    }

    private static NessGraph nessGraph(DataHubBox box) {
        NessGraph graph = new Graph().loadStashes(Stashes).as(NessGraph.class);
        injectJmsConfiguration(box, graph);
        return graph;
    }

    private static void injectJmsConfiguration(DataHubBox box, NessGraph graph) {
        DataHubConfiguration configuration = box.configuration();
        Archetype archetype = box.archetype();
        graph.datalake().path(archetype.datalake().root().getAbsolutePath());
        graph.broker().path(archetype.datahub().stage().getAbsolutePath());
        graph.broker().port(Integer.parseInt(configuration.brokerPort()));
        graph.broker().secondaryPort(Integer.parseInt(configuration.brokerSecondaryPort()));
    }
}
