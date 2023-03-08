package seedu.address.model;

import seedu.address.commons.core.GuiSettings;

import java.nio.file.Path;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs2 {

    GuiSettings getGuiSettings();

    Path getListingBookFilePath();

}
