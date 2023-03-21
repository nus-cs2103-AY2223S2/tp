package seedu.connectus.model;

import java.nio.file.Path;

import seedu.connectus.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getConnectUsFilePath();

}
