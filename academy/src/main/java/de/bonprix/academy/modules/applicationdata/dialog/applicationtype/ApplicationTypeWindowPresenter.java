package de.bonprix.academy.modules.applicationdata.dialog.applicationtype;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import de.bonprix.I18N;
import de.bonprix.forkjoinpool.RecursiveTaskLambda;
import de.bonprix.academy.common.enums.Mode;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event.InsertedApplicationTypeEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event.UpdatedApplicationTypeEvent;
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
public class ApplicationTypeWindowPresenter
		extends AbstractMvpDialogPresenter<ApplicationTypeWindowView<ApplicationTypeWindowView.Presenter>>
		implements ApplicationTypeWindowView.Presenter {

	@Resource
	private EventBus localEventBus;

	@Resource
	private ApplicationTypeService applicationTypeService;

	@Resource
	private UiNotificationProvider notificationProvider;

	@Resource
	private ForkJoinPool forkJoinPool;

	private Mode mode;

	@PostConstruct
	public void init() {
		ForkJoinTask<List<ApplicationType>> taskApplicationTypes = new RecursiveTaskLambda<List<ApplicationType>>(
				() -> {
					return this.applicationTypeService.findAll(Paged.defaultPaged());
				});
		this.forkJoinPool.execute(taskApplicationTypes);

		try {
			getView().setApplicationTypes(taskApplicationTypes.get());
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException("Couldn't load mandatory data for screen", e);
		}

		setMode(Mode.ADD);
		getView().setEntity(new ApplicationType());
	}

	private void setMode(Mode mode) {
		this.mode = mode;
		getView().setConfigurableHeadline(mode.getI18nKey());
	}

	@Override
	public void updateEntity(boolean isValid, ApplicationType applicationType) {
		if (!isValid) {
			this.notificationProvider.showErrorNotification(I18N.get("ERROR"), I18N.get("ERROR_ENTITY_NOT_VALID"));
			return;
		}

		switch (this.mode) {
		case ADD:
			long applicationTypeId = this.applicationTypeService.create(applicationType);
			applicationType.setId(applicationTypeId);
			this.localEventBus.fireEvent(new InsertedApplicationTypeEvent(applicationType));
			break;
		case EDIT:
			this.applicationTypeService.update(applicationType);
			this.localEventBus.fireEvent(new UpdatedApplicationTypeEvent(applicationType));
			break;
		default:
			break;
		}

		close();
	}

	public ApplicationTypeWindowPresenter withApplicationTypeId(Long applicationTypeId) {
		getView().setEntity(this.applicationTypeService.findById(applicationTypeId));
		setMode(Mode.EDIT);
		return this;
	}
}
