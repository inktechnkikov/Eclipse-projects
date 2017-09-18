package de.bonprix.academy.modules.applicationdata;

import java.util.List;

import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.vaadin.mvp.view.regular.MvpView;
import de.bonprix.vaadin.mvp.view.regular.MvpViewPresenter;

public interface ApplicationDataView extends MvpView {

	interface Presenter extends MvpViewPresenter<ApplicationDataView> {

		/**
		 * In the menu add application was clicked.
		 */
		void clickedMenuAddApplication();

		/**
		 * In the menu add application type was clicked.
		 */
		void clickedMenuAddApplicationType();

		/**
		 * In the menu add application group was clicked.
		 */
		void clickedMenuAddApplicationGroup();

		/**
		 * In the menu edit was clicked.
		 */
		void clickedMenuEdit();

		/**
		 * In the menu delete was clicked.
		 */
		void clickedMenuDelete();

		/**
		 * element in application grid was de-/selected.
		 * 
		 * @param application
		 *            selected item, can be null -> deselection
		 */
		void selectionChangedOnApplicationGrid(Application application);

		/**
		 * element in application type grid was de-/selected.
		 * 
		 * @param applicationType
		 *            selected item, can be null -> deselection
		 */
		void selectionChangedOnApplicationTypeGrid(ApplicationType applicationType);

		/**
		 * element in application group grid was de-/selected.
		 * 
		 * @param applicationGroup
		 *            selected item, can be null -> deselection
		 */
		void selectionChangedOnApplicationGroupGrid(ApplicationGroup applicationGroup);

	}

	/**
	 * replaces existing entries in application grid
	 * 
	 * @param applications
	 *            new applications for application grid
	 */
	void setApplications(List<Application> applications);

	/**
	 * replaces existing entries in application type grid
	 * 
	 * @param applicationTypes
	 *            new application types for application type grid
	 */
	void setApplicationTypes(List<ApplicationType> applicationTypes);

	/**
	 * replaces existing entries in application group grid
	 * 
	 * @param applicationGroups
	 *            new application groups for application group grid
	 */
	void setApplicationGroups(List<ApplicationGroup> applicationGroups);

	/**
	 * enables the edit and delete in the menu
	 */
	void enableMenuEditAndMenuDelete();

	/**
	 * disables the edit and delete in the menu
	 */
	void disableMenuEditAndMenuDelete();

	/**
	 * deselects all selected items in application grid
	 */
	void deselectApplicationGrid();

	/**
	 * deselects all selected items in application type grid
	 */
	void deselectApplicationTypeGrid();

	/**
	 * deselects all selected items in application group grid
	 */
	void deselectApplicationGroupGrid();

	/**
	 * updates application from application grid
	 * 
	 * @param application
	 *            application to be updated
	 */
	void updateApplicationFromGrid(Application application);

	/**
	 * updates application type from application type grid
	 * 
	 * @param applicationType
	 *            application type to be updated
	 */
	void updateApplicationTypeFromGrid(ApplicationType applicationType);

	/**
	 * updates application group from application group grid
	 * 
	 * @param applicationGroup
	 *            application group to be updated
	 */
	void updateApplicationGroupFromGrid(ApplicationGroup applicationGroup);

	/**
	 * new insert of application to application grid
	 * 
	 * @param application
	 *            new application
	 */
	void insertApplicationToGrid(Application application);

	/**
	 * new insert of application type to application type grid
	 * 
	 * @param applicationType
	 *            new application type
	 */
	void insertApplicationTypeToGrid(ApplicationType applicationType);

	/**
	 * new insert of application group to application group grid
	 * 
	 * @param applicationGroup
	 *            new application group
	 */
	void insertApplicationGroupToGrid(ApplicationGroup applicationGroup);

	/**
	 * removes application from application grid
	 * 
	 * @param application
	 *            application to be removed
	 */
	void removeApplicationFromGrid(Application application);

	/**
	 * removes application type from application type grid
	 * 
	 * @param applicationType
	 *            application type to be removed
	 */
	void removeApplicationTypeFromGrid(ApplicationType applicationType);

	/**
	 * removes application group from application group grid
	 * 
	 * @param applicationGroup
	 *            application group to be removed
	 */
	void removeApplicationGroupFromGrid(ApplicationGroup applicationGroup);

}