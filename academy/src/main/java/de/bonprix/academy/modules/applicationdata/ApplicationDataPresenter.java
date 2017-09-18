package de.bonprix.academy.modules.applicationdata;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import javax.annotation.Resource;

import de.bonprix.I18N;
import de.bonprix.forkjoinpool.RecursiveTaskLambda;
import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.academy.modules.applicationdata.dialog.application.ApplicationWindowPresenter;
import de.bonprix.academy.modules.applicationdata.dialog.application.event.InsertedApplicationEvent;
import de.bonprix.academy.modules.applicationdata.dialog.application.event.UpdatedApplicationEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.ApplicationGroupWindowPresenter;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event.InsertedApplicationGroupEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event.UpdatedApplicationGroupEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.ApplicationTypeWindowPresenter;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event.InsertedApplicationTypeEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event.UpdatedApplicationTypeEvent;
import de.bonprix.global.masterdata.service.application.ApplicationService;
import de.bonprix.global.masterdata.service.application.fetch.ApplicationFetchOptions;
import de.bonprix.global.masterdata.service.application.filter.ApplicationFilter;
import de.bonprix.global.masterdata.service.applicationgroup.ApplicationGroupService;
import de.bonprix.global.masterdata.service.applicationtype.ApplicationTypeService;
import de.bonprix.model.Paged;
import de.bonprix.vaadin.eventbus.EventBus;
import de.bonprix.vaadin.eventbus.EventHandler;
import de.bonprix.vaadin.mvp.SpringPresenter;
import de.bonprix.vaadin.mvp.view.regular.AbstractMvpViewPresenter;
import de.bonprix.vaadin.provider.UiNotificationProvider;

/**
 * @author t.sologub
 */
