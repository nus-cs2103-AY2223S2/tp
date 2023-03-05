package expresslibrary.model;

import java.nio.file.Path;

import expresslibrary.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getExpressLibraryFilePath();

}
