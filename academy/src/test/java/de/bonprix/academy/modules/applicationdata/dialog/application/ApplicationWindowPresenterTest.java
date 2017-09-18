package de.bonprix.academy.modules.applicationdata.dialog.application;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.Test;

import de.bonprix.I18NAwareUnitTest;
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
import de.bonprix.vaadin.provider.UiDialogProvider;
import de.bonprix.vaadin.provider.UiNotificationProvider;

public class ApplicationWindowPresenterTest extends I18NAwareUnitTest {

	@InjectMocks
	private ApplicationWindowPresenter applicationWindowPresenter;

	@Mock
	private EventBus mockLocalEventBus;

	@Mock
	private ApplicationWindowView<ApplicationWindowPresenter> mockApplicationWindowView;

	@Mock
	private ApplicationService mockApplicationService;

	@Mock
	private ApplicationTypeService mockApplicationTypeService;

	@Mock
	private ApplicationGroupService mockApplicationGroupService;

	@Mock
	private UiNotificationProvider mockNotificationProvider;

	@Mock
	private UiDialogProvider mockDialogProvider;

	@Spy
	private ForkJoinPool forkJoinPool;

	@Test
	public void initTest() {
		List<Application> applications = new ArrayList<>();
		Mockito	.when(this.mockApplicationService.findAll(	Mockito.any(ApplicationFilter.class),
															Mockito.any(ApplicationFetchOptions.class)))
				.thenReturn(applications);
		List<ApplicationType> applicationTypes = new ArrayList<>();
		Mockito	.when(this.mockApplicationTypeService.findAll(Mockito.any(Paged.class)))
				.thenReturn(applicationTypes);
		List<ApplicationGroup> applicationGroups = new ArrayList<>();
		Mockito	.when(this.mockApplicationGroupService.findAll(Mockito.any(Paged.class)))
				.thenReturn(applicationGroups);

		Mockito	.verify(this.mockApplicationService)
				.findAll(Mockito.any(ApplicationFilter.class), Mockito.any(ApplicationFetchOptions.class));
		Mockito	.verify(this.mockApplicationWindowView)
				.setApplications(applications);
		Mockito	.verify(this.mockApplicationTypeService)
				.findAll(Mockito.any(Paged.class));
		Mockito	.verify(this.mockApplicationWindowView)
				.setApplicationTypes(applicationTypes);
		Mockito	.verify(this.mockApplicationGroupService)
				.findAll(Mockito.any(Paged.class));
		Mockito	.verify(this.mockApplicationWindowView)
				.setApplicationGroups(applicationGroups);

		Mockito	.verify(this.mockApplicationWindowView)
				.setConfigurableHeadline(Mode.ADD.getI18nKey());

		Mockito	.verify(this.mockApplicationWindowView)
				.setEntity(Mockito.any(Application.class));

	}

	@Test
	public void withApplicationIdTest() {
		Long applicationId = 1L;
		Application application = new ApplicationBuilder()	.withId(applicationId)
															.build();
		Mockito	.when(this.mockApplicationService.findById(	Mockito.anyLong(),
															Mockito.any(ApplicationFetchOptions.class)))
				.thenReturn(application);

		this.applicationWindowPresenter.withApplicationId(applicationId);

		Mockito	.verify(this.mockApplicationService)
				.findById(Mockito.same(applicationId), Mockito.any(ApplicationFetchOptions.class));
		Mockito	.verify(this.mockApplicationWindowView)
				.setEntity(application);

		Mockito	.verify(this.mockApplicationWindowView)
				.setConfigurableHeadline(Mode.EDIT.getI18nKey());
	}

	@Test
	public void updateEntityNotValidTest() {
		this.applicationWindowPresenter.updateEntity(false, new Application());

		// should be called
		Mockito	.verify(this.mockNotificationProvider)
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());

		// should not be called
		Mockito	.verify(this.mockApplicationService, Mockito.never())
				.update(Mockito.any());
		Mockito	.verify(this.mockLocalEventBus, Mockito.never())
				.fireEvent(Mockito.any());
	}

	@Test
	public void updateEntityModeAddTest() {
		Application application = new Application();

		Application savedApplication = new ApplicationBuilder()	.withId(1L)
																.build();
		Mockito	.when(this.mockApplicationService.create(application))
				.thenReturn(savedApplication.getId());

		this.applicationWindowPresenter.updateEntity(true, application);

		// should not be called
		Mockito	.verify(this.mockNotificationProvider, Mockito.never())
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());
		Mockito	.verify(this.mockApplicationService, Mockito.never())
				.update(Mockito.any());

		// should be called
		Mockito	.verify(this.mockApplicationService)
				.create(application);

		ArgumentCaptor<InsertedApplicationEvent> insertedApplicationEventCaptor = ArgumentCaptor.forClass(InsertedApplicationEvent.class);
		Mockito	.verify(this.mockLocalEventBus)
				.fireEvent(insertedApplicationEventCaptor.capture());
		InsertedApplicationEvent capturedInsertedApplicationEvent = insertedApplicationEventCaptor.getValue();
		Assert.assertEquals(capturedInsertedApplicationEvent.getApplication(), savedApplication);

		Mockito	.verify(this.mockDialogProvider)
				.closeCurrentDialog();
	}

	@Test
	public void updateEntityModeEditTest() {
		Application application = new ApplicationBuilder()	.withId(1L)
															.build();

		Application updatedApplication = new ApplicationBuilder()	.withId(1L)
																	.build();

		this.applicationWindowPresenter.withApplicationId(1L);
		this.applicationWindowPresenter.updateEntity(true, application);

		// should not be called
		Mockito	.verify(this.mockNotificationProvider, Mockito.never())
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());
		Mockito	.verify(this.mockApplicationService, Mockito.never())
				.create(Mockito.any());

		// should be called
		Mockito	.verify(this.mockApplicationService)
				.update(application);

		ArgumentCaptor<UpdatedApplicationEvent> updatedApplicationEventCaptor = ArgumentCaptor.forClass(UpdatedApplicationEvent.class);
		Mockito	.verify(this.mockLocalEventBus)
				.fireEvent(updatedApplicationEventCaptor.capture());
		UpdatedApplicationEvent capturedUpdatedApplicationEvent = updatedApplicationEventCaptor.getValue();
		Assert.assertEquals(capturedUpdatedApplicationEvent.getApplication(), updatedApplication);

		Mockito	.verify(this.mockDialogProvider)
				.closeCurrentDialog();
	}

}
