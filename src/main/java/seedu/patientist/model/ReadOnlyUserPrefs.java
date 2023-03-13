package seedu.patientist.model;

import java.nio.file.Path;

import seedu.patientist.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPatientistFilePath();

}
