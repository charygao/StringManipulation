package osmedile.intellij.stringmanip;

import osmedile.intellij.stringmanip.config.PluginPersistentStateComponent;

public class CaseSwitchingSettings {
	private boolean separatorBeforeDigit = true;
	private boolean separatorAfterDigit = true;
	private boolean putSeparatorBetweenUpperCases;

	public CaseSwitchingSettings() {
	}

	public static CaseSwitchingSettings getInstance() {
		return PluginPersistentStateComponent.getInstance().getCaseSwitchingSettings();
	}

	public boolean isSeparatorBeforeDigit() {
		return separatorBeforeDigit;
	}

	public void setSeparatorBeforeDigit(final boolean separatorBeforeDigit) {
		this.separatorBeforeDigit = separatorBeforeDigit;
	}

	public boolean isSeparatorAfterDigit() {
		return separatorAfterDigit;
	}

	public void setSeparatorAfterDigit(final boolean separatorAfterDigit) {
		this.separatorAfterDigit = separatorAfterDigit;
	}

	public void resetToDefault() {
		separatorBeforeDigit = true;
		separatorAfterDigit = true;
		putSeparatorBetweenUpperCases = false;
	}

	public boolean isPutSeparatorBetweenUpperCases() {
		return putSeparatorBetweenUpperCases;
	}

	public void setPutSeparatorBetweenUpperCases(boolean putSeparatorBetweenUpperCases) {
		this.putSeparatorBetweenUpperCases = putSeparatorBetweenUpperCases;
	}

}