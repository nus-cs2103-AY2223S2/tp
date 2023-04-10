package wingman.logic;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import wingman.commons.core.GuiSettings;
import wingman.commons.core.LogsCenter;
import wingman.logic.core.Command;
import wingman.logic.core.CommandResult;
import wingman.logic.core.WingmanParser;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.OperationMode;
import wingman.model.crew.Crew;
import wingman.model.flight.Flight;
import wingman.model.item.Item;
import wingman.model.location.Location;
import wingman.model.pilot.Pilot;
import wingman.model.plane.Plane;
import wingman.storage.Storage;

/**
 * The main LogicManager of the app.
 * <p>
 * TODO: remove the magic literals.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);
    private final Model model;
    private final Storage storage;
    private final WingmanParser parser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     *
     * @param model   the model to use.
     * @param storage the storage to use.
     * @param parser  the parser to use.
     */
    public LogicManager(Model model, Storage storage, WingmanParser parser) {
        this.model = model;
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}, using the default {@code WingmanParser}.
     *
     * @param model   the model to use.
     * @param storage the storage to use.
     */
    public LogicManager(Model model, Storage storage) {
        this(model, storage, new WingmanParser());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        final Command command = parser.parse(getOperationMode(), commandText);
        final CommandResult result = command.execute(model);
        this.save();
        return result;
    }

    /**
     * Saves the current state of the application to the storage.
     *
     * @throws CommandException if there was an error during saving.
     */
    private void save() throws CommandException {
        try {
            storage.savePilotManager(model.getPilotManager());
            storage.savePlaneManager(model.getPlaneManager());
            storage.saveCrewManager(model.getCrewManager());
            storage.saveLocationManager(model.getLocationManager());
            storage.saveFlightManager(model.getFlightManager());
            storage.saveUserPrefs(model.getUserPrefs());
        } catch (IOException e) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + e, e);
        }
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public ObservableList<Item> getFilteredItemList() {
        logger.info("Getting filtered item list: " + model
                                                             .getItemsList()
                                                             .size() + " "
                            + "items");
        return model.getItemsList();
    }

    @Override
    public ObservableList<Flight> getFilteredFlightList() {
        logger.info("Getting filtered flight list: " + model
                                                               .getFlightList()
                                                               .size() + " "
                            + "flights");
        return model.getFlightList();
    }

    @Override
    public ObservableList<Crew> getFilteredCrewList() {
        logger.info("Getting filtered crew list: " + model
                                                             .getCrewList()
                                                             .size() + " "
                            + "crew");
        return model.getCrewList();
    }

    @Override
    public ObservableList<Plane> getFilteredPlaneList() {
        logger.info("Getting filtered plane list: " + model
                                                              .getPlaneList()
                                                              .size() + " "
                            + "planes");
        return model.getPlaneList();
    }

    @Override
    public ObservableList<Pilot> getFilteredPilotList() {
        logger.info("Getting filtered pilot list: " + model
                                                              .getPilotList()
                                                              .size() + " "
                            + "pilots");
        return model.getPilotList();
    }

    @Override

    public ObservableList<Location> getFilteredLocationList() {
        logger.info("Getting filtered location list: " + model
                                                                 .getLocationList()
                                                                 .size() + " "
                            + "locations");
        return model.getLocationList();
    }

    @Override
    public OperationMode getOperationMode() {
        return model.getOperationMode();
    }
}
