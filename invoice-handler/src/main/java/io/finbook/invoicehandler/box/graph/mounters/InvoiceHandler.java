package io.finbook.invoicehandler.box.graph.mounters;

import io.finbook.TextGenerator;
import io.finbook.datahub.events.ProcessedInvoices;
import io.finbook.invoicehandler.box.InvoiceHandlerBox;
import io.finbook.invoicehandler.box.helpers.XMLHelper;
import io.intino.alexandria.event.Event;

import io.finbook.invoicehandler.box.mounters.Mounter;

public class InvoiceHandler implements Mounter {

	private InvoiceHandlerBox box;

	public InvoiceHandler(InvoiceHandlerBox box) {
		this.box = box;
	}

	public void handle(io.finbook.datahub.events.Invoices event) {
		XMLHelper xmlHelper = new XMLHelper(TextGenerator.getAsciiTextFrom(event.xml()));
		ProcessedInvoices processedInvoice = xmlHelper.getProcessedInvoice();
		box.terminal().publish(processedInvoice);
	}

	public void handle(Event event) {
		if	(event instanceof io.finbook.datahub.events.Invoices) handle((io.finbook.datahub.events.Invoices) event);
	}
}