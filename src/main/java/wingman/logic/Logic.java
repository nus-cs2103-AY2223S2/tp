package wingman.logic;

import javafx.collections.ObservableList;
import wingman.commons.core.GuiSettings;
import wingman.logic.core.CommandResult;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.OperationMode;
import wingman.model.crew.Crew;
import wingman.model.flight.Flight;
import wingman.model.item.Item;
import wingman.model.location.Location;
import wingman.model.pilot.Pilot;
import wingman.model.plane.Plane;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Gets the filtered list of items.
     *
     * @return the filtered list of items.
     */
    ObservableList<Item> getFilteredItemList();

    /**
     * Gets the current operation mode.
     *
     * @return the current operation mode.
     */
    OperationMode getOperationMode();

    /**
     * Gets the filtered list of flights.
     *
     * @return the filtered list of flights.
     */
    ObservableList<Flight> getFilteredFlightList();

    /**
     * Gets the filtered list of crew.
     *
     * @return the filtered list of crew.
     */
    ObservableList<Crew> getFilteredCrewList();

    /**
     * Gets the filtered list of planes.
     *
     * @return the filtered list of planes.
     */
    ObservableList<Plane> getFilteredPlaneList();

    /**
     * Gets the filtered list of pilots.
     *
     * @return the filtered list of pilots.
     */
    ObservableList<Pilot> getFilteredPilotList();

    /**
     * Gets the filtered list of locations.
     *
     * @return the filtered list of locations.
     */
    ObservableList<Location> getFilteredLocationList();
}
