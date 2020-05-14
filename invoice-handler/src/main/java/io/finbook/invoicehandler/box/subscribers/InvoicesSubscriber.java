package io.finbook.invoicehandler.box.subscribers;

import io.finbook.invoicehandler.box.InvoiceHandlerBox;

public class InvoicesSubscriber implements java.util.function.Consumer<io.finbook.datahub.events.Invoices> {
	private InvoiceHandlerBox box;

	public InvoicesSubscriber(InvoiceHandlerBox box) {
		this.box = box;
	}

	public void accept(io.finbook.datahub.events.Invoices event) {

	}
}