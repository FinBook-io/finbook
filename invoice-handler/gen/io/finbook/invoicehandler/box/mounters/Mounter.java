package io.finbook.invoicehandler.box.mounters;

import io.intino.alexandria.event.Event;

public interface Mounter {
	void handle(Event event);
}