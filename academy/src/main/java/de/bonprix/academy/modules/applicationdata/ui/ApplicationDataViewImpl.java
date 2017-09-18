package de.bonprix.academy.modules.applicationdata.ui;

import java.util.List;
import java.util.Locale;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid.SelectionMode;

import de.bonprix.academy.common.enums.Mode;
import de.bonprix.academy.modules.applicationdata.ApplicationDataView;
import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.vaadin.bean.grid.BeanItemGrid;
import de.bonprix.vaadin.bean.grid.FilterHeader;
import de.bonprix.vaadin.data.converter.StringToLongConverter;
import de.bonprix.vaadin.fluentui.FluentUI;
import de.bonprix.vaadin.mvp.view.regular.AbstractMvpView;
import de.bonprix.vaadin.ui.ComponentBar.ComponentBarItem;

/**
 * @author t.sologub
 */
@SuppressWarnings("serial")
@SpringView(name = ApplicationDataViewImpl.VIEW_NAME,
		order = 10)
public class ApplicationDataViewImpl extends
		AbstractMvpView<ApplicationDataView.Presenter> implements
		ApplicationDataView {

	public static final String VIEW_NAME = "APPLICATIONDATA";

	private BeanItemGrid<Application> applicationGrid;
	private BeanItemGrid<ApplicationType> applicationTypeGrid;
	private BeanItemGrid<ApplicationGroup> applicationGroupGrid;

	@Override
	protected void initializeUI() {
		addMenuElement(new ComponentBarItem(Mode.ADD, Mode.ADD.getI18nKey()));
		addMenuElement(new ComponentBarItem("application", "APPLICATION")
				.withParentId(Mode.ADD)
				.withClickAction(() -> getPresenter()
						.clickedMenuAddApplication()));
		addMenuElement(new ComponentBarItem("applicationtype",
				"APPLICATIONTYPE").withParentId(Mode.ADD)
						.withClickAction(() -> getPresenter()
								.clickedMenuAddApplicationType()));
		addMenuElement(new ComponentBarItem("applicationgroup",
				"APPLICATIONGROUP").withParentId(Mode.ADD)
						.withClickAction(() -> getPresenter()
								.clickedMenuAddApplicationGroup()));

		addMenuElement(new ComponentBarItem(Mode.EDIT, Mode.EDIT.getI18nKey())
				.withEnabled(false)
				.withClickAction(() -> getPresenter().clickedMenuEdit()));
		addMenuElement(new ComponentBarItem(Mode.DELETE, Mode.DELETE
				.getI18nKey()).withEnabled(false)
						.withClickAction(() -> getPresenter()
								.clickedMenuDelete()));

		this.applicationGrid = createApplicationGrid();
		this.applicationTypeGrid = createApplicationTypeGrid();
		this.applicationGroupGrid = createApplicationGroupGrid();

		setCompositionRoot(FluentUI.horizontal()
				.spacing()
				.sizeFull()
				.add(FluentUI.tabSheet()
						.sizeFull()
						.add(FluentUI.tab(this.applicationGrid)
								.captionKey("APPLICATION")
								.get())
						.get())
				.add(FluentUI.tabSheet()
						.sizeFull()
						.add(FluentUI.tab(this.applicationTypeGrid)
								.captionKey("APPLICATIONTYPE")
								.get())
						.add(FluentUI.tab(this.applicationGroupGrid)
								.captionKey("APPLICATIONGROUP")
								.get())
						.get())
				.get());
	}

	private BeanItemGrid<Application> createApplicationGrid() {
		BeanItemGrid<Application> applicationGrid = new BeanItemGrid<>(
				Application.class);

		applicationGrid.setSizeFull();
		applicationGrid.setColumns("id",
				"name",
				"applicationType",
				"applicationGroup",
				"scmUrl",
				"oldId");
		applicationGrid.setSelectionMode(SelectionMode.SINGLE);
		applicationGrid.addSelectionChangeListener(event -> {
			getPresenter().selectionChangedOnApplicationGrid(event
					.getSelectedItem());
		});
		applicationGrid.getColumn("id")
				.setConverter(new StringToLongConverter());
		applicationGrid.getColumn("applicationType")
				.setConverter(new Converter<String, ApplicationType>() {

					private static final long serialVersionUID = -8192890496811467014L;

					@Override
					public ApplicationType convertToModel(String value,
							Class<? extends ApplicationType> targetType,
							Locale locale)
							throws com.vaadin.data.util.converter.Converter.ConversionException {
						return null;
					}

					@Override
					public String convertToPresentation(ApplicationType value,
							Class<? extends String> targetType, Locale locale)
							throws com.vaadin.data.util.converter.Converter.ConversionException {
						if (value != null) {
							return value.getNameKey();
						}
						return "";
					}

					@Override
					public Class<ApplicationType> getModelType() {
						return ApplicationType.class;
					}

					@Override
					public Class<String> getPresentationType() {
						return String.class;
					}

				});
		applicationGrid.getColumn("applicationGroup")
				.setConverter(new Converter<String, ApplicationGroup>() {

					private static final long serialVersionUID = -8192890496811467014L;

					@Override
					public ApplicationGroup convertToModel(String value,
							Class<? extends ApplicationGroup> targetGroup,
							Locale locale)
							throws com.vaadin.data.util.converter.Converter.ConversionException {
						return null;
					}

					@Override
					public String convertToPresentation(ApplicationGroup value,
							Class<? extends String> targetGroup, Locale locale)
							throws com.vaadin.data.util.converter.Converter.ConversionException {
						return value != null ? value.getNameKey() : "";
					}

					@Override
					public Class<ApplicationGroup> getModelType() {
						return ApplicationGroup.class;
					}

					@Override
					public Class<String> getPresentationType() {
						return String.class;
					}

				});

		applicationGrid.getColumn("oldId")
				.setConverter(new StringToLongConverter());

		FilterHeader applicationFilterHeader = applicationGrid
				.addFilterHeader();
		applicationFilterHeader.addStringFilter("id",
				"id");
		applicationFilterHeader.addStringFilter("name",
				"name");
		applicationFilterHeader.addStringFilter("applicationType",
				"applicationType");
		applicationFilterHeader.addStringFilter("applicationGroup",
				"applicationGroup");
		applicationFilterHeader.addStringFilter("scmUrl",
				"scmUrl");
		applicationFilterHeader.addStringFilter("oldId",
				"oldId");

		return applicationGrid;
	}

	private BeanItemGrid<ApplicationType> createApplicationTypeGrid() {
		BeanItemGrid<ApplicationType> applicationTypeGrid = new BeanItemGrid<>(
				ApplicationType.class);

		applicationTypeGrid.setSizeFull();
		applicationTypeGrid.setColumns("id",
				"nameKey");
		applicationTypeGrid.setSelectionMode(SelectionMode.SINGLE);
		applicationTypeGrid.addSelectionChangeListener(event -> {
			getPresenter().selectionChangedOnApplicationTypeGrid(event
					.getSelectedItem());
		});
		applicationTypeGrid.getColumn("id")
				.setConverter(new StringToLongConverter());

		FilterHeader applicationTypeFilter = applicationTypeGrid
				.addFilterHeader();
		applicationTypeFilter.addStringFilter("id",
				"id");
		applicationTypeFilter.addStringFilter("nameKey",
				"name");

		return applicationTypeGrid;
	}

	private BeanItemGrid<ApplicationGroup> createApplicationGroupGrid() {
		BeanItemGrid<ApplicationGroup> applicationGroupGrid = new BeanItemGrid<>(
				ApplicationGroup.class);

		applicationGroupGrid.setSizeFull();
		applicationGroupGrid.setColumns("id",
				"nameKey",
				"sortPrio");
		applicationGroupGrid.setSelectionMode(SelectionMode.SINGLE);
		applicationGroupGrid.addSelectionChangeListener(event -> {
			getPresenter().selectionChangedOnApplicationGroupGrid(event
					.getSelectedItem());
		});
		applicationGroupGrid.getColumn("id")
				.setConverter(new StringToLongConverter());

		FilterHeader applicationTypeFilter = applicationGroupGrid
				.addFilterHeader();
		applicationTypeFilter.addStringFilter("id",
				"id");
		applicationTypeFilter.addStringFilter("nameKey",
				"name");
		applicationTypeFilter.addStringFilter("sortPrio",
				"sortPrio");

		return applicationGroupGrid;
	}

	@Override
	public void setApplications(List<Application> applications) {
		this.applicationGrid.addAllBeans(applications);
	}

	@Override
	public void setApplicationTypes(List<ApplicationType> applicationTypes) {
		this.applicationTypeGrid.addAllBeans(applicationTypes);
	}

	@Override
	public void setApplicationGroups(List<ApplicationGroup> applicationGroups) {
		this.applicationGroupGrid.addAllBeans(applicationGroups);
	}

	@Override
	public void disableMenuEditAndMenuDelete() {
		enableMenuElement(Mode.EDIT,
				false);
		enableMenuElement(Mode.DELETE,
				false);
	}

	@Override
	public void enableMenuEditAndMenuDelete() {
		enableMenuElement(Mode.EDIT,
				true);
		enableMenuElement(Mode.DELETE,
				true);

	}

	@Override
	public void deselectApplicationGrid() {
		this.applicationGrid.deselectAll();
	}

	@Override
	public void deselectApplicationTypeGrid() {
		this.applicationTypeGrid.deselectAll();
	}

	@Override
	public void deselectApplicationGroupGrid() {
		this.applicationGroupGrid.deselectAll();
	}

	@Override
	public void updateApplicationFromGrid(Application application) {
		this.applicationGrid.replaceBean(application);
		this.applicationGrid.select(application);
		this.applicationGrid.scrollTo(application);
	}

	@Override
	public void updateApplicationTypeFromGrid(ApplicationType applicationType) {
		this.applicationTypeGrid.replaceBean(applicationType);
		this.applicationTypeGrid.select(applicationType);
		this.applicationTypeGrid.scrollTo(applicationType);
	}

	@Override
	public void updateApplicationGroupFromGrid(
			ApplicationGroup applicationGroup) {
		this.applicationGroupGrid.replaceBean(applicationGroup);
		this.applicationGroupGrid.select(applicationGroup);
		this.applicationGroupGrid.scrollTo(applicationGroup);
	}

	@Override
	public void removeApplicationFromGrid(Application application) {
		this.applicationGrid.removeBean(application);
	}

	@Override
	public void removeApplicationTypeFromGrid(ApplicationType applicationType) {
		this.applicationTypeGrid.removeBean(applicationType);
	}

	@Override
	public void removeApplicationGroupFromGrid(
			ApplicationGroup applicationGroup) {
		this.applicationGroupGrid.removeBean(applicationGroup);
	}

	@Override
	public void insertApplicationToGrid(Application application) {
		this.applicationGrid.addBean(application);
		this.applicationGrid.select(application);
		this.applicationGrid.scrollTo(application);
	}

	@Override
	public void insertApplicationTypeToGrid(ApplicationType applicationType) {
		this.applicationTypeGrid.addBean(applicationType);
		this.applicationTypeGrid.select(applicationType);
		this.applicationTypeGrid.scrollTo(applicationType);
	}

	@Override
	public void insertApplicationGroupToGrid(
			ApplicationGroup applicationGroup) {
		this.applicationGroupGrid.addBean(applicationGroup);
		this.applicationGroupGrid.select(applicationGroup);
		this.applicationGroupGrid.scrollTo(applicationGroup);
	}

}
