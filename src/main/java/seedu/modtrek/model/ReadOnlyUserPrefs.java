package seedu.modtrek.model;

import java.nio.file.Path;

import seedu.modtrek.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFilePath();

}
