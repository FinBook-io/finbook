package io.finbook.invoicehandler.graph;

import io.intino.magritte.framework.Graph;

public class AbstractGraph extends io.intino.magritte.framework.GraphWrapper {

	protected io.intino.magritte.framework.Graph graph;
	private java.util.List<io.finbook.invoicehandler.graph.Invoice> invoiceList = new java.util.ArrayList<>();

	private java.util.Map<String, Indexer> _index = _fillIndex();

	public AbstractGraph(io.intino.magritte.framework.Graph graph) {
		this.graph = graph;
		this.graph.i18n().register("invoice-handler");
	}

	public AbstractGraph(io.intino.magritte.framework.Graph graph, AbstractGraph wrapper) {
		this.graph = graph;
		this.graph.i18n().register("invoice-handler");
		this.invoiceList = new java.util.ArrayList<>(wrapper.invoiceList);
	}

	public <T extends io.intino.magritte.framework.GraphWrapper> T a$(Class<T> t) {
		return this.core$().as(t);
	}

    @Override
	public void update() {
		this._index.values().forEach(v -> v.clear());
		graph.rootList().forEach(r -> addNode$(r));
	}

	@Override
	protected void addNode$(io.intino.magritte.framework.Node node) {
		for (io.intino.magritte.framework.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).add(node);
		if (this._index.containsKey(node.id())) this._index.get(node.id()).add(node);
	}

	@Override
	protected void removeNode$(io.intino.magritte.framework.Node node) {
		for (io.intino.magritte.framework.Concept c : node.conceptList()) if (this._index.containsKey(c.id())) this._index.get(c.id()).remove(node);
		if (this._index.containsKey(node.id())) this._index.get(node.id()).remove(node);
	}

	public java.net.URL resourceAsMessage$(String language, String key) {
		return graph.loadResource(graph.i18n().message(language, key));
	}

	public java.util.List<io.finbook.invoicehandler.graph.Invoice> invoiceList() {
		return invoiceList;
	}

	public java.util.stream.Stream<io.finbook.invoicehandler.graph.Invoice> invoiceList(java.util.function.Predicate<io.finbook.invoicehandler.graph.Invoice> filter) {
		return invoiceList.stream().filter(filter);
	}

	public io.finbook.invoicehandler.graph.Invoice invoice(int index) {
		return invoiceList.get(index);
	}

	public io.intino.magritte.framework.Graph core$() {
		return graph;
	}

	public io.intino.magritte.framework.utils.I18n i18n$() {
		return graph.i18n();
	}

	public Create create() {
		return new Create("Misc", null);
	}

	public Create create(String stash) {
		return new Create(stash, null);
	}

	public Create create(String stash, String name) {
		return new Create(stash, name);
	}

	public Clear clear() {
		return new Clear();
	}

	public class Create {
		private final String stash;
		private final String name;

		public Create(String stash, String name) {
			this.stash = stash;
			this.name = name;
		}

		public io.finbook.invoicehandler.graph.Invoice invoice(java.lang.String xml) {
			io.finbook.invoicehandler.graph.Invoice newElement = AbstractGraph.this.graph.createRoot(io.finbook.invoicehandler.graph.Invoice.class, stash, this.name).a$(io.finbook.invoicehandler.graph.Invoice.class);
			newElement.core$().set(newElement, "xml", java.util.Collections.singletonList(xml));
			return newElement;
		}
	}

	public class Clear {
	    public void invoice(java.util.function.Predicate<io.finbook.invoicehandler.graph.Invoice> filter) {
	    	new java.util.ArrayList<>(AbstractGraph.this.invoiceList()).stream().filter(filter).forEach(io.intino.magritte.framework.Layer::delete$);
	    }
	}


	private java.util.HashMap<String, Indexer> _fillIndex() {
		java.util.HashMap<String, Indexer> map = new java.util.HashMap<>();
		map.put("Invoice", new Indexer(node -> invoiceList.add(node.as(io.finbook.invoicehandler.graph.Invoice.class)), node -> invoiceList.remove(node.as(io.finbook.invoicehandler.graph.Invoice.class)), () -> invoiceList.clear()));
		return map;
	}

	public static class Indexer {
		Add add;
		Remove remove;
		IndexClear clear;

		public Indexer(Add add, Remove remove, IndexClear clear) {
			this.add = add;
			this.remove = remove;
			this.clear = clear;
		}

		void add(io.intino.magritte.framework.Node node) {
			this.add.add(node);
		}

		void remove(io.intino.magritte.framework.Node node) {
			this.remove.remove(node);
		}

		void clear() {
			this.clear.clear();
		}
	}

	interface Add {
		void add(io.intino.magritte.framework.Node node);
	}

	interface Remove {
		void remove(io.intino.magritte.framework.Node node);
	}

	interface IndexClear {
		void clear();
	}
}