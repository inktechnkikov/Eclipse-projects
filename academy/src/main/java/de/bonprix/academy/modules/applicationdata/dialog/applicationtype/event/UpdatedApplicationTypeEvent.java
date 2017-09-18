package de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event;

import de.bonprix.global.masterdata.dto.ApplicationType;

public class UpdatedApplicationTypeEvent extends AbstractApplicationTypeEvent {

	public UpdatedApplicationTypeEvent(ApplicationType applicationType) {
		super(applicationType);
	}

}
