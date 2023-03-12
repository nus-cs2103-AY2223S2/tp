package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;
import seedu.address.storage.ReadOnlyData;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs extends ReadOnlyData {

    GuiSettings getGuiSettings();

    Path getAddressBookFilePath();

}
