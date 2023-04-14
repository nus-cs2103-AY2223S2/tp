package seedu.ultron.model;

import java.nio.file.Path;

import seedu.ultron.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getUltronFilePath();

}
