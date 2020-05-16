package io.finbook.invoicehandler.box.graph.mounters;

import io.finbook.invoicehandler.box.InvoiceHandlerBox;
import io.finbook.invoicehandler.box.mounters.Mounter;
import io.intino.alexandria.event.Event;

public class InvoiceHandler implements Mounter {

	private InvoiceHandlerBox box;

	public InvoiceHandler(InvoiceHandlerBox box) {
		this.box = box;
	}

	public void handle(io.finbook.datahub.events.Invoices event) {

	}

	public void handle(Event event) {
		if	(event instanceof io.finbook.datahub.events.Invoices) handle((io.finbook.datahub.events.Invoices) event);
	}
}