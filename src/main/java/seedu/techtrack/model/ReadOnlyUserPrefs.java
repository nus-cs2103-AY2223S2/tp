package seedu.techtrack.model;

import java.nio.file.Path;

import seedu.techtrack.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getRoleBookFilePath();

}
