package taa.model;

import java.nio.file.Path;

import taa.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaaDataFilePath();

}
