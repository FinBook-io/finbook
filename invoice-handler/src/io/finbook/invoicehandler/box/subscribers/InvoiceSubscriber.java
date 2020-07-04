package io.finbook.invoicehandler.box.subscribers;

import io.finbook.TextGenerator;
import io.finbook.datahub.events.ProcessedInvoice;
import io.finbook.invoicehandler.box.InvoiceHandlerBox;
import io.finbook.invoicehandler.box.helpers.XMLHelper;

public class InvoiceSubscriber implements java.util.function.Consumer<io.finbook.datahub.events.Invoice> {
	private InvoiceHandlerBox box;

	public InvoiceSubscriber(InvoiceHandlerBox box) {
		this.box = box;
	}

	public void accept(io.finbook.datahub.events.Invoice event) {
		String xml = TextGenerator.getAsciiTextFrom(event.xml());
		XMLHelper xmlHelper = new XMLHelper(event, xml);
		ProcessedInvoice processedInvoice = xmlHelper.getProcessedInvoice();
		box.terminal().publish(processedInvoice);
	}
}