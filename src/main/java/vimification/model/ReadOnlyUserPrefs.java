package vimification.model;

import java.nio.file.Path;

import vimification.common.core.GuiSettings;

/**
 * Unmodifiable view of the user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskListFilePath();

    Path getMacroMapFilePath();

}
