package seedu.event.model;

import java.nio.file.Path;

import seedu.event.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getEventBookFilePath();

    Path getContactListFilePath();

}
