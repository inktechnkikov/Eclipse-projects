package de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.ui;

import java.util.List;

import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.validator.LongRangeValidator;
import com.vaadin.ui.Component;

import de.bonprix.I18N;
import de.bonprix.global.masterdata.dto.ApplicationGroup;
import de.bonprix.academy.modules.applicationdata.dialog.applicationgroup.ApplicationGroupWindowView;
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
public class ApplicationGroupWindowViewImpl extends AbstractMvpDialogView<ApplicationGroupWindowView.Presenter>
		implements ApplicationGroupWindowView<ApplicationGroupWindowView.Presenter> {

	private BeanFieldGroup<ApplicationGroup> fieldGroupApplicationGroup;
	private List<ApplicationGroup> applicationGroups;

	protected ApplicationGroupWindowViewImpl() {
		super(new DialogConfigurationBuilder()	.withPrimaryButton(DialogButton.SAVE)
												.withButton(DialogButton.CANCEL)
												.withCloseOnButton(DialogButton.CANCEL)
												.build());

		this.fieldGroupApplicationGroup = new BeanFieldGroup<>(ApplicationGroup.class);
		this.fieldGroupApplicationGroup.setBuffered(false);

		addButtonListener(DialogButton.SAVE, event -> {
			getPresenter().updateEntity(this.fieldGroupApplicationGroup.isValid(),
										this.fieldGroupApplicationGroup	.getItemDataSource()
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
										.bind(this.fieldGroupApplicationGroup, "nameKey")
										.validator(value -> {
											if (!(((String) value).matches("^[A-Z][A-Z_]*$"))) {
												throw new InvalidValueException(I18N.get("VALIDATION_NAME_KEY"));
											}
										})
										.validator(value -> {
											for (ApplicationGroup applicationGroup : this.applicationGroups) {
												if (applicationGroup.getNameKey() != null
														&& applicationGroup	.getNameKey()
																			.equals(value)
														&& applicationGroup.getId() != null && !applicationGroup.getId()
																												.equals(this.fieldGroupApplicationGroup	.getItemDataSource()
																																						.getBean()
																																						.getId())) {
													throw new InvalidValueException(I18N.get("VALIDATION_NOT_UNIQUE"));
												}
											}
										})
										.get())
						.add(FluentUI	.textField()
										.captionKey("SORT_PRIO")
										.bind(this.fieldGroupApplicationGroup, "sortPrio")
										.validator(new LongRangeValidator(I18N.get("VALIDATION_NOT_A_NUMBER"), 0L,
												10000L))
										.validator(value -> {
											for (ApplicationGroup applicationGroup : this.applicationGroups) {
												if (applicationGroup.getSortPrio() != null
														&& applicationGroup	.getSortPrio()
																			.equals(value)
														&& applicationGroup.getId() != null && !applicationGroup.getId()
																												.equals(this.fieldGroupApplicationGroup	.getItemDataSource()
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
	public void setEntity(ApplicationGroup applicationGroup) {
		this.fieldGroupApplicationGroup.setItemDataSource(applicationGroup);
	}

	@Override
	public void setApplicationGroups(List<ApplicationGroup> applicationGroups) {
		this.applicationGroups = applicationGroups;
	}
}
