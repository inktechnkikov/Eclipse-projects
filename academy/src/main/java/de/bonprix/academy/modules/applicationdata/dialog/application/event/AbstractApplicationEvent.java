package de.bonprix.academy.modules.applicationdata.dialog.application.event;

import de.bonprix.global.masterdata.dto.Application;

public abstract class AbstractApplicationEvent {

	private final Application application;

	public AbstractApplicationEvent(Application application) {
		this.application = application;
	}

	public Application getApplication() {
		return this.application;
	}
}
