package de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event;

import de.bonprix.global.masterdata.dto.ApplicationType;

public class InsertedApplicationTypeEvent extends AbstractApplicationTypeEvent {

	public InsertedApplicationTypeEvent(ApplicationType applicationType) {
		super(applicationType);
	}

}
