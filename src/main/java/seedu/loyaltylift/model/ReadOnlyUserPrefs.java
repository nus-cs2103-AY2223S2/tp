package seedu.loyaltylift.model;

import java.nio.file.Path;

import seedu.loyaltylift.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

}
