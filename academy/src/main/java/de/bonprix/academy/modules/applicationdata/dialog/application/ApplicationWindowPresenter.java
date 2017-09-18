package de.bonprix.academy.modules.applicationdata.dialog.application;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import de.bonprix.I18N;
import de.bonprix.forkjoinpool.RecursiveTaskLambda;
import de.bonprix.academy.common.enums.Mode;
import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.global.masterdata.dto.builder.ApplicationBuilder;
import de.bonprix.academy.modules.applicationdata.dialog.application.event.InsertedApplicationEvent;
import de.bonprix.academy.modules.applicationdata.dialog.application.event.UpdatedApplicationEvent;
import de.bonprix.global.masterdata.service.application.ApplicationService;
import de.bonprix.global.masterdata.service.application.fetch.ApplicationFetchOptions;
import de.bonprix.global.masterdata.service.application.filter.ApplicationFilter;
import de.bonprix.global.masterdata.service.applicationgroup.ApplicationGroupService;
import de.bonprix.global.masterdata.service.applicationtype.ApplicationTypeService;
import de.bonprix.model.Paged;
import de.bonprix.vaadin.eventbus.EventBus;
import de.bonprix.vaadin.mvp.SpringPresenter;
import de.bonprix.vaadin.mvp.dialog.AbstractMvpDialogPresenter;
import de.bonprix.vaadin.provider.UiNotificationProvider;

/**
 * @author t.sologub
 */
@SpringPresenter
public class ApplicationWindowPresenter
		extends AbstractMvpDialogPresenter<ApplicationWindowView<ApplicationWindowView.Presenter>>
		implements ApplicationWindowView.Presenter {

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

	private Mode mode;

	@PostConstruct
	protected void init() throws InterruptedException {

		ForkJoinTask<List<Application>> taskApplications = new RecursiveTaskLambda<List<Application>>(() -> {
			return ApplicationWindowPresenter.this.applicationService.findAll(	new ApplicationFilter(),
																				new ApplicationFetchOptions().withFetchAll());
		});
		this.forkJoinPool.execute(taskApplications);

		ForkJoinTask<List<ApplicationType>> taskApplicationTypes = new RecursiveTaskLambda<List<ApplicationType>>(
				() -> {
					return ApplicationWindowPresenter.this.applicationTypeService.findAll(Paged.defaultPaged());
				});
		this.forkJoinPool.execute(taskApplicationTypes);

		ForkJoinTask<List<ApplicationGroup>> taskApplicationGroups = new RecursiveTaskLambda<List<ApplicationGroup>>(
				() -> {
					return ApplicationWindowPresenter.this.applicationGroupService.findAll(Paged.defaultPaged());
				});
		this.forkJoinPool.execute(taskApplicationGroups);

		try {
			getView().setApplications(taskApplications.get());
			getView().setApplicationTypes(taskApplicationTypes.get());
			getView().setApplicationGroups(taskApplicationGroups.get());
			setMode(Mode.ADD);
			getView().setEntity(new ApplicationBuilder().build());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Couldn't load mandatory data for screen", e);
		}

	}

	private void setMode(Mode mode) {
		this.mode = mode;
		getView().setConfigurableHeadline(mode.getI18nKey());
	}

	@Override
	public void updateEntity(boolean isValid, final Application application) {
		if (!isValid) {
			this.notificationProvider.showErrorNotification(I18N.get("ERROR"), I18N.get("ERROR_ENTITY_NOT_VALID"));
			return;
		}

		switch (this.mode) {
		case ADD:
			long applicationId = this.applicationService.create(application);
			application.setId(applicationId);
			this.localEventBus.fireEvent(new InsertedApplicationEvent(application));
			break;
		case EDIT:
			this.applicationService.update(application);
			this.localEventBus.fireEvent(new UpdatedApplicationEvent(application));
			break;
		default:
			break;
		}

		close();
	}

	public ApplicationWindowPresenter withApplicationId(Long applicationId) {
		getView().setEntity(this.applicationService.findById(	applicationId,
																new ApplicationFetchOptions().withFetchAll()));
		setMode(Mode.EDIT);
		return this;
	}
}
