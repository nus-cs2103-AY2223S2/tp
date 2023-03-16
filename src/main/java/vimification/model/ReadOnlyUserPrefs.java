package vimification.model;

import java.nio.file.Path;

import vimification.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskListFilePath();

}
