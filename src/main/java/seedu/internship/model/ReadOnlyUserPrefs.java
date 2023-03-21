package seedu.internship.model;

import java.nio.file.Path;

import seedu.internship.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {
    GuiSettings getGuiSettings();

    Path getInternshipCatalogueFilePath();
}

