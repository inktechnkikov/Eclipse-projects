package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import de.bonprix.I18N;
import de.bonprix.forkjoinpool.RecursiveTaskLambda;
import de.bonprix.academy.common.enums.Mode;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event.InsertedApplicationGroupEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event.UpdatedApplicationGroupEvent;
import de.bonprix.global.masterdata.service.applicationgroup.ApplicationGroupService;
import de.bonprix.model.Paged;
import de.bonprix.vaadin.eventbus.EventBus;
import de.bonprix.vaadin.mvp.SpringPresenter;
import de.bonprix.vaadin.mvp.dialog.AbstractMvpDialogPresenter;
import de.bonprix.vaadin.provider.UiNotificationProvider;

/**
 * @author t.sologub
 */
@SpringPresenter
public class ApplicationGroupWindowPresenter
		extends AbstractMvpDialogPresenter<ApplicationGroupWindowView<ApplicationGroupWindowView.Presenter>>
		implements ApplicationGroupWindowView.Presenter {

	@Resource
	private EventBus localEventBus;

	@Resource
	private ApplicationGroupService applicationGroupService;

	@Resource
	private UiNotificationProvider notificationProvider;

	@Resource
	private ForkJoinPool forkJoinPool;

	private Mode mode;

	@PostConstruct
	public void init() {
		ForkJoinTask<List<ApplicationGroup>> taskApplicationGroups = new RecursiveTaskLambda<List<ApplicationGroup>>(
				() -> {
					return this.applicationGroupService.findAll(Paged.defaultPaged());
				});
		this.forkJoinPool.execute(taskApplicationGroups);

		try {
			getView().setApplicationGroups(taskApplicationGroups.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Couldn't load mandatory data for screen", e);
		}

		setMode(Mode.ADD);
		getView().setEntity(new ApplicationGroup());
	}

	private void setMode(Mode mode) {
		this.mode = mode;
		getView().setConfigurableHeadline(mode.getI18nKey());
	}

	@Override
	public void updateEntity(boolean isValid, ApplicationGroup applicationGroup) {
		if (!isValid) {
			this.notificationProvider.showErrorNotification(I18N.get("ERROR"), I18N.get("ERROR_ENTITY_NOT_VALID"));
			return;
		}

		switch (this.mode) {
		case ADD:
			long applicationGroupId = this.applicationGroupService.create(applicationGroup);
			applicationGroup.setId(applicationGroupId);
			this.localEventBus.fireEvent(new InsertedApplicationGroupEvent(applicationGroup));
			break;
		case EDIT:
			this.applicationGroupService.update(applicationGroup);
			this.localEventBus.fireEvent(new UpdatedApplicationGroupEvent(applicationGroup));
			break;
		default:
			break;
		}

		close();
	}

	public ApplicationGroupWindowPresenter withApplicationGroupId(Long applicationGroupId) {
		getView().setEntity(this.applicationGroupService.findById(applicationGroupId));
		setMode(Mode.EDIT);
		return this;
	}
}
