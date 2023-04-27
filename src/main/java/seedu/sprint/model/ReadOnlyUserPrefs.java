package seedu.sprint.model;

import java.nio.file.Path;

import seedu.sprint.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getInternshipBookFilePath();

}
