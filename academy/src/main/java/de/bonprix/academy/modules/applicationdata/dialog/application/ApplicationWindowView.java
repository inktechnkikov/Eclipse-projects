package de.bonprix.academy.modules.applicationdata.dialog.application;

import java.util.List;

import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.vaadin.mvp.dialog.MvpDialogPresenter;
import de.bonprix.vaadin.mvp.dialog.MvpDialogView;

/**
 * @author t.sologub
 */
public interface ApplicationWindowView<PRESENTER extends ApplicationWindowView.Presenter>
		extends MvpDialogView<PRESENTER> {

	interface Presenter extends MvpDialogPresenter {

		/**
		 * will create (no id) or update (has id) entity
		 * 
		 * @param isValid
		 *            if current entity is valid
		 * @param application
		 *            to be created/updated entity
		 */
		void updateEntity(boolean isValid, Application application);

	}

	/**
	 * sets the entity of the dialog to be edited
	 * 
	 * @param application
	 *            entity to be edited in the dialog
	 */
	void setEntity(Application application);

	/**
	 * sets the applications for unique validation
	 * 
	 * @param applications
	 *            all existing applications
	 */
	void setApplications(List<Application> applications);

	/**
	 * sets the options for the application type combobox
	 * 
	 * @param applicationTypes
	 *            options
	 */
	void setApplicationTypes(List<ApplicationType> applicationTypes);

	/**
	 * sets the options for the application group combobox
	 * 
	 * @param applicationGroups
	 *            options
	 */
	void setApplicationGroups(List<ApplicationGroup> applicationGroups);

	/**
	 * sets the headline of the dialog
	 * 
	 * @param captionKey
	 */
	void setConfigurableHeadline(String captionKey);

}