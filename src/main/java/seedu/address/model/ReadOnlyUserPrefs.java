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

    /**
     * Returns the file path of the pilotManager
     *
     * @return file path of the pilotManager
     */
    Path getPilotManagerFilePath();

    /**
     * Sets the user prefs' pilot manager file path.
     *
     * @param pilotManagerFilePath the new pilot manager file path
     */
    void setPilotManagerFilePath(Path pilotManagerFilePath);

    /**
     * Returns the file path of the locationManager
     *
     * @return file path of the locationManager
     */
    Path getLocationManagerFilePath();

    /**
     * Sets the user prefs' location manager file path.
     *
     * @param locationManagerFilePath the new location manager file path
     */
    void setLocationManagerFilePath(Path locationManagerFilePath);

    /**
     * Returns the file path of the flightManager
     *
     * @return file path of the flightManager
     */
    Path getFlightManagerFilePath();

    /**
     * Sets the user prefs' flight manager file path.
     *
     * @param flightManagerFilePath the new flight manager file path
     */
    void setFlightManagerFilePath(Path flightManagerFilePath);

}
