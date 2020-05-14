package io.finbook.invoicehandler.box;

import java.util.Map;
import java.util.HashMap;

public class InvoiceHandlerConfiguration extends io.intino.alexandria.core.BoxConfiguration {

	public InvoiceHandlerConfiguration(String[] args) {
		super(args);
	}

	public String datalakeDirectory() {
		return get("datalake_directory");
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

	public String workspace() {
		return get("workspace");
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