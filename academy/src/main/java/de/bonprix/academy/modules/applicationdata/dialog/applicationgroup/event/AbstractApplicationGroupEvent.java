package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event;

import de.bonprix.global.masterdata.dto.ApplicationGroup;

public abstract class AbstractApplicationGroupEvent {

	private final ApplicationGroup applicationGroup;

	public AbstractApplicationGroupEvent(ApplicationGroup applicationGroup) {
		this.applicationGroup = applicationGroup;
	}

	public ApplicationGroup getApplicationGroup() {
		return this.applicationGroup;
	}
}
