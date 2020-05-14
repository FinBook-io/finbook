package io.finbook.invoicehandler.graph;

import io.finbook.invoicehandler.graph.*;

public abstract class AbstractInvoice  extends io.intino.magritte.framework.Layer implements io.intino.magritte.framework.tags.Terminal {
	protected java.lang.String xml;

	public AbstractInvoice(io.intino.magritte.framework.Node node) {
		super(node);
	}

	public java.lang.String xml() {
		return xml;
	}

	public Invoice xml(java.lang.String value) {
		this.xml = value;
		return (Invoice) this;
	}

	@Override
	protected java.util.Map<java.lang.String, java.util.List<?>> variables$() {
		java.util.Map<String, java.util.List<?>> map = new java.util.LinkedHashMap<>();
		map.put("xml", new java.util.ArrayList(java.util.Collections.singletonList(this.xml)));
		return map;
	}

	@Override
	protected void load$(java.lang.String name, java.util.List<?> values) {
		super.load$(name, values);
		if (name.equalsIgnoreCase("xml")) this.xml = io.intino.magritte.framework.loaders.StringLoader.load(values, this).get(0);
	}

	@Override
	protected void set$(java.lang.String name, java.util.List<?> values) {
		super.set$(name, values);
		if (name.equalsIgnoreCase("xml")) this.xml = (java.lang.String) values.get(0);
	}

	public io.finbook.invoicehandler.graph.InvoiceHandlerGraph graph() {
		return (io.finbook.invoicehandler.graph.InvoiceHandlerGraph) core$().graph().as(io.finbook.invoicehandler.graph.InvoiceHandlerGraph.class);
	}
}