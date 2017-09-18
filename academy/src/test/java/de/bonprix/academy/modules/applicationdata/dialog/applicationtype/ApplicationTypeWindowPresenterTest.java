package de.bonprix.academy.modules.applicationdata.dialog.applicationtype;

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
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.global.masterdata.dto.builder.ApplicationTypeBuilder;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event.InsertedApplicationTypeEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.event.UpdatedApplicationTypeEvent;
import de.bonprix.global.masterdata.service.applicationtype.ApplicationTypeService;
import de.bonprix.model.Paged;
import de.bonprix.vaadin.eventbus.EventBus;
import de.bonprix.vaadin.provider.UiDialogProvider;
import de.bonprix.vaadin.provider.UiNotificationProvider;

public class ApplicationTypeWindowPresenterTest extends I18NAwareUnitTest {

	@InjectMocks
	private ApplicationTypeWindowPresenter applicationTypeWindowPresenter;

	@Mock
	private EventBus mockLocalEventBus;

	@Mock
	private ApplicationTypeWindowView<ApplicationTypeWindowPresenter> mockApplicationTypeWindowView;

	@Mock
	private ApplicationTypeService mockApplicationTypeService;

	@Mock
	private UiNotificationProvider mockNotificationProvider;

	@Mock
	private UiDialogProvider mockDialogProvider;

	@Spy
	private ForkJoinPool forkJoinPool;

	@Test
	public void initTest() {
		List<ApplicationType> applicationTypes = new ArrayList<>();
		Mockito	.when(this.mockApplicationTypeService.findAll(Mockito.any(Paged.class)))
				.thenReturn(applicationTypes);

		Mockito	.verify(this.mockApplicationTypeService)
				.findAll(Mockito.any(Paged.class));
		Mockito	.verify(this.mockApplicationTypeWindowView)
				.setApplicationTypes(applicationTypes);

		Mockito	.verify(this.mockApplicationTypeWindowView)
				.setConfigurableHeadline(Mode.ADD.getI18nKey());

		Mockito	.verify(this.mockApplicationTypeWindowView)
				.setEntity(Mockito.any(ApplicationType.class));
	}

	@Test
	public void withCountryIdTest() {
		Long applicationTypeId = 1L;
		ApplicationType applicationType = new ApplicationTypeBuilder()	.withId(applicationTypeId)
																		.build();
		Mockito	.when(this.mockApplicationTypeService.findById(applicationTypeId))
				.thenReturn(applicationType);

		this.applicationTypeWindowPresenter.withApplicationTypeId(applicationTypeId);

		Mockito	.verify(this.mockApplicationTypeService)
				.findById(applicationTypeId);
		Mockito	.verify(this.mockApplicationTypeWindowView)
				.setEntity(applicationType);

		Mockito	.verify(this.mockApplicationTypeWindowView)
				.setConfigurableHeadline(Mode.EDIT.getI18nKey());
	}

	@Test
	public void updateEntityNotValidTest() {
		this.applicationTypeWindowPresenter.updateEntity(false, new ApplicationType());

		// should be called
		Mockito	.verify(this.mockNotificationProvider)
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());

		// should not be called
		Mockito	.verify(this.mockApplicationTypeService, Mockito.never())
				.update(Mockito.any());
		Mockito	.verify(this.mockLocalEventBus, Mockito.never())
				.fireEvent(Mockito.any());
	}

	@Test
	public void updateEntityModeAddTest() {
		ApplicationType applicationType = new ApplicationType();

		ApplicationType savedApplicationType = new ApplicationTypeBuilder()	.withId(1L)
																			.build();
		Mockito	.when(this.mockApplicationTypeService.create(applicationType))
				.thenReturn(savedApplicationType.getId());

		this.applicationTypeWindowPresenter.updateEntity(true, applicationType);

		// should not be called
		Mockito	.verify(this.mockNotificationProvider, Mockito.never())
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());
		Mockito	.verify(this.mockApplicationTypeService, Mockito.never())
				.update(Mockito.any());

		// should be called
		Mockito	.verify(this.mockApplicationTypeService)
				.create(applicationType);

		ArgumentCaptor<InsertedApplicationTypeEvent> insertedCountryEventCaptor = ArgumentCaptor.forClass(InsertedApplicationTypeEvent.class);
		Mockito	.verify(this.mockLocalEventBus)
				.fireEvent(insertedCountryEventCaptor.capture());
		InsertedApplicationTypeEvent capturedInsertedCountryEvent = insertedCountryEventCaptor.getValue();
		Assert.assertEquals(capturedInsertedCountryEvent.getApplicationType(), savedApplicationType);

		Mockito	.verify(this.mockDialogProvider)
				.closeCurrentDialog();
	}

	@Test
	public void updateEntityModeEditTest() {
		ApplicationType applicationType = new ApplicationTypeBuilder()	.withId(1L)
																		.build();

		ApplicationType updatedApplicationType = new ApplicationTypeBuilder()	.withId(1L)
																				.build();

		this.applicationTypeWindowPresenter.withApplicationTypeId(1L);
		this.applicationTypeWindowPresenter.updateEntity(true, applicationType);

		// should not be called
		Mockito	.verify(this.mockNotificationProvider, Mockito.never())
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());
		Mockito	.verify(this.mockApplicationTypeService, Mockito.never())
				.create(Mockito.any());

		// should be called
		Mockito	.verify(this.mockApplicationTypeService)
				.update(applicationType);

		ArgumentCaptor<UpdatedApplicationTypeEvent> updatedCountryEventCaptor = ArgumentCaptor.forClass(UpdatedApplicationTypeEvent.class);
		Mockito	.verify(this.mockLocalEventBus)
				.fireEvent(updatedCountryEventCaptor.capture());
		UpdatedApplicationTypeEvent capturedUpdatedCountryEvent = updatedCountryEventCaptor.getValue();
		Assert.assertEquals(capturedUpdatedCountryEvent.getApplicationType(), updatedApplicationType);

		Mockito	.verify(this.mockDialogProvider)
				.closeCurrentDialog();
	}

}
