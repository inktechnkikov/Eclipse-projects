package de.bonprix.academy.modules.applicationdata.dialog.application.ui;

import java.util.List;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.LongRangeValidator;
import com.vaadin.ui.Component;
import de.bonprix.I18N;
import de.bonprix.global.masterdata.dto.Application;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.academy.modules.applicationdata.dialog.application.ApplicationWindowView;
import de.bonprix.vaadin.bean.field.BeanItemComboBox;
import de.bonprix.vaadin.bean.field.BeanItemComboBoxMultiselect;
import de.bonprix.vaadin.data.converter.StringToLongConverter;
import de.bonprix.vaadin.dialog.DialogButton;
import de.bonprix.vaadin.dialog.DialogConfigurationBuilder;
import de.bonprix.vaadin.fluentui.FluentUI;
import de.bonprix.vaadin.mvp.SpringViewComponent;
import de.bonprix.vaadin.mvp.dialog.AbstractMvpDialogView;

/**
 * @author t.sologub
 */
@SuppressWarnings("serial")
@SpringViewComponent
public class ApplicationWindowViewImpl extends AbstractMvpDialogView<ApplicationWindowView.Presenter>
		implements ApplicationWindowView<ApplicationWindowView.Presenter> {

	private BeanFieldGroup<Application> fieldGroupApplication;

	private BeanItemComboBox<ApplicationType> comboBoxApplicationType;
	private BeanItemComboBox<ApplicationGroup> comboBoxApplicationGroup;

	private List<Application> applications;

	protected ApplicationWindowViewImpl() {
		super(new DialogConfigurationBuilder()	.withPrimaryButton(DialogButton.SAVE)
												.withButton(DialogButton.CANCEL)
												.withCloseOnButton(DialogButton.CANCEL)
												.build());

		this.fieldGroupApplication = new BeanFieldGroup<>(Application.class);
		this.fieldGroupApplication.setBuffered(false);

		addButtonListener(DialogButton.SAVE, event -> {
			getPresenter().updateEntity(this.fieldGroupApplication.isValid(),
										this.fieldGroupApplication	.getItemDataSource()
																	.getBean());
		});

		this.comboBoxApplicationType = FluentUI	.beanItemComboBox(ApplicationType.class)
												.captionKey("APPLICATIONTYPE")
												.required()
												.nullSelectionAllowed(false)
												.bind(this.fieldGroupApplication, "applicationType")
												.itemCaptionGenerator(item -> item.getNameKey())
												.get();

		this.comboBoxApplicationGroup = FluentUI.beanItemComboBox(ApplicationGroup.class)
												.captionKey("APPLICATIONGROUP")
												.bind(this.fieldGroupApplication, "applicationGroup")
												.itemCaptionGenerator(item -> item.getNameKey())
												.get();
	}

	@Override
	protected Component layout() {
		return FluentUI	.form()
						.sizeUndefined()
						.margin()
						.spacing()
						.add(FluentUI	.textField()
										.captionKey("NAME")
										.required()
										.bind(this.fieldGroupApplication, "name")
										.validator(value -> {
											if (((String) value).matches(".*[äöüß]+.*")) {
												throw new InvalidValueException(I18N.get("VALIDATION_NO_UMLAUTS"));
											}
										})
										.validator(value -> {
											if (!((String) value).matches("^[a-zA-Z].*")) {
												throw new InvalidValueException(
														I18N.get("VALIDATION_STARTS_WITH_A_LETTER"));
											}
										})
										.validator(value -> {
											if (!((String) value).matches("^[a-zA-Z][a-zA-Z0-9.:-]*$")) {
												throw new InvalidValueException(
														I18N.get("VALIDATION_NO_PUNCION_EXCEPT_COLON_DOT_DASH"));
											}
										})
										.validator(value -> {
											if (((String) value).matches(".*[ ]+.*")) {
												throw new InvalidValueException(I18N.get("VALIDATION_NO_SPACES"));
											}
										})
										.validator(value -> {
											for (Application application : this.applications) {
												if (application.getName() != null && application.getName()
																								.equals(value)
														&& application.getId() != null && !application	.getId()
																										.equals(this.fieldGroupApplication	.getItemDataSource()
																																			.getBean()
																																			.getId())) {
													throw new InvalidValueException(I18N.get("VALIDATION_NOT_UNIQUE"));
												}
											}
										})
										.get())
						.add(this.comboBoxApplicationType)
						.add(this.comboBoxApplicationGroup)
						.add(FluentUI	.textField()
										.captionKey("SCM_URL")
										.bind(this.fieldGroupApplication, "scmUrl")
										.validator(value -> {
											if (value == null) {
												return;

											}
											if (!((String) value).matches("(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?")) {
												throw new InvalidValueException(I18N.get("VALIDATION_URL_NOT_VALID"));
											}
										})
										.validator(value -> {
											for (Application application : this.applications) {
												if (application.getScmUrl() != null && application	.getScmUrl()
																									.equals(value)
														&& application.getId() != null && !application	.getId()
																										.equals(this.fieldGroupApplication	.getItemDataSource()
																																			.getBean()
																																			.getId())) {
													throw new InvalidValueException(I18N.get("VALIDATION_NOT_UNIQUE"));
												}
											}
										})
										.get())
						.add(FluentUI	.textField()
										.captionKey("OLD_APPLICATION_ID")
										.bind(this.fieldGroupApplication, "oldId")
										.validator(new LongRangeValidator(I18N.get("VALIDATION_NOT_A_NUMBER"), 0L,
												100000000L))
										.validator(value -> {
											for (Application application : this.applications) {
												if (application.getOldId() != null && application	.getOldId()
																									.equals(value)
														&& application.getId() != null && !application	.getId()
																										.equals(this.fieldGroupApplication	.getItemDataSource()
																																			.getBean()
																																			.getId())) {
													throw new InvalidValueException(I18N.get("VALIDATION_NOT_UNIQUE"));
												}
											}
										})
										.converter(new StringToLongConverter())
										.get())
						.get();

	}

	@Override
	public void setEntity(Application application) {
		this.fieldGroupApplication.setItemDataSource(application);
	}

	@Override
	public void setConfigurableHeadline(String captionKey) {
		getConfiguration().setHeadline(I18N.get(captionKey) + " " + I18N.get("APPLICATION"));
	}

	@Override
	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	@Override
	public void setApplicationTypes(List<ApplicationType> applicationTypes) {
		this.comboBoxApplicationType.addAllBeans(applicationTypes);
	}

	@Override
	public void setApplicationGroups(List<ApplicationGroup> applicationGroups) {
		this.comboBoxApplicationGroup.addAllBeans(applicationGroups);
	}

}
