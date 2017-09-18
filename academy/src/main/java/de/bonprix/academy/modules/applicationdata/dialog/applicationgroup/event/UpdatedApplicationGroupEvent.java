package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event;

import de.bonprix.global.masterdata.dto.ApplicationGroup;

public class UpdatedApplicationGroupEvent extends AbstractApplicationGroupEvent {

	public UpdatedApplicationGroupEvent(ApplicationGroup applicationGroup) {
		super(applicationGroup);
	}

}
