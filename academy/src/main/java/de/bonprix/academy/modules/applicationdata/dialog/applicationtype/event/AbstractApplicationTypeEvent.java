package de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event;

import de.bonprix.global.masterdata.dto.ApplicationType;

public abstract class AbstractApplicationTypeEvent {

	private final ApplicationType applicationType;

	public AbstractApplicationTypeEvent(ApplicationType applicationType) {
		this.applicationType = applicationType;
	}

	public ApplicationType getApplicationType() {
		return this.applicationType;
	}
}
