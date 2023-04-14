package seedu.powercards.model;

import java.nio.file.Path;

import seedu.powercards.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMasterDeckFilePath();

}
