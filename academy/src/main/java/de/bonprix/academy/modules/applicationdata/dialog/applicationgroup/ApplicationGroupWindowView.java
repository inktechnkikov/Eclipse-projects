package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup;

import java.util.List;

import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.vaadin.mvp.dialog.MvpDialogPresenter;
import de.bonprix.vaadin.mvp.dialog.MvpDialogView;

/**
 * @author t.sologub
 */
public interface ApplicationGroupWindowView<PRESENTER extends ApplicationGroupWindowView.Presenter>
		extends MvpDialogView<PRESENTER> {

	interface Presenter extends MvpDialogPresenter {

		/**
		 * will create (no id) or update (has id) entity
		 * 
		 * @param isValid
		 *            if current entity is valid
		 * @param applicationGroup
		 *            to be created/updated entity
		 */
		void updateEntity(boolean isValid, ApplicationGroup applicationGroup);

	}

	/**
	 * sets the entity of the dialog to be edited
	 * 
	 * @param applicationGroup
	 *            entity to be edited in the dialog
	 */
	void setEntity(ApplicationGroup applicationGroup);

	/**
	 * sets the headline of the dialog
	 * 
	 * @param captionKey
	 */
	void setConfigurableHeadline(String captionKey);

	/**
	 * sets the application groups for unique validation
	 * 
	 * @param applicationGroups
	 *            all existing application groups
	 */
	void setApplicationGroups(List<ApplicationGroup> applicationGroups);

}