package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup;

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
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.builder.ApplicationGroupBuilder;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event.InsertedApplicationGroupEvent;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.event.UpdatedApplicationGroupEvent;
import de.bonprix.global.masterdata.service.applicationgroup.ApplicationGroupService;
import de.bonprix.model.Paged;
import de.bonprix.vaadin.eventbus.EventBus;
import de.bonprix.vaadin.provider.UiDialogProvider;
import de.bonprix.vaadin.provider.UiNotificationProvider;

public class ApplicationGroupWindowPresenterTest extends I18NAwareUnitTest {

	@InjectMocks
	private ApplicationGroupWindowPresenter applicationGroupWindowPresenter;

	@Mock
	private EventBus mockLocalEventBus;

	@Mock
	private ApplicationGroupWindowView<ApplicationGroupWindowPresenter> mockApplicationGroupWindowView;

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
		List<ApplicationGroup> applicationGroups = new ArrayList<>();
		Mockito	.when(this.mockApplicationGroupService.findAll(Mockito.any(Paged.class)))
				.thenReturn(applicationGroups);

		Mockito	.verify(this.mockApplicationGroupService)
				.findAll(Mockito.any(Paged.class));
		Mockito	.verify(this.mockApplicationGroupWindowView)
				.setApplicationGroups(applicationGroups);

		Mockito	.verify(this.mockApplicationGroupWindowView)
				.setConfigurableHeadline(Mode.ADD.getI18nKey());

		Mockito	.verify(this.mockApplicationGroupWindowView)
				.setEntity(Mockito.any(ApplicationGroup.class));
	}

	@Test
	public void withCountryIdTest() {
		Long applicationGroupId = 1L;
		ApplicationGroup applicationGroup = new ApplicationGroupBuilder()	.withId(applicationGroupId)
																			.build();
		Mockito	.when(this.mockApplicationGroupService.findById(applicationGroupId))
				.thenReturn(applicationGroup);

		this.applicationGroupWindowPresenter.withApplicationGroupId(applicationGroupId);

		Mockito	.verify(this.mockApplicationGroupService)
				.findById(applicationGroupId);
		Mockito	.verify(this.mockApplicationGroupWindowView)
				.setEntity(applicationGroup);

		Mockito	.verify(this.mockApplicationGroupWindowView)
				.setConfigurableHeadline(Mode.EDIT.getI18nKey());
	}

	@Test
	public void updateEntityNotValidTest() {
		this.applicationGroupWindowPresenter.updateEntity(false, new ApplicationGroup());

		// should be called
		Mockito	.verify(this.mockNotificationProvider)
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());

		// should not be called
		Mockito	.verify(this.mockApplicationGroupService, Mockito.never())
				.update(Mockito.any());
		Mockito	.verify(this.mockLocalEventBus, Mockito.never())
				.fireEvent(Mockito.any());
	}

	@Test
	public void updateEntityModeAddTest() {
		ApplicationGroup applicationGroup = new ApplicationGroup();

		ApplicationGroup savedApplicationGroup = new ApplicationGroupBuilder()	.withId(1L)
																				.build();
		Mockito	.when(this.mockApplicationGroupService.create(applicationGroup))
				.thenReturn(savedApplicationGroup.getId());

		this.applicationGroupWindowPresenter.updateEntity(true, applicationGroup);

		// should not be called
		Mockito	.verify(this.mockNotificationProvider, Mockito.never())
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());
		Mockito	.verify(this.mockApplicationGroupService, Mockito.never())
				.update(Mockito.any());

		// should be called
		Mockito	.verify(this.mockApplicationGroupService)
				.create(applicationGroup);

		ArgumentCaptor<InsertedApplicationGroupEvent> insertedCountryEventCaptor = ArgumentCaptor.forClass(InsertedApplicationGroupEvent.class);
		Mockito	.verify(this.mockLocalEventBus)
				.fireEvent(insertedCountryEventCaptor.capture());
		InsertedApplicationGroupEvent capturedInsertedCountryEvent = insertedCountryEventCaptor.getValue();
		Assert.assertEquals(capturedInsertedCountryEvent.getApplicationGroup(), savedApplicationGroup);

		Mockito	.verify(this.mockDialogProvider)
				.closeCurrentDialog();
	}

	@Test
	public void updateEntityModeEditTest() {
		ApplicationGroup applicationGroup = new ApplicationGroupBuilder()	.withId(1L)
																			.build();

		ApplicationGroup updatedApplicationGroup = new ApplicationGroupBuilder().withId(1L)
																				.build();

		this.applicationGroupWindowPresenter.withApplicationGroupId(1L);
		this.applicationGroupWindowPresenter.updateEntity(true, applicationGroup);

		// should not be called
		Mockito	.verify(this.mockNotificationProvider, Mockito.never())
				.showErrorNotification(Mockito.anyString(), Mockito.anyString());
		Mockito	.verify(this.mockApplicationGroupService, Mockito.never())
				.create(Mockito.any());

		// should be called
		Mockito	.verify(this.mockApplicationGroupService)
				.update(applicationGroup);

		ArgumentCaptor<UpdatedApplicationGroupEvent> updatedCountryEventCaptor = ArgumentCaptor.forClass(UpdatedApplicationGroupEvent.class);
		Mockito	.verify(this.mockLocalEventBus)
				.fireEvent(updatedCountryEventCaptor.capture());
		UpdatedApplicationGroupEvent capturedUpdatedCountryEvent = updatedCountryEventCaptor.getValue();
		Assert.assertEquals(capturedUpdatedCountryEvent.getApplicationGroup(), updatedApplicationGroup);

		Mockito	.verify(this.mockDialogProvider)
				.closeCurrentDialog();
	}

}
