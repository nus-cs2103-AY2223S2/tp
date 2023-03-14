package seedu.careflow.model;

import java.nio.file.Path;

import seedu.careflow.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getPatientRecordFilePath();

    Path getDrugInventoryFilePath();

}
