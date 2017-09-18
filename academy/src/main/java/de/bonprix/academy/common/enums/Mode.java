package de.bonprix.academy.common.enums;

public enum Mode {
	ADD("ADD"), EDIT("EDIT"), DELETE("DELETE");

	private final String i18nKey;

	Mode(String i18nKey) {
		this.i18nKey = i18nKey;
	}

	public String getI18nKey() {
		return this.i18nKey;
	}

}
