package seedu.sudohr.model;

import java.nio.file.Path;

import seedu.sudohr.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getSudoHrFilePath();

}
