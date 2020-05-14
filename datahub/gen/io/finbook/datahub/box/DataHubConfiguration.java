package io.finbook.datahub.box;

import java.util.Map;
import java.util.HashMap;

public class DataHubConfiguration extends io.intino.alexandria.core.BoxConfiguration {

	public DataHubConfiguration(String[] args) {
		super(args);
	}

	public String brokerPort() {
		return get("broker_port");
	}

	public String brokerSecondaryPort() {
		return get("broker_secondary_port");
	}
}