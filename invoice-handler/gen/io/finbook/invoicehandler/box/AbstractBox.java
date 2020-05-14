package io.finbook.invoicehandler.box;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import io.intino.alexandria.logger.Logger;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public abstract class AbstractBox extends io.intino.alexandria.core.Box {
	protected InvoiceHandlerConfiguration configuration;
	private io.intino.alexandria.event.JmsEventHub eventHub;
	private io.finbook.datahub.HandlerTerminal terminal;
	private io.intino.alexandria.datalake.Datalake datalake;

	public AbstractBox(String[] args) {
		this(new InvoiceHandlerConfiguration(args));
	}

	public AbstractBox(InvoiceHandlerConfiguration configuration) {
		this.configuration = configuration;
		initJavaLogger();
		this.datalake = new io.intino.alexandria.datalake.file.FileDatalake(new java.io.File(configuration().get("datalake_directory")));
		this.eventHub = new io.intino.alexandria.event.JmsEventHub(configuration().get("terminal_url"), configuration().get("terminal_user"), configuration().get("terminal_password"), configuration().get("terminal_clientId"), new java.io.File(configuration().get("terminal_working_directory")));
		this.terminal = new io.finbook.datahub.HandlerTerminal(eventHub);
	}

	public InvoiceHandlerConfiguration configuration() {
		return configuration;
	}

	@Override
	public io.intino.alexandria.core.Box put(Object o) {
		return this;
	}

	public abstract void beforeStart();

	public io.intino.alexandria.core.Box start() {
		if (owner != null) owner.beforeStart();
		beforeStart();
		if (owner != null) owner.start();
		initSentinels();
		initUI();
		initRestServices();
		initSoapServices();
		initJmxServices();
		initDatalake();
		initMessageHub();
		initTerminal();
		initMessagingServices();
		initSlackBots();
		initWorkflow();
		if (owner != null) owner.afterStart();
		afterStart();
		return this;
	}

	public abstract void afterStart();

	public abstract void beforeStop();

	public void stop() {
		if (owner != null) owner.beforeStop();
		beforeStop();
		if (owner != null) owner.stop();
		if (terminal != null) terminal.stop();
		if (owner != null) owner.afterStop();
		afterStop();
	}

	public abstract void afterStop();

	public io.finbook.datahub.HandlerTerminal terminal() {
		return this.terminal;
	}

	protected io.intino.alexandria.event.JmsEventHub eventHub() {
		return this.eventHub;
	}

	public io.intino.alexandria.datalake.Datalake datalake() {
		return this.datalake;
	}



	private void initRestServices() {

	}

	private void initSoapServices() {

	}

	private void initMessagingServices() {

	}

	private void initJmxServices() {

	}

	private void initSlackBots() {

	}

	private void initUI() {

	}

	private void initDatalake() {
	}

	private void initMessageHub() {
	}


	private void initTerminal() {
		if(this.terminal != null) {
			this.terminal.subscribe((io.finbook.datahub.HandlerTerminal.InvoicesConsumer) e -> new io.finbook.invoicehandler.box.subscribers.InvoicesSubscriber((InvoiceHandlerBox) AbstractBox.this).accept(e), "Invoices");
		}

	}

	private void initSentinels() {
	}

	private void initWorkflow() {
	}

	private void initJavaLogger() {
		final java.util.logging.Logger Logger = java.util.logging.Logger.getGlobal();
		final ConsoleHandler handler = new ConsoleHandler();
		handler.setLevel(Level.INFO);
		handler.setFormatter(new io.intino.alexandria.logger.Formatter());
		Logger.setUseParentHandlers(false);
		Logger.addHandler(handler);
	}

	private java.net.URL url(String url) {
		try {
			return new java.net.URL(url);
		} catch (java.net.MalformedURLException e) {
			return null;
		}
	}
}