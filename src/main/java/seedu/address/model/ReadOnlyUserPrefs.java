package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {
    /**
     * Returns GuiSettings.
     */
    GuiSettings getGuiSettings();

    /**
     * Returns file path of Reroll.
     */
    Path getRerollFilePath();

}
