package io.finbook.invoicehandler.box;

public class Main {

	public static void main(String[] args) {
		InvoiceHandlerConfiguration configuration = new InvoiceHandlerConfiguration(args);
		InvoiceHandlerBox box = new InvoiceHandlerBox(configuration);

		box.start();
		Runtime.getRuntime().addShutdownHook(new Thread(box::stop));
	}
}