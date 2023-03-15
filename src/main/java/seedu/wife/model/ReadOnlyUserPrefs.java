package seedu.wife.model;

import java.nio.file.Path;

import seedu.wife.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getWifeFilePath();

}
