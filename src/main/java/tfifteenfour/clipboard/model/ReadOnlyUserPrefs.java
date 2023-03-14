package tfifteenfour.clipboard.model;

import java.nio.file.Path;

import tfifteenfour.clipboard.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getRosterFilePath();

}
