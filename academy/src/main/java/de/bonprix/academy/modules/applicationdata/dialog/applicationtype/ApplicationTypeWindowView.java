package de.bonprix.academy.modules.applicationdata.dialog.applicationtype;

import java.util.List;

import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.vaadin.mvp.dialog.MvpDialogPresenter;
import de.bonprix.vaadin.mvp.dialog.MvpDialogView;

/**
 * @author t.sologub
 */
public interface ApplicationTypeWindowView<PRESENTER extends ApplicationTypeWindowView.Presenter>
		extends MvpDialogView<PRESENTER> {

	interface Presenter extends MvpDialogPresenter {

		/**
		 * will create (no id) or update (has id) entity
		 * 
		 * @param isValid
		 *            if current entity is valid
		 * @param applicationType
		 *            to be created/updated entity
		 */
		void updateEntity(boolean isValid, ApplicationType applicationType);

	}

	/**
	 * sets the entity of the dialog to be edited
	 * 
	 * @param applicationType
	 *            entity to be edited in the dialog
	 */
	void setEntity(ApplicationType applicationType);

	/**
	 * sets the headline of the dialog
	 * 
	 * @param captionKey
	 */
	void setConfigurableHeadline(String captionKey);

	/**
	 * sets the application types for unique validation
	 * 
	 * @param applicationTypes
	 *            all existing application types
	 */
	void setApplicationTypes(List<ApplicationType> applicationTypes);

}