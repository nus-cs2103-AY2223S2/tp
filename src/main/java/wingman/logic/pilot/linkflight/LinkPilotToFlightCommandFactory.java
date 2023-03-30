package wingman.logic.pilot.linkflight;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import wingman.commons.fp.Lazy;
import wingman.commons.util.GetUtil;
import wingman.logic.core.Command;
import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.flight.Flight;
import wingman.model.pilot.FlightPilotType;
import wingman.model.pilot.Pilot;

/**
 * The factory that's responsible for creating a new
 * <code>CommandFactory</code>.
 */
public class LinkPilotToFlightCommandFactory implements CommandFactory<LinkPilotToFlightCommand> {
    private static final String COMMAND_WORD = "linkflight";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String PILOT_FLYING_PREFIX = "/pf";
    private static final String PILOT_MONITORING_PREFIX = "/pm";

    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered.\n"
                    + "Please enter /fl followed by the flight ID.";
    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered.\n"
                    + "Please enter at least 1 of the following:\n"
                    + "     /pm for the Pilot Monitoring, "
                    + "/pf for the Pilot Flying.";

    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";

    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private final Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy;
    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     */
    public LinkPilotToFlightCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public LinkPilotToFlightCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getFlightManager),
                modelLazy.map(Model::getPilotManager)
        );
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param flightManagerLazy the lazy instance of the flight manager.
     * @param pilotManagerLazy  the lazy instance of the pilot manager.
     */
    public LinkPilotToFlightCommandFactory(
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy,
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy
    ) {
        this.flightManagerLazy = flightManagerLazy;
        this.pilotManagerLazy = pilotManagerLazy;
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * and the flight manager.
     *
     * @param flightManager the flight manager.
     * @param pilotManager  the pilot manager.
     */
    public LinkPilotToFlightCommandFactory(
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


    private boolean addPilot(Optional<String> pilotIdOptional, FlightPilotType type, Map<FlightPilotType, Pilot> target)
            throws CommandException {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }

        int pilotId;
        try {
            pilotId = Command.parseIntegerToZeroBasedIndex(pilotIdOptional.get());
        } catch (NumberFormatException e) {
            throw new CommandException(String.format(
                    INVALID_INDEX_VALUE_MESSAGE,
                    pilotIdOptional.get()
            ));
        }

        boolean isPilotIndexValid = (pilotId < pilotManagerLazy.get().size());
        if (!isPilotIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    pilotId + 1));
        }

        Optional<Pilot> pilotOptional = pilotManagerLazy.get().getItemOptional(pilotId);
        if (pilotOptional.isEmpty()) {
            return false;
        }
        target.put(type, pilotOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(Optional<String> flightIdOptional) throws ParseException, CommandException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }

        int flightId;
        try {
            flightId = Command.parseIntegerToZeroBasedIndex(flightIdOptional.get());
        } catch (NumberFormatException e) {
            throw new ParseException(String.format(INVALID_INDEX_VALUE_MESSAGE, flightIdOptional.get()));
        }

        boolean isFlightIndexValid = (flightId < flightManagerLazy.get().size());
        if (!isFlightIndexValid) {
            throw new CommandException(String.format(
                    INDEX_OUT_OF_BOUNDS_MESSAGE,
                    flightId + 1));
        }

        Optional<Flight> flightOptional = flightManagerLazy.get().getItemOptional(flightId);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }

        return flightOptional.get();
    }

    @Override
    public LinkPilotToFlightCommand createCommand(CommandParam param)
            throws ParseException, IndexOutOfBoundException {
        Optional<String> pilotFlyingIdOptional =
                param.getNamedValues(PILOT_FLYING_PREFIX);
        Optional<String> pilotMonitoringIdOptional =
                param.getNamedValues(PILOT_MONITORING_PREFIX);

        Flight flight;
        try {
            flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        Map<FlightPilotType, Pilot> pilots = new HashMap<>();

        boolean hasFoundPilot;
        try {
            hasFoundPilot = addPilot(
                    pilotFlyingIdOptional,
                    FlightPilotType.PILOT_FLYING,
                    pilots
            ) || addPilot(
                    pilotMonitoringIdOptional,
                    FlightPilotType.PILOT_MONITORING,
                    pilots
            );
        } catch (CommandException e) {
            throw new ParseException(e.getMessage());
        }

        if (!hasFoundPilot) {
            throw new ParseException(NO_PILOT_MESSAGE);
        }

        return new LinkPilotToFlightCommand(flight, pilots);
    }
}
