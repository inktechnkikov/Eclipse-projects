package de.bonprix.academy.modules.applicationdata.dialog.application.event;

import de.bonprix.global.masterdata.dto.Application;

public class UpdatedApplicationEvent extends AbstractApplicationEvent {

	public UpdatedApplicationEvent(Application application) {
		super(application);
	}

}
