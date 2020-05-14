package io.finbook.invoicehandler.graph;

import io.intino.magritte.framework.Graph;

public class InvoiceHandlerGraph extends io.finbook.invoicehandler.graph.AbstractGraph {

	public InvoiceHandlerGraph(Graph graph) {
		super(graph);
	}

	public InvoiceHandlerGraph(io.intino.magritte.framework.Graph graph, InvoiceHandlerGraph wrapper) {
	    super(graph, wrapper);
	}
}