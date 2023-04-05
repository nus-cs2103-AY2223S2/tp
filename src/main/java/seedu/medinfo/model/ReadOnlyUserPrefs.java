package seedu.medinfo.model;

import java.nio.file.Path;

import seedu.medinfo.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getMedInfoFilePath();

}
