package seedu.task.model;

import java.nio.file.Path;

import seedu.task.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskBookFilePath();

    Path getPlannerFilePath();

}
