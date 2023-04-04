package vimification.model;

import java.nio.file.Path;

import vimification.common.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getLogicTaskListFilePath();

}
