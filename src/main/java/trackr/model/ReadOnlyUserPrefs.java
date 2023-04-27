package trackr.model;

import java.nio.file.Path;

import trackr.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    //@@author
    Path getTrackrFilePath();

}
