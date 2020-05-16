package io.finbook.tester.box;

import java.util.Map;
import java.util.HashMap;

public class TesterConfiguration extends io.intino.alexandria.core.BoxConfiguration {

	public TesterConfiguration(String[] args) {
		super(args);
	}

	public String terminalUser() {
		return get("terminal_user");
	}

	public String terminalPassword() {
		return get("terminal_password");
	}

	public String terminalClientid() {
		return get("terminal_clientId");
	}

	public String datalakeDirectory() {
		return get("datalake_directory");
	}

	public String terminalUrl() {
		return get("terminal_url");
	}

	public String port() {
		return get("port");
	}

	public String terminalWorkingDirectory() {
		return get("terminal_working_directory");
	}
}