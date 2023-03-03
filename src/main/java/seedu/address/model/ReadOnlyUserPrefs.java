package seedu.address.model;

import java.nio.file.Path;

import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.OperationMode;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    OperationMode getOperationMode();

    Path getAddressBookFilePath();

    Path getPilotManagerFilePath();

}
