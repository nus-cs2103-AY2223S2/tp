package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    OperationMode getOperationMode();

    Path getAddressBookFilePath();

    Path getPilotManagerFilePath();
    void setPilotManagerFilePath(Path pilotManagerFilePath);

    Path getLocationManagerFilePath();
    void setLocationManagerFilePath(Path locationManagerFilePath);

}
