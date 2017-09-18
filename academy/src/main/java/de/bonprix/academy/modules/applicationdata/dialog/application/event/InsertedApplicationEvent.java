package de.bonprix.academy.modules.applicationdata.dialog.application.event;

import de.bonprix.global.masterdata.dto.Application;

public class InsertedApplicationEvent extends AbstractApplicationEvent {

	public InsertedApplicationEvent(Application application) {
		super(application);
	}

}
