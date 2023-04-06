package seedu.socket.model;

import java.nio.file.Path;

import seedu.socket.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getSocketFilePath();

}
