package seedu.address.logic.pilot.unlinkpilot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.fp.Lazy;
import seedu.address.commons.util.GetUtil;
import seedu.address.logic.core.Command;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
import seedu.address.logic.core.exceptions.CommandException;
import seedu.address.logic.core.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.flight.Flight;
import seedu.address.model.pilot.FlightPilotType;
import seedu.address.model.pilot.Pilot;


/**
 * The factory that creates {@code UnlinkPilotCommand}.
 */
public class UnlinkPilotToFlightCommandFactory implements CommandFactory<UnlinkPilotToFlightCommand> {
    private static final String COMMAND_WORD = "unlinkflight";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String PILOT_FLYING_PREFIX = "/pf";
    private static final String PILOT_MONITORING_PREFIX = "/pm";

    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered. Please enter /fl for the flight.";
    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered. Please enter /pm for the pilot monitoring"
                    + " or /pf for the pilot flying.";

    private final Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy;
    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new unlink command factory with the model registered.
     */
    public UnlinkPilotToFlightCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the unlink command factory.
     */
    public UnlinkPilotToFlightCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getFlightManager),
                modelLazy.map(Model::getPilotManager)
        );
    }

    /**
     * Creates a new unlink pilot command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param flightManagerLazy the lazy instance of the flight manager.
     * @param pilotManagerLazy  the lazy instance of the pilot manager.
     */
    public UnlinkPilotToFlightCommandFactory(
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy,
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy
    ) {
        this.flightManagerLazy = flightManagerLazy;
        this.pilotManagerLazy = pilotManagerLazy;
    }

    /**
     * Creates a new unlink pilot command factory with the given pilot manager
     * and the flight manager.
     *
     * @param flightManager the flight manager.
     * @param pilotManager  the pilot manager.
     */
    public UnlinkPilotToFlightCommandFactory(
            ReadOnlyItemManager<Flight> flightManager,
            ReadOnlyItemManager<Pilot> pilotManager
    ) {
        this(
                Lazy.of(flightManager),
                Lazy.of(pilotManager));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                FLIGHT_PREFIX,
                PILOT_FLYING_PREFIX,
                PILOT_MONITORING_PREFIX
        ));
    }

    private boolean addPilot(
            Optional<String> pilotIdOptional,
            FlightPilotType type,
            Map<FlightPilotType, Pilot> target
    ) throws CommandException {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPilot =
                Command.parseIntegerToZeroBasedIndex(pilotIdOptional.get());
        Optional<Pilot> pilotOptional =
                pilotManagerLazy.get().getItemOptional(indexOfPilot);
        if (pilotOptional.isEmpty()) {
            return false;
        }
        target.put(type, pilotOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(
            Optional<String> flightIdOptional
    ) throws ParseException, CommandException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        int indexOfFlight =
                Command.parseIntegerToZeroBasedIndex(flightIdOptional.get());
        Optional<Flight> flightOptional =
                flightManagerLazy.get().getItemOptional(indexOfFlight);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        return flightOptional.get();
    }


    @Override
    public UnlinkPilotToFlightCommand createCommand(CommandParam param)
            throws ParseException, CommandException {
        Optional<String> pilotFlyingIdOptional =
                param.getNamedValues(PILOT_FLYING_PREFIX);
        Optional<String> pilotMonitoringIdOptional =
                param.getNamedValues(PILOT_MONITORING_PREFIX);

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        Map<FlightPilotType, Pilot> pilots = new HashMap<>();

        boolean hasFoundPilot = addPilot(
                pilotFlyingIdOptional,
                FlightPilotType.PILOT_FLYING,
                pilots
        ) || addPilot(
                pilotMonitoringIdOptional,
                FlightPilotType.PILOT_MONITORING,
                pilots
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PILOT_MESSAGE);
        }

        return new UnlinkPilotToFlightCommand(flight, pilots);
    }
}
