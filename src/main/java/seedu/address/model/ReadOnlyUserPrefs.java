package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    /**
     * Retuns the GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns the file path of the file containing the Tracker data.
     */
    Path getTrackerFilePath();

}
