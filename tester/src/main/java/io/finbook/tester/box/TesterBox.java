package io.finbook.tester.box;

import io.finbook.TextGenerator;
import io.finbook.datahub.events.Invoices;
import io.finbook.tester.Archetype;

import java.time.Instant;

public class TesterBox extends AbstractBox {

	private Archetype archetype;

	public TesterBox(String[] args) {
		super(args);
		TesterConfiguration configuration = new TesterConfiguration(args);
		this.archetype = new Archetype(configuration.home());
	}

	public TesterBox(TesterConfiguration configuration) {
		super(configuration);
		this.archetype = new Archetype(configuration.home());
	}

	@Override
	public io.intino.alexandria.core.Box put(Object o) {
		super.put(o);
		return this;
	}

	@Override
	public void beforeStart() {

	}

	@Override
	public void afterStart() {
		//Envío un evento

		Invoices invoices = new Invoices()
				.ts(Instant.now())
				.ss("Raul")
				.xml(TextGenerator.getBase64TextFrom("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><cfdi:Voucher Uuid=\"646464\" Concept=\"Bill of a eating.\" Currency=\"euro\" Date=\"Thu May 14 08:47:40 BST 2020\" Location=\"39032\" SubTotal=\"21.357588898891848\" TaxRate=\"0.32\" Total=\"28.19201734653724\" Type=\"income\" Use=\"G01\"><cfdi:Issuer IssuerName=\"el patio de lola\" IssuerRFC=\"1000000\"/><cfdi:Receiver ReceiverName=\"Julian Bell\" ReceiverRFC=\"3000008\"/></cfdi:Voucher>"));
		terminal().publish(invoices);
	}

	@Override
	public void beforeStop() {

	}

	@Override
	public void afterStop() {

	}
}