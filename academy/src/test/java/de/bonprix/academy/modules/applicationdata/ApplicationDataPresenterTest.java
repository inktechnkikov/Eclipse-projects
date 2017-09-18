package de.bonprix.academy.modules.applicationdata;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.context.ApplicationContext;
import org.testng.annotations.Test;

import de.bonprix.I18NAwareUnitTest;
import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.global.masterdata.dto.builder.ApplicationBuilder;
import de.bonprix.global.masterdata.dto.builder.ApplicationGroupBuilder;
import de.bonprix.global.masterdata.dto.builder.ApplicationTypeBuilder;
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
import de.bonprix.vaadin.provider.UiNotificationProvider;

public class ApplicationDataPresenterTest extends I18NAwareUnitTest {

	@InjectMocks
	private ApplicationDataPresenter applicationDataPresenter;

	@Mock
	private EventBus mockLocalEventBus;

	@Mock
	private ApplicationDataView mockApplicationDataView;

	@Mock
	private ApplicationService mockApplicationService;

	@Mock
	private ApplicationTypeService mockApplicationTypeService;

	@Mock
	private ApplicationGroupService mockApplicationGroupService;

	@Mock
	private UiNotificationProvider mockNotificationProvider;

	@Mock
	private ApplicationContext mockApplicationContext;

	@Spy
	private ForkJoinPool forkJoinPool;

	@Test
	public void initTest() {
		List<Application> countries = new ArrayList<>();
		Mockito	.when(this.mockApplicationService.findAll(	Mockito.any(ApplicationFilter.class),
															Mockito.any(ApplicationFetchOptions.class)))
				.thenReturn(countries);
		List<ApplicationType> applicationTypes = new ArrayList<>();
		Mockito	.when(this.mockApplicationTypeService.findAll(Mockito.any(Paged.class)))
				.thenReturn(applicationTypes);
		List<ApplicationGroup> applicationGroups = new ArrayList<>();
		Mockito	.when(this.mockApplicationGroupService.findAll(Mockito.any(Paged.class)))
				.thenReturn(applicationGroups);

		this.applicationDataPresenter.init();

		Mockito	.verify(this.mockLocalEventBus)
				.addHandler(this.applicationDataPresenter);

		Mockito	.verify(this.mockApplicationService, Mockito.timeout(100))
				.findAll(Mockito.any(ApplicationFilter.class), Mockito.any(ApplicationFetchOptions.class));
		Mockito	.verify(this.mockApplicationDataView, Mockito.timeout(100))
				.setApplications(countries);
		Mockito	.verify(this.mockApplicationTypeService, Mockito.timeout(100))
				.findAll(Mockito.any(Paged.class));
		Mockito	.verify(this.mockApplicationDataView, Mockito.timeout(100))
				.setApplicationTypes(applicationTypes);
		Mockito	.verify(this.mockApplicationGroupService, Mockito.timeout(100))
				.findAll(Mockito.any(Paged.class));
		Mockito	.verify(this.mockApplicationDataView, Mockito.timeout(100))
				.setApplicationGroups(applicationGroups);
	}

	@Test
	public void selectionChangedOnGridNoSelectionTest() {
		this.applicationDataPresenter.selectionChangedOnApplicationGrid(null);

		// should be called
		Mockito	.verify(this.mockApplicationDataView)
				.disableMenuEditAndMenuDelete();

		// should not be called
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.deselectApplicationGrid();
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.deselectApplicationTypeGrid();
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.deselectApplicationGroupGrid();
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.enableMenuEditAndMenuDelete();
	}

	@Test
	public void selectionChangedOnGridApplicationSelectionTest() {
		this.applicationDataPresenter.selectionChangedOnApplicationGrid(new Application());

		// should not be called
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.disableMenuEditAndMenuDelete();
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.deselectApplicationGrid();

		// should be called
		Mockito	.verify(this.mockApplicationDataView)
				.deselectApplicationTypeGrid();
		Mockito	.verify(this.mockApplicationDataView)
				.deselectApplicationGroupGrid();
		Mockito	.verify(this.mockApplicationDataView)
				.enableMenuEditAndMenuDelete();
	}

	@Test
	public void selectionChangedOnGridApplicationTypeSelectionTest() {
		this.applicationDataPresenter.selectionChangedOnApplicationTypeGrid(new ApplicationType());

		// should not be called
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.disableMenuEditAndMenuDelete();
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.deselectApplicationTypeGrid();

		// should be called
		Mockito	.verify(this.mockApplicationDataView)
				.deselectApplicationGrid();
		Mockito	.verify(this.mockApplicationDataView)
				.deselectApplicationGroupGrid();
		Mockito	.verify(this.mockApplicationDataView)
				.enableMenuEditAndMenuDelete();
	}

	@Test
	public void selectionChangedOnGridApplicationGroupSelectionTest() {
		this.applicationDataPresenter.selectionChangedOnApplicationGroupGrid(new ApplicationGroup());

		// should not be called
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.disableMenuEditAndMenuDelete();
		Mockito	.verify(this.mockApplicationDataView, Mockito.never())
				.deselectApplicationGroupGrid();

		// should be called
		Mockito	.verify(this.mockApplicationDataView)
				.deselectApplicationGrid();
		Mockito	.verify(this.mockApplicationDataView)
				.deselectApplicationTypeGrid();
		Mockito	.verify(this.mockApplicationDataView)
				.enableMenuEditAndMenuDelete();
	}

	@Test
	public void clickedMenuEditApplicationTest() {
		Application application = new ApplicationBuilder()	.withId(1L)
															.build();

		this.applicationDataPresenter.selectionChangedOnApplicationGrid(application);

		ApplicationWindowPresenter mockApplicationWindowPresenter = Mockito.mock(ApplicationWindowPresenter.class);
		Mockito	.when(mockApplicationWindowPresenter.withApplicationId(Mockito.anyLong()))
				.thenReturn(mockApplicationWindowPresenter);
		Mockito	.when(this.mockApplicationContext.getBean(ApplicationWindowPresenter.class))
				.thenReturn(mockApplicationWindowPresenter);

		this.applicationDataPresenter.clickedMenuEdit();

		// should be called
		Mockito	.verify(mockApplicationWindowPresenter)
				.withApplicationId(application.getId());
		Mockito	.verify(mockApplicationWindowPresenter)
				.open();
	}

	@Test
	public void clickedMenuEditApplicationTypeTest() {
		ApplicationType applicationType = new ApplicationTypeBuilder()	.withId(1L)
																		.build();

		this.applicationDataPresenter.selectionChangedOnApplicationTypeGrid(applicationType);

		ApplicationTypeWindowPresenter mockApplicationTypeWindowPresenter = Mockito.mock(ApplicationTypeWindowPresenter.class);
		Mockito	.when(mockApplicationTypeWindowPresenter.withApplicationTypeId(Mockito.anyLong()))
				.thenReturn(mockApplicationTypeWindowPresenter);
		Mockito	.when(this.mockApplicationContext.getBean(ApplicationTypeWindowPresenter.class))
				.thenReturn(mockApplicationTypeWindowPresenter);

		this.applicationDataPresenter.clickedMenuEdit();

		// should be called
		Mockito	.verify(mockApplicationTypeWindowPresenter)
				.withApplicationTypeId(applicationType.getId());
		Mockito	.verify(mockApplicationTypeWindowPresenter)
				.open();
	}

	@Test
	public void clickedMenuEditApplicationGroupTest() {
		ApplicationGroup applicationGroup = new ApplicationGroupBuilder()	.withId(1L)
																			.build();

		this.applicationDataPresenter.selectionChangedOnApplicationGroupGrid(applicationGroup);

		ApplicationGroupWindowPresenter mockApplicationGroupWindowPresenter = Mockito.mock(ApplicationGroupWindowPresenter.class);
		Mockito	.when(mockApplicationGroupWindowPresenter.withApplicationGroupId(Mockito.anyLong()))
				.thenReturn(mockApplicationGroupWindowPresenter);
		Mockito	.when(this.mockApplicationContext.getBean(ApplicationGroupWindowPresenter.class))
				.thenReturn(mockApplicationGroupWindowPresenter);

		this.applicationDataPresenter.clickedMenuEdit();

		// should be called
		Mockito	.verify(mockApplicationGroupWindowPresenter)
				.withApplicationGroupId(applicationGroup.getId());
		Mockito	.verify(mockApplicationGroupWindowPresenter)
				.open();
	}

	@Test
	public void updatedApplicationEventTest() {
		Application application = new Application();

		this.applicationDataPresenter.updatedApplicationEvent(new UpdatedApplicationEvent(application));

		Mockito	.verify(this.mockApplicationDataView)
				.updateApplicationFromGrid(application);
	}

	@Test
	public void updatedApplicationTypeEventTest() {
		ApplicationType applicationType = new ApplicationType();

		this.applicationDataPresenter.updatedApplicationTypeEvent(new UpdatedApplicationTypeEvent(applicationType));

		Mockito	.verify(this.mockApplicationDataView)
				.updateApplicationTypeFromGrid(applicationType);
	}

	@Test
	public void updatedApplicationGroupEventTest() {
		ApplicationGroup applicationGroup = new ApplicationGroup();

		this.applicationDataPresenter.updatedApplicationGroupEvent(new UpdatedApplicationGroupEvent(applicationGroup));

		Mockito	.verify(this.mockApplicationDataView)
				.updateApplicationGroupFromGrid(applicationGroup);
	}

	@Test
	public void clickedMenuAddApplicationTest() {
		ApplicationWindowPresenter mockApplicationWindowPresenter = Mockito.mock(ApplicationWindowPresenter.class);
		Mockito	.when(mockApplicationWindowPresenter.withApplicationId(Mockito.anyLong()))
				.thenReturn(mockApplicationWindowPresenter);
		Mockito	.when(this.mockApplicationContext.getBean(ApplicationWindowPresenter.class))
				.thenReturn(mockApplicationWindowPresenter);

		this.applicationDataPresenter.clickedMenuAddApplication();

		// should be called
		Mockito	.verify(mockApplicationWindowPresenter)
				.open();

		// should not be called
		Mockito	.verify(mockApplicationWindowPresenter, Mockito.never())
				.withApplicationId(Mockito.anyLong());
	}

	@Test
	public void clickedMenuAddApplicationTypeTest() {
		ApplicationTypeWindowPresenter mockApplicationTypeWindowPresenter = Mockito.mock(ApplicationTypeWindowPresenter.class);
		Mockito	.when(mockApplicationTypeWindowPresenter.withApplicationTypeId(Mockito.anyLong()))
				.thenReturn(mockApplicationTypeWindowPresenter);
		Mockito	.when(this.mockApplicationContext.getBean(ApplicationTypeWindowPresenter.class))
				.thenReturn(mockApplicationTypeWindowPresenter);

		this.applicationDataPresenter.clickedMenuAddApplicationType();

		// should be called
		Mockito	.verify(mockApplicationTypeWindowPresenter)
				.open();

		// should not be called
		Mockito	.verify(mockApplicationTypeWindowPresenter, Mockito.never())
				.withApplicationTypeId(Mockito.anyLong());
	}

	@Test
	public void clickedMenuAddApplicationGroupTest() {
		ApplicationGroupWindowPresenter mockApplicationGroupWindowPresenter = Mockito.mock(ApplicationGroupWindowPresenter.class);
		Mockito	.when(mockApplicationGroupWindowPresenter.withApplicationGroupId(Mockito.anyLong()))
				.thenReturn(mockApplicationGroupWindowPresenter);
		Mockito	.when(this.mockApplicationContext.getBean(ApplicationGroupWindowPresenter.class))
				.thenReturn(mockApplicationGroupWindowPresenter);

		this.applicationDataPresenter.clickedMenuAddApplicationGroup();

		// should be called
		Mockito	.verify(mockApplicationGroupWindowPresenter)
				.open();

		// should not be called
		Mockito	.verify(mockApplicationGroupWindowPresenter, Mockito.never())
				.withApplicationGroupId(Mockito.anyLong());
	}

	@Test
	public void insertedApplicationEventTest() {
		Application application = new Application();

		this.applicationDataPresenter.insertedApplicationEvent(new InsertedApplicationEvent(application));

		Mockito	.verify(this.mockApplicationDataView)
				.insertApplicationToGrid(application);
	}

	@Test
	public void insertedApplicationTypeEventTest() {
		ApplicationType applicationType = new ApplicationType();

		this.applicationDataPresenter.insertedApplicationTypeEvent(new InsertedApplicationTypeEvent(applicationType));

		Mockito	.verify(this.mockApplicationDataView)
				.insertApplicationTypeToGrid(applicationType);
	}

	@Test
	public void insertedApplicationGroupEventTest() {
		ApplicationGroup applicationGroup = new ApplicationGroup();

		this.applicationDataPresenter.insertedApplicationGroupEvent(new InsertedApplicationGroupEvent(
				applicationGroup));

		Mockito	.verify(this.mockApplicationDataView)
				.insertApplicationGroupToGrid(applicationGroup);
	}

	// @Test
	// public void clickedMenuDeleteApplicationTest() {
	// Application application = new ApplicationBuilder() .withId(1L)
	// .build();
	//
	// this.applicationDataPresenter.selectionChangedOnGrid(ApplicationDataGrids.APPLICATION,
	// application);
	//
	// this.applicationDataPresenter.clickedMenuDelete();
	//
	// ArgumentCaptor<DialogButtonAction> dialogButtonActionCaptor =
	// ArgumentCaptor.forClass(DialogButtonAction.class);
	// Mockito .verify(this.mockNotificationProvider)
	// .showQuestionMessageBox(Mockito.anyString(),
	// dialogButtonActionCaptor.capture());
	// DialogButtonAction capturedDialogButtonAction =
	// dialogButtonActionCaptor.getValue();
	//
	// capturedDialogButtonAction.onClick();
	//
	// // should be called
	// Mockito .verify(this.mockApplicationService)
	// .deleteById(application.getId());
	// Mockito .verify(this.mockApplicationDataView)
	// .removeApplicationFromGrid(application);
	// }

	// @Test
	// public void clickedMenuDeleteApplicationTypeTest() {
	// ApplicationType applicationType = new ApplicationTypeBuilder()
	// .withId(1L)
	// .build();
	//
	// this.applicationDataPresenter.selectionChangedOnGrid(ApplicationDataGrids.APPLICATION_TYPE,
	// applicationType);
	//
	// this.applicationDataPresenter.clickedMenuDelete();
	//
	// ArgumentCaptor<DialogButtonAction> dialogButtonActionCaptor =
	// ArgumentCaptor.forClass(DialogButtonAction.class);
	// Mockito .verify(this.mockNotificationProvider)
	// .showQuestionMessageBox(Mockito.anyString(),
	// dialogButtonActionCaptor.capture());
	// DialogButtonAction capturedDialogButtonAction =
	// dialogButtonActionCaptor.getValue();
	//
	// capturedDialogButtonAction.onClick();
	//
	// // should be called
	// Mockito .verify(this.mockApplicationTypeService)
	// .deleteById(applicationType.getId());
	// Mockito .verify(this.mockApplicationDataView)
	// .removeApplicationTypeFromGrid(applicationType);
	// }

	// @Test
	// public void clickedMenuDeleteApplicationGroupTest() {
	// ApplicationGroup applicationGroup = new ApplicationGroupBuilder()
	// .withId(1L)
	// .build();
	//
	// this.applicationDataPresenter.selectionChangedOnGrid(ApplicationDataGrids.APPLICATION_GROUP,
	// applicationGroup);
	//
	// this.applicationDataPresenter.clickedMenuDelete();
	//
	// ArgumentCaptor<DialogButtonAction> dialogButtonActionCaptor =
	// ArgumentCaptor.forClass(DialogButtonAction.class);
	// Mockito .verify(this.mockNotificationProvider)
	// .showQuestionMessageBox(Mockito.anyString(),
	// dialogButtonActionCaptor.capture());
	// DialogButtonAction capturedDialogButtonAction =
	// dialogButtonActionCaptor.getValue();
	//
	// capturedDialogButtonAction.onClick();
	//
	// // should be called
	// Mockito .verify(this.mockApplicationGroupService)
	// .deleteById(applicationGroup.getId());
	// Mockito .verify(this.mockApplicationDataView)
	// .removeApplicationGroupFromGrid(applicationGroup);
	// }

}
