package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyIdentifiableManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.location.Location;
import seedu.address.model.pilot.Pilot;

/**
 * API of the Storage component
 */
public interface Storage extends AddressBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getAddressBookFilePath();

    @Override
    Optional<ReadOnlyAddressBook> readAddressBook() throws DataConversionException, IOException;

    @Override
    void saveAddressBook(ReadOnlyAddressBook addressBook) throws IOException;

    /**
     * Returns the path to the pilot manager file.
     *
     * @return the path to the pilot manager file.
     */
    Path getPilotManagerFilePath();

    /**
     * Reads the pilot manager from the {@code Storage::getPilotManagerFilePath}
     *
     * @return the pilot manager.
     */
    Optional<? extends ReadOnlyIdentifiableManager<Pilot>> readPilotManager() throws DataConversionException,
                                                         IOException;

    /* Location related methods */

    /**
     * Saves the pilot manager to the {@code Storage::getLocationManagerFilePath}
     */
    void savePilotManager(ReadOnlyIdentifiableManager<Pilot> pilotManager) throws IOException;

    /**
     * Retrieves the file path to location manager.
     * @return the location manager file path
     */
    Path getLocationManagerFilePath();

    /**
     * Reads the location manager from the {@code Storage::getLocationManagerFilePath}
     *
     * @return the location manager.
     */
    Optional<? extends ReadOnlyIdentifiableManager<Location>> readLocationManager() throws DataConversionException,
            IOException;

    /**
     * Saves the location manager to {@code Storage::getLocationManagerFilePath}
     * @param locationManager the location manager
     * @throws IOException when the file cannot be saved
     */
    void saveLocationManager(ReadOnlyIdentifiableManager<Location> locationManager) throws IOException;
}
