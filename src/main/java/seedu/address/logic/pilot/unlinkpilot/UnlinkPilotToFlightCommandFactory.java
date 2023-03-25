package seedu.address.logic.pilot.unlinkpilot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.fp.Lazy;
import seedu.address.commons.util.GetUtil;
import seedu.address.logic.core.CommandFactory;
import seedu.address.logic.core.CommandParam;
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
    private static final String COMMAND_WORD = "unlink";
    private static final String PILOT_FLYING_PREFIX = "/pf";
    private static final String PILOT_MONITORING_PREFIX = "/pm";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered. Please enter /pm for the pilot monitoring"
                    + " or /pf for the pilot flying.";
    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered. Please enter /fl for the flight.";

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
                modelLazy.map(Model::getPilotManager),
                modelLazy.map(Model::getFlightManager)
        );
    }

    /**
     * Creates a new unlink pilot command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param pilotManagerLazy  the lazy instance of the pilot manager.
     * @param flightManagerLazy the lazy instance of the flight manager.
     */
    public UnlinkPilotToFlightCommandFactory(
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy
    ) {
        this.pilotManagerLazy = pilotManagerLazy;
        this.flightManagerLazy = flightManagerLazy;
    }

    /**
     * Creates a new unlink pilot command factory with the given pilot manager
     * and the flight manager.
     *
     * @param pilotManager  the pilot manager.
     * @param flightManager the flight manager.
     */
    public UnlinkPilotToFlightCommandFactory(
            ReadOnlyItemManager<Pilot> pilotManager,
            ReadOnlyItemManager<Flight> flightManager
    ) {
        this(Lazy.of(pilotManager), Lazy.of(flightManager));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                PILOT_FLYING_PREFIX,
                FLIGHT_PREFIX,
                PILOT_MONITORING_PREFIX
        ));
    }

    private boolean addPilot(
            Optional<String> pilotIdOptional,
            FlightPilotType type,
            Map<FlightPilotType, Pilot> target
    ) {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPilot =
                Integer.parseInt(pilotIdOptional.get());
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
    ) throws ParseException {
        if (flightIdOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        int indexOfFlight =
                Integer.parseInt(flightIdOptional.get());
        Optional<Flight> flightOptional =
                flightManagerLazy.get().getItemOptional(indexOfFlight);
        if (flightOptional.isEmpty()) {
            throw new ParseException(NO_FLIGHT_MESSAGE);
        }
        return flightOptional.get();
    }


    @Override
    public UnlinkPilotToFlightCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> pilotFlyingIdOptional =
                param.getNamedValues(PILOT_FLYING_PREFIX);
        Optional<String> pilotMonitoringIdOptional =
                param.getNamedValues(PILOT_MONITORING_PREFIX);
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

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        return new UnlinkPilotToFlightCommand(pilots, flight);
    }
}
