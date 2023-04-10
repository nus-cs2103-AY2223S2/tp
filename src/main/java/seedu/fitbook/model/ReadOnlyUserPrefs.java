package seedu.fitbook.model;

import java.nio.file.Path;

import seedu.fitbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();
    //@@author
    Path getFitBookFilePath();

    Path getFitBookExerciseRoutineFilePath();
}
