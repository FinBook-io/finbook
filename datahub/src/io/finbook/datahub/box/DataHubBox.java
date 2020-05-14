package io.finbook.datahub.box;

import io.finbook.datahub.Archetype;
import io.intino.datahub.DataHub;
import io.intino.datahub.graph.NessGraph;

public class DataHubBox extends AbstractBox {

	private Archetype archetype;
	private DataHub dataHub;
	private NessGraph nessGraph;

	public DataHubBox(String[] args) {
		super(args);
		DataHubConfiguration configuration = new DataHubConfiguration(args);
		this.archetype = new Archetype(configuration.home());
	}

	public DataHubBox(DataHubConfiguration configuration) {
		super(configuration);
		this.archetype = new Archetype(configuration.home());
	}

	@Override
	public io.intino.alexandria.core.Box put(Object o) {
		super.put(o);
		if (o instanceof NessGraph) this.nessGraph = (NessGraph) o;
		if (o instanceof DataHub) this.dataHub = (DataHub) o;
		return this;
	}

	public Archetype archetype() {
		return archetype;
	}

	public DataHub dataHub() {
		return dataHub;
	}

	public NessGraph nessGraph() {
		return nessGraph;
	}

	public void beforeStart() {

	}

	public void afterStart() {

	}

	public void beforeStop() {

	}

	public void afterStop() {

	}
}