package wingman.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import wingman.commons.exceptions.DataConversionException;
import wingman.model.ReadOnlyItemManager;
import wingman.model.ReadOnlyUserPrefs;
import wingman.model.UserPrefs;
import wingman.model.crew.Crew;
import wingman.model.flight.Flight;
import wingman.model.location.Location;
import wingman.model.pilot.Pilot;
import wingman.model.plane.Plane;

/**
 * API of the Storage component
 */
public interface Storage extends UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;


    // ================ Pilot methods ==============================

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
    Optional<? extends ReadOnlyItemManager<Pilot>> readPilotManager() throws DataConversionException,
                                                                             IOException;

    /**
     * Saves the pilot manager to the {@code Storage::getLocationManagerFilePath}
     */
    void savePilotManager(ReadOnlyItemManager<Pilot> pilotManager) throws IOException;


    // ================ Location methods ==============================

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
    Optional<? extends ReadOnlyItemManager<Location>> readLocationManager() throws DataConversionException,
            IOException;

    /**
     * Saves the location manager to {@code Storage::getLocationManagerFilePath}
     * @param locationManager the location manager
     * @throws IOException when the file cannot be saved
     */
    void saveLocationManager(ReadOnlyItemManager<Location> locationManager) throws IOException;


    // ================ Crew methods ==============================

    /**
     * Returns the path to the crew manager file.
     *
     * @return the path to the crew manager file.
     */
    Path getCrewManagerFilePath();

    /**
     * Reads the crew manager from the {@code Storage::getCrewManagerFilePath}
     *
     * @return the crew manager.
     */
    Optional<? extends ReadOnlyItemManager<Crew>> readCrewManager() throws DataConversionException,
            IOException;


    /**
     * Saves the crew manager to the {@code Storage::getCrewManagerFilePath}
     */
    void saveCrewManager(ReadOnlyItemManager<Crew> crewManager) throws IOException;


    // ================ Plane methods ==============================

    Path getPlaneManagerFilePath();

    Optional<? extends ReadOnlyItemManager<Plane>> readPlaneManager() throws DataConversionException,
                                                                             IOException;

    void savePlaneManager(ReadOnlyItemManager<Plane> planeManager) throws IOException;


    // ================ Flight methods ==============================

    /**
     * Returns the path to the flight manager file.
     *
     * @return the path to the flight manager file.
     */
    Path getFlightManagerFilePath();

    /**
     * Reads the flight manager from the {@code Storage::getFlightManagerFilePath}
     *
     * @return the flight manager
     * @throws DataConversionException when the file cannot be converted
     * @throws IOException when the file cannot be read
     */
    Optional<? extends ReadOnlyItemManager<Flight>> readFlightManager() throws DataConversionException,
                                                                               IOException;

    /**
     * Saves the flight manager to the {@code Storage::getFlightManagerFilePath}
     *
     * @param flightManager the flight manager
     * @throws IOException when the file cannot be saved
     */
    void saveFlightManager(ReadOnlyItemManager<Flight> flightManager) throws IOException;
}
