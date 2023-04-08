package fasttrack.model;

import java.nio.file.Path;

import fasttrack.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getExpenseTrackerFilePath();

}
