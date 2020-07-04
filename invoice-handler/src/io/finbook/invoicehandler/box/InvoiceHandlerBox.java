package io.finbook.invoicehandler.box;

public class InvoiceHandlerBox extends AbstractBox {

	public InvoiceHandlerBox(String[] args) {
		super(args);
	}

	public InvoiceHandlerBox(InvoiceHandlerConfiguration configuration) {
		super(configuration);
	}

	@Override
	public io.intino.alexandria.core.Box put(Object o) {
		super.put(o);
		return this;
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