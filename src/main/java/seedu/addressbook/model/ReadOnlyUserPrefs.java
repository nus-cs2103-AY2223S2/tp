package seedu.addressbook.model;

import java.nio.file.Path;

import seedu.addressbook.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFitBookFilePath();

}
