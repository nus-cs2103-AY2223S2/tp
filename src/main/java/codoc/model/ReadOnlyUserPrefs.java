package codoc.model;

import java.nio.file.Path;

import codoc.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getCodocFilePath();

}
