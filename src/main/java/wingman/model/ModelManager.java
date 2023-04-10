package wingman.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import wingman.commons.core.GuiSettings;
import wingman.commons.core.LogsCenter;
import wingman.commons.util.CollectionUtil;
import wingman.model.crew.Crew;
import wingman.model.crew.exceptions.CrewNotFoundException;
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.flight.Flight;
import wingman.model.flight.exceptions.FlightNotFoundException;
import wingman.model.item.Item;
import wingman.model.item.exceptions.DuplicateItemException;
import wingman.model.location.Location;
import wingman.model.location.exceptions.LocationNotFoundException;
import wingman.model.pilot.Pilot;
import wingman.model.pilot.exceptions.PilotNotFoundException;
import wingman.model.plane.Plane;
import wingman.model.plane.exceptions.PlaneNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final UserPrefs userPrefs;

    // pilot manager
    private final ItemManager<Pilot> pilotManager;
    private final FilteredList<Pilot> filteredPilots;
    private final ObservableList<Pilot> pilotList;

    // location manager
    private final ItemManager<Location> locationManager;
    private final FilteredList<Location> filteredLocations;
    private final ObservableList<Location> locationList;

    // crew manager
    private final ItemManager<Crew> crewManager;
    private final FilteredList<Crew> filteredCrew;
    private final ObservableList<Crew> crewList;

    // plane manager
    private final ItemManager<Plane> planeManager;
    private final FilteredList<Plane> filteredPlanes;
    private final ObservableList<Plane> planeList;

    // flight manager
    private final ItemManager<Flight> flightManager;
    private final FilteredList<Flight> filteredFlights;
    private final ObservableList<Flight> flightList;

    // general utilities
    private final ObservableList<Item> itemsList;
    private Optional<ObservableList<? extends Item>> lastBoundList = Optional.empty();

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(
            ReadOnlyUserPrefs userPrefs,
            ReadOnlyItemManager<Pilot> pilotManager,
            ReadOnlyItemManager<Location> locationManager,
            ReadOnlyItemManager<Crew> crewManager,
            ReadOnlyItemManager<Plane> planeManager,
            ReadOnlyItemManager<Flight> flightManager
    ) {
        CollectionUtil.requireAllNonNull(userPrefs);

        logger.fine(
                "Initializing with Wingman storage(pilot, location, crew, plane, flight)"
                        + " and user prefs " + userPrefs);

        this.userPrefs = new UserPrefs(userPrefs);

        this.pilotManager = new ItemManager<>(pilotManager);
        filteredPilots = new FilteredList<>(this.pilotManager.getItemList());

        this.locationManager = new ItemManager<>(locationManager);
        filteredLocations = new FilteredList<>(this.locationManager.getItemList());

        this.crewManager = new ItemManager<>(crewManager);
        filteredCrew = new FilteredList<>(this.crewManager.getItemList());

        this.planeManager = new ItemManager<>(planeManager);
        filteredPlanes = new FilteredList<>(this.planeManager.getItemList());

        this.flightManager = new ItemManager<>(flightManager);
        filteredFlights = new FilteredList<>(this.flightManager.getItemList());

        itemsList = FXCollections.observableArrayList();
        flightList = new FilteredList<>(filteredFlights);
        crewList = new FilteredList<>(filteredCrew);
        planeList = new FilteredList<>(filteredPlanes);
        pilotList = new FilteredList<>(filteredPilots);
        locationList = new FilteredList<>(filteredLocations);

        setOperationMode(userPrefs.getOperationMode());
    }

    /**
     * Initializes a ModelManager
     */
    public ModelManager() {
        this(new UserPrefs(), new ItemManager<>(),
                new ItemManager<>(), new ItemManager<>(),
                new ItemManager<>(), new ItemManager<>()
        );
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public OperationMode getOperationMode() {
        return userPrefs.getOperationMode();
    }

    /**
     * Sets the operation mode of the app.
     *
     * @param mode the new operation mode
     */
    @Override
    public void setOperationMode(OperationMode mode) {
        this.userPrefs.setOperationMode(mode);
        switch (mode) {
        case PILOT:
            rebind(filteredPilots);
            break;
        case PLANE:
            rebind(filteredPlanes);
            break;
        case FLIGHT:
            rebind(filteredFlights);
            break;
        case CREW:
            rebind(filteredCrew);
            break;
        case LOCATION:
            rebind(filteredLocations);
            break;
        default:
            logger.warning("Unknown operation mode: " + mode);
            break;
        }
    }

    private void rebind(ObservableList<? extends Item> list) {
        if (lastBoundList.isPresent()) {
            final ObservableList<? extends Item> lastBound =
                    lastBoundList.get();
            Bindings.unbindContent(itemsList, lastBound);
        }
        Bindings.bindContent(itemsList, list);
        lastBoundList = Optional.of(list);
    }

    @Override
    public ObservableList<Item> getItemsList() {
        return itemsList;
    }

    @Override
    public ObservableList<Flight> getFlightList() {
        return flightList;
    }

    @Override
    public ObservableList<Crew> getCrewList() {
        return crewList;
    }

    @Override
    public ObservableList<Plane> getPlaneList() {
        return planeList;
    }

    @Override
    public ObservableList<Pilot> getPilotList() {
        return pilotList;
    }

    @Override
    public ObservableList<Location> getLocationList() {
        return locationList;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }


    //=========== Pilot ========================================================

    @Override
    public ReadOnlyItemManager<Pilot> getPilotManager() {
        return pilotManager;
    }

    @Override
    public Path getPilotManagerFilePath() {
        return userPrefs.getPilotManagerFilePath();
    }

    @Override
    public void setPilotManagerFilePath(Path pilotManagerFilePath) {
        requireNonNull(pilotManagerFilePath);
        userPrefs.setPilotManagerFilePath(pilotManagerFilePath);
    }

    @Override
    public void setPilotManager(ReadOnlyItemManager<Pilot> pilotManager) {
        this.pilotManager.resetData(pilotManager);
    }

    @Override
    public boolean hasPilot(Pilot pilot) {
        requireNonNull(pilot);
        return pilotManager.hasItem(pilot);
    }

    @Override
    public void deletePilot(Pilot target) {
        pilotManager.removeItem(target);
    }

    @Override
    public void deletePilot(String id) {
        pilotManager.removeItem(id);
    }

    @Override
    public void deletePilotByIndex(int index) throws IndexOutOfBoundException {
        pilotManager.removeItemByIndex(index);
    }

    @Override
    public void addPilot(Pilot pilot) {
        requireNonNull(pilot);
        pilotManager.addItem(pilot);
    }

    @Override
    public void setPilot(Pilot target, Pilot editedPilot) {
        CollectionUtil.requireAllNonNull(target, editedPilot);
        pilotManager.setItem(target, editedPilot);
    }

    @Override
    public boolean checkPilot(String id) {
        Optional<Pilot> temp = pilotManager.getItemOptional(id);

        if (temp.isPresent()) {
            Pilot pilotToCheck = temp.get();
            return pilotToCheck.isAvailable();
        } else {
            throw new PilotNotFoundException();
        }
    }

    @Override
    public boolean checkPilotByIndex(int index) throws IndexOutOfBoundException {
        Optional<Pilot> pilot = pilotManager.getItemOptional(index);

        if (pilot.isPresent()) {
            Pilot pilotToCheck = pilot.get();
            return pilotToCheck.isAvailable();
        } else {
            throw new PilotNotFoundException();
        }
    }

    @Override
    public ObservableList<Pilot> getFilteredPilotList() {
        return filteredPilots;
    }

    @Override
    public void updateFilteredPilotList(Predicate<Pilot> predicate) {
        requireNonNull(predicate);
        filteredPilots.setPredicate(predicate);
    }


    //=========== Location ========================================================

    @Override
    public Location getLocationById(String id) {
        Location locationToFind = null;
        for (Location location : getFilteredLocationList()) {
            if (location.getId().equals(id)) {
                locationToFind = location;
            }
        }
        if (locationToFind == null) {
            throw new LocationNotFoundException();
        }
        return locationToFind;
    }

    @Override
    public ReadOnlyItemManager<Location> getLocationManager() {
        return locationManager;
    }

    @Override
    public Path getLocationManagerFilePath() {
        return userPrefs.getPilotManagerFilePath();
    }

    @Override
    public void setLocationManagerFilePath(Path locationManagerFilePath) {
        requireNonNull(locationManagerFilePath);
        userPrefs.setPilotManagerFilePath(locationManagerFilePath);
    }

    @Override
    public void setLocationManager(ReadOnlyItemManager<Location> locationManager) {
        this.locationManager.resetData(locationManager);
    }

    @Override
    public boolean hasLocation(Location location) {
        requireNonNull(location);
        return locationManager.hasItem(location);
    }

    @Override
    public void deleteLocation(Location location) {
        locationManager.removeItem(location);
    }

    @Override
    public void deleteLocation(String id) {
        locationManager.removeItem(id);
    }

    @Override
    public void deleteLocationByIndex(int index) throws IndexOutOfBoundException {
        locationManager.removeItemByIndex(index);
    }

    @Override
    public void addLocation(Location location) throws DuplicateItemException {
        requireNonNull(location);
        locationManager.addItem(location);
    }

    @Override
    public void setLocation(Location target, Location editedLocation) {
        CollectionUtil.requireAllNonNull(target, editedLocation);
        locationManager.setItem(target, editedLocation);
    }

    @Override
    public ObservableList<Location> getFilteredLocationList() {
        return filteredLocations;
    }

    @Override
    public void updateFilteredLocationList(Predicate<Location> predicate) {
        requireNonNull(predicate);
        filteredLocations.setPredicate(predicate);
    }


    //=========== Crew ========================================================

    @Override
    public void setCrewManager(ReadOnlyItemManager<Crew> crewManager) {
        requireNonNull(crewManager);
        this.crewManager.resetData(crewManager);
    }

    @Override
    public ReadOnlyItemManager<Crew> getCrewManager() {
        return crewManager;
    }

    @Override
    public Path getCrewManagerFilePath() {
        return userPrefs.getCrewManagerFilePath();
    }

    @Override
    public void setCrewManagerFilePath(Path crewManagerFilePath) {
        requireNonNull(crewManagerFilePath);
        userPrefs.setCrewManagerFilePath(crewManagerFilePath);
    }

    @Override
    public boolean hasCrew(Crew crew) {
        requireNonNull(crew);
        return this.crewManager.hasItem(crew);
    }

    @Override
    public boolean hasCrew(String id) {
        requireNonNull(id);
        return this.crewManager.hasItem(id);
    }

    @Override
    public void addCrew(Crew crew) throws DuplicateItemException {
        requireNonNull(crew);
        crewManager.addItem(crew);
    }

    @Override
    public void deleteCrew(Crew crew) {
        crewManager.removeItem(crew);
    }

    @Override
    public void deleteCrew(String id) {
        crewManager.removeItem(id);
    }

    @Override
    public void deleteCrew(int index) throws IndexOutOfBoundException {
        crewManager.removeItemByIndex(index);
    }

    @Override
    public void deleteCrewByIndex(int index) throws IndexOutOfBoundException {
        crewManager.removeItemByIndex(index);
    }

    @Override
    public void setCrew(Crew target, Crew editedCrew) {
        CollectionUtil.requireAllNonNull(target, editedCrew);
        crewManager.setItem(target, editedCrew);
    }

    @Override
    public boolean checkCrew(String id) {
        Optional<Crew> temp = crewManager.getItemOptional(id);

        if (temp.isPresent()) {
            Crew crewToCheck = temp.get();
            return crewToCheck.isAvailable();
        } else {
            throw new CrewNotFoundException();
        }
    }

    @Override
    public boolean checkCrewByIndex(int index) throws CrewNotFoundException {
        Optional<Crew> crew = crewManager.getItemOptional(index);

        if (crew.isPresent()) {
            Crew crewToCheck = crew.get();
            return crewToCheck.isAvailable();
        } else {
            throw new CrewNotFoundException();
        }
    }

    @Override
    public void updateFilteredCrewList(Predicate<Crew> predicate) {
        requireNonNull(predicate);
        filteredCrew.setPredicate(predicate);
    }

    @Override
    public ObservableList<Crew> getFilteredCrewList() {
        return filteredCrew;
    }


    //=========== Plane ========================================================
    @Override
    public void setPlaneManager(ReadOnlyItemManager<Plane> planeManager) {
        this.planeManager.resetData(planeManager);
    }

    @Override
    public ReadOnlyItemManager<Plane> getPlaneManager() {
        return this.planeManager;
    }

    @Override
    public void addPlane(Plane plane) throws DuplicateItemException {
        CollectionUtil.requireAllNonNull(plane);
        planeManager.addItem(plane);
    }

    @Override
    public void deletePlane(Plane plane) {
        planeManager.removeItem(plane);
    }

    @Override
    public void deletePlane(String id) {
        planeManager.removeItem(id);
    }

    @Override
    public void deletePlaneByIndex(int index) throws IndexOutOfBoundException {
        planeManager.removeItemByIndex(index);
    }

    @Override
    public boolean hasPlane(Plane plane) {
        requireNonNull(plane);
        return planeManager.hasItem(plane);
    }

    @Override
    public boolean hasPlane(String id) {
        return planeManager.hasItem(id);
    }

    @Override
    public void setPlane(Plane target, Plane editedPlane) {
        CollectionUtil.requireAllNonNull(target, editedPlane);
        planeManager.setItem(target, editedPlane);
    }

    @Override
    public boolean checkPlane(String id) {
        Optional<Plane> temp = planeManager.getItemOptional(id);

        if (temp.isPresent()) {
            Plane planeToCheck = temp.get();
            return planeToCheck.isAvailable();
        } else {
            throw new PlaneNotFoundException();
        }
    }

    @Override
    public boolean checkPlaneByIndex(int index) throws IndexOutOfBoundException {
        Optional<Plane> plane = planeManager.getItemOptional(index);

        if (plane.isPresent()) {
            Plane planeToCheck = plane.get();
            return planeToCheck.isAvailable();
        } else {
            throw new PlaneNotFoundException();
        }
    }

    @Override
    public ObservableList<Plane> getFilteredPlaneList() {
        return filteredPlanes;
    }

    @Override
    public void updateFilteredPlaneList(Predicate<Plane> predicate) {
        requireNonNull(predicate);
        filteredPlanes.setPredicate(predicate);
    }


    //=========== Flight ========================================================

    @Override
    public Flight getFlightById(String id) {
        Flight flightToFind = null;
        for (Flight flight : getFilteredFlightList()) {
            if (flight.getId().equals(id)) {
                flightToFind = flight;
            }
        }
        if (flightToFind == null) {
            throw new FlightNotFoundException();
        }
        return flightToFind;
    }

    @Override
    public ReadOnlyItemManager<Flight> getFlightManager() {
        return flightManager;
    }

    @Override
    public Path getFlightManagerFilePath() {
        return userPrefs.getFlightManagerFilePath();
    }

    @Override
    public void setFlightManagerFilePath(Path flightManagerFilePath) {
        requireNonNull(flightManagerFilePath);
        userPrefs.setFlightManagerFilePath(flightManagerFilePath);
    }

    @Override
    public void setFlightManager(ReadOnlyItemManager<Flight> flightManager) {
        this.flightManager.resetData(flightManager);
    }

    @Override
    public boolean hasFlight(Flight flight) {
        requireNonNull(flight);
        return flightManager.hasItem(flight);
    }

    @Override
    public void deleteFlight(Flight target) {
        flightManager.removeItem(target);
    }

    @Override
    public void deleteFlight(String id) {
        flightManager.removeItem(id);
    }

    @Override
    public void deleteFlightByIndex(int index) throws IndexOutOfBoundException {
        flightManager.removeItemByIndex(index);
    }

    @Override
    public void addFlight(Flight flight) throws DuplicateItemException {
        requireNonNull(flight);
        flightManager.addItem(flight);
    }

    @Override
    public void setFlight(Flight target, Flight editedFlight) {
        CollectionUtil.requireAllNonNull(target, editedFlight);
        flightManager.setItem(target, editedFlight);
    }

    @Override
    public ObservableList<Flight> getFilteredFlightList() {
        return filteredFlights;
    }

    @Override
    public void updateFilteredFlightList(Predicate<Flight> predicate) {
        requireNonNull(predicate);
        filteredFlights.setPredicate(predicate);
    }


    //=========== Generic ========================================================

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return userPrefs.equals(other.userPrefs)
                       && pilotManager.equals(other.pilotManager)
                       && crewManager.equals(other.crewManager)
                       && planeManager.equals(other.planeManager)
                       && flightManager.equals(other.flightManager)
                       && locationManager.equals(other.locationManager);
    }

    @Override
    public <T extends Item> boolean isIndexValid(
            int index,
            ReadOnlyItemManager<T> itemManager
    ) {
        return index <= itemManager.size() && index > 0;
    }
}
