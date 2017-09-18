package de.bonprix.academy.modules.applicationdata.dialog.applicationtype.ui;

import java.util.List;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Component;
import de.bonprix.I18N;
import de.bonprix.global.masterdata.dto.ApplicationType;
import de.bonprix.academy.modules.applicationdata.dialog.applicationtype.ApplicationTypeWindowView;
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
public class ApplicationTypeWindowViewImpl extends AbstractMvpDialogView<ApplicationTypeWindowView.Presenter>
		implements ApplicationTypeWindowView<ApplicationTypeWindowView.Presenter> {

	private BeanFieldGroup<ApplicationType> fieldGroupApplicationType;
	private List<ApplicationType> applicationTypes;

	protected ApplicationTypeWindowViewImpl() {
		super(new DialogConfigurationBuilder()	.withPrimaryButton(DialogButton.SAVE)
												.withButton(DialogButton.CANCEL)
												.withCloseOnButton(DialogButton.CANCEL)
												.build());

		this.fieldGroupApplicationType = new BeanFieldGroup<>(ApplicationType.class);
		this.fieldGroupApplicationType.setBuffered(false);

		addButtonListener(DialogButton.SAVE, event -> {
			getPresenter().updateEntity(this.fieldGroupApplicationType.isValid(),
										this.fieldGroupApplicationType	.getItemDataSource()
																		.getBean());
		});
	}

	@Override
	protected Component layout() {
		return FluentUI	.form()
						.margin()
						.sizeUndefined()
						.spacing()
						.add(FluentUI	.textField()
										.captionKey("NAME_KEY")
										.required()
										.bind(this.fieldGroupApplicationType, "nameKey")
										.validator(value -> {
											if (!(((String) value).matches("^[A-Z][A-Z_]*$"))) {
												throw new InvalidValueException(I18N.get("VALIDATION_NAME_KEY"));
											}
										})
										.validator(value -> {
											for (ApplicationType applicationType : this.applicationTypes) {
												if (applicationType.getNameKey() != null
														&& applicationType	.getNameKey()
																			.equals(value)
														&& applicationType.getId() != null && !applicationType	.getId()
																												.equals(this.fieldGroupApplicationType	.getItemDataSource()
																																						.getBean()
																																						.getId())) {
													throw new InvalidValueException(I18N.get("VALIDATION_NOT_UNIQUE"));
												}
											}
										})
										.get())
						.get();

	}

	@Override
	public void setConfigurableHeadline(String captionKey) {
		getConfiguration().setHeadline(I18N.get(captionKey) + " " + I18N.get("APPLICATIONTYPE"));
	}

	@Override
	public void setEntity(ApplicationType applicationType) {
		this.fieldGroupApplicationType.setItemDataSource(applicationType);
	}

	@Override
	public void setApplicationTypes(List<ApplicationType> applicationTypes) {
		this.applicationTypes = applicationTypes;
	}
}
