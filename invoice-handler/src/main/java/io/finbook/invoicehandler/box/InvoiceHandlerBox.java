package io.finbook.invoicehandler.box;

import io.finbook.invoicehandler.Archetype;

public class InvoiceHandlerBox extends AbstractBox {

	private Archetype archetype;

	public InvoiceHandlerBox(String[] args) {
		super(args);
		InvoiceHandlerConfiguration configuration = new InvoiceHandlerConfiguration(args);
		this.archetype = new Archetype(configuration.home());
	}

	public InvoiceHandlerBox(InvoiceHandlerConfiguration configuration) {
		super(configuration);
		this.archetype = new Archetype(configuration.home());
	}

	@Override
	public io.intino.alexandria.core.Box put(Object o) {
		super.put(o);
		return this;
	}

	public Archetype archetype() {
		return archetype;
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