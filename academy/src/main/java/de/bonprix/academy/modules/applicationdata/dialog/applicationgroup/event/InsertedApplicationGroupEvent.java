package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event;

import de.bonprix.global.masterdata.dto.ApplicationGroup;

public class InsertedApplicationGroupEvent extends AbstractApplicationGroupEvent {

	public InsertedApplicationGroupEvent(ApplicationGroup applicationGroup) {
		super(applicationGroup);
	}

}