@SpringPresenter
public class ApplicationDataPresenter extends AbstractMvpViewPresenter<ApplicationDataView>
		implements ApplicationDataView.Presenter {

	@Resource
	private EventBus localEventBus;

	@Resource
	private ApplicationService applicationService;

	@Resource
	private ApplicationTypeService applicationTypeService;

	@Resource
	private ApplicationGroupService applicationGroupService;

	@Resource
	private UiNotificationProvider notificationProvider;

	@Resource
	private ForkJoinPool forkJoinPool;

	private ApplicationDataGrids currentSelectedApplicationDataGrid;
	private Object currentSelectedItem;

	/**
	 * enum for containing each grid on the view
	 */
	private enum ApplicationDataGrids {
		APPLICATION, APPLICATION_TYPE, APPLICATION_GROUP, NONE;
	}

	@Override
	public void init() {
		this.localEventBus.addHandler(this);

		ForkJoinTask<List<Application>> taskApplications = new RecursiveTaskLambda<List<Application>>(() -> {
			return this.applicationService.findAll(	new ApplicationFilter(),
													new ApplicationFetchOptions().withFetchAll());
		});
		this.forkJoinPool.execute(taskApplications);

		ForkJoinTask<List<ApplicationType>> taskApplicationTypes = new RecursiveTaskLambda<List<ApplicationType>>(
				() -> {
					return this.applicationTypeService.findAll(Paged.defaultPaged());
				});
		this.forkJoinPool.execute(taskApplicationTypes);

		ForkJoinTask<List<ApplicationGroup>> taskApplicationGroups = new RecursiveTaskLambda<List<ApplicationGroup>>(
				() -> {
					return this.applicationGroupService.findAll(Paged.defaultPaged());
				});
		this.forkJoinPool.execute(taskApplicationGroups);

		try {
			getView().setApplications(taskApplications.get());
			getView().setApplicationTypes(taskApplicationTypes.get());
			getView().setApplicationGroups(taskApplicationGroups.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Couldn't load mandatory data for screen", e);
		}
	}

	@Override
	public void onViewEnter() {
	}

	@Override
	public void clickedMenuAddApplication() {
		createPresenter(ApplicationWindowPresenter.class).open();
	}

	@EventHandler
	public void insertedApplicationEvent(InsertedApplicationEvent event) {
		getView().insertApplicationToGrid(event.getApplication());
	}

	@Override
	public void clickedMenuAddApplicationType() {
		createPresenter(ApplicationTypeWindowPresenter.class).open();
	}

	@EventHandler
	public void insertedApplicationTypeEvent(InsertedApplicationTypeEvent event) {
		getView().insertApplicationTypeToGrid(event.getApplicationType());
	}

	@Override
	public void clickedMenuAddApplicationGroup() {
		createPresenter(ApplicationGroupWindowPresenter.class).open();
	}

	@EventHandler
	public void insertedApplicationGroupEvent(InsertedApplicationGroupEvent event) {
		getView().insertApplicationGroupToGrid(event.getApplicationGroup());
	}

	@Override
	public void clickedMenuEdit() {
		switch (this.currentSelectedApplicationDataGrid) {
		case APPLICATION:
			clickedMenuEditApplication((Application) this.currentSelectedItem);
			break;
		case APPLICATION_TYPE:
			clickedMenuEditApplicationType((ApplicationType) this.currentSelectedItem);
			break;
		case APPLICATION_GROUP:
			clickedMenuEditApplicationGroup((ApplicationGroup) this.currentSelectedItem);
			break;
		case NONE:
			break;
		}
	}

	private void clickedMenuEditApplication(Application application) {
		createPresenter(ApplicationWindowPresenter.class)	.withApplicationId(application.getId())
															.open();
	}

	@EventHandler
	public void updatedApplicationEvent(UpdatedApplicationEvent event) {
		getView().updateApplicationFromGrid(event.getApplication());
	}

	private void clickedMenuEditApplicationType(ApplicationType applicationType) {
		createPresenter(ApplicationTypeWindowPresenter.class)	.withApplicationTypeId(applicationType.getId())
																.open();
	}

	@EventHandler
	public void updatedApplicationTypeEvent(UpdatedApplicationTypeEvent event) {
		getView().updateApplicationTypeFromGrid(event.getApplicationType());
	}

	private void clickedMenuEditApplicationGroup(ApplicationGroup applicationGroup) {
		createPresenter(ApplicationGroupWindowPresenter.class)	.withApplicationGroupId(applicationGroup.getId())
																.open();
	}

	@EventHandler
	public void updatedApplicationGroupEvent(UpdatedApplicationGroupEvent event) {
		getView().updateApplicationGroupFromGrid(event.getApplicationGroup());
	}

	@Override
	public void clickedMenuDelete() {
		switch (this.currentSelectedApplicationDataGrid) {
		case APPLICATION:
			clickedMenuDeleteApplication((Application) this.currentSelectedItem);
			break;
		case APPLICATION_TYPE:
			clickedMenuDeleteApplicationType((ApplicationType) this.currentSelectedItem);
			break;
		case APPLICATION_GROUP:
			clickedMenuDeleteApplicationGroup((ApplicationGroup) this.currentSelectedItem);
			break;
		default:
			break;
		}
	}

	private void clickedMenuDeleteApplication(Application application) {
		this.notificationProvider.showQuestionMessageBox(	I18N.get("WARNING_DELETE") + " " + application.getName(),
															() -> {
																this.applicationService.deleteById(application.getId());
																getView().removeApplicationFromGrid(application);
															});
	}

	private void clickedMenuDeleteApplicationType(ApplicationType applicationType) {
		this.notificationProvider.showQuestionMessageBox(I18N.get("WARNING_DELETE") + " "
				+ applicationType.getNameKey(), () -> {
					this.applicationTypeService.deleteById(applicationType.getId());
					getView().removeApplicationTypeFromGrid(applicationType);
				});
	}

	private void clickedMenuDeleteApplicationGroup(ApplicationGroup applicationGroup) {
		this.notificationProvider.showQuestionMessageBox(I18N.get("WARNING_DELETE") + " "
				+ applicationGroup.getNameKey(), () -> {
					this.applicationGroupService.deleteById(applicationGroup.getId());
					getView().removeApplicationGroupFromGrid(applicationGroup);
				});
	}

	@Override
	public void selectionChangedOnApplicationGrid(Application application) {
		selectionChangedOnGrid(ApplicationDataGrids.APPLICATION, application);
	}

	@Override
	public void selectionChangedOnApplicationTypeGrid(ApplicationType applicationType) {
		selectionChangedOnGrid(ApplicationDataGrids.APPLICATION_TYPE, applicationType);
	}

	@Override
	public void selectionChangedOnApplicationGroupGrid(ApplicationGroup applicationGroup) {
		selectionChangedOnGrid(ApplicationDataGrids.APPLICATION_GROUP, applicationGroup);
	}

	/**
	 * Sets the selected object in the presenter and deselects all other grids
	 * on the view
	 * 
	 * @param applicationDataGrid
	 *            table that the selection happened on
	 * @param selectedItemId
	 *            selected item of the grid, can be null -> deselection
	 */
	private void selectionChangedOnGrid(ApplicationDataGrids applicationDataGrid, Object selectedItem) {
		if (selectedItem == null) {
			this.currentSelectedApplicationDataGrid = ApplicationDataGrids.NONE;
			this.currentSelectedItem = null;
			getView().disableMenuEditAndMenuDelete();
			return;
		}

		if (!ApplicationDataGrids.APPLICATION.equals(applicationDataGrid)) {
			getView().deselectApplicationGrid();
		}
		if (!ApplicationDataGrids.APPLICATION_TYPE.equals(applicationDataGrid)) {
			getView().deselectApplicationTypeGrid();
		}
		if (!ApplicationDataGrids.APPLICATION_GROUP.equals(applicationDataGrid)) {
			getView().deselectApplicationGroupGrid();
		}

		getView().enableMenuEditAndMenuDelete();
		this.currentSelectedApplicationDataGrid = applicationDataGrid;
		this.currentSelectedItem = selectedItem;
	}

}
