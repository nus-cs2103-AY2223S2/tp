package seedu.address.logic.crew.unlinkflight;

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
import seedu.address.model.crew.Crew;
import seedu.address.model.crew.FlightCrewType;
import seedu.address.model.exception.IndexOutOfBoundException;
import seedu.address.model.flight.Flight;


/**
 * The factory that creates {@code UnlinkCrewCommand}.
 */
public class UnlinkCrewToFlightCommandFactory implements CommandFactory<UnlinkCrewToFlightCommand> {
    private static final String COMMAND_WORD = "unlinkflight";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String CABIN_SERVICE_DIRECTOR_PREFIX = "/csd";
    private static final String SENIOR_FLIGHT_ATTENDANT_PREFIX = "/sfa";
    private static final String FLIGHT_ATTENDANT_PREFIX = "/fa";
    private static final String TRAINEE_PREFIX = "/tr";

    private static final String NO_CREW_MESSAGE =
            "No crew has been entered. Please enter /csd for the Cabin Service Director,"
                    + " /sfa for Senior Flight Attendants,"
                    + " /fa for Flight Attendants or /tr for Trainees";
    private static final String NO_FLIGHT_MESSAGE =
            "No flight has been entered. Please enter /fl for the flight.";

    private final Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy;
    private final Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy;

    /**
     * Creates a new unlink command factory with the model registered.
     */
    public UnlinkCrewToFlightCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new unlink command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the unlink command factory.
     */
    public UnlinkCrewToFlightCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getCrewManager),
                modelLazy.map(Model::getFlightManager)
        );
    }

    /**
     * Creates a new unlink crew command factory with the given crew manager
     * lazy and the flight manager lazy.
     *
     * @param crewManagerLazy   the lazy instance of the crew manager.
     * @param flightManagerLazy the lazy instance of the flight manager.
     */
    public UnlinkCrewToFlightCommandFactory(
            Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy
    ) {
        this.crewManagerLazy = crewManagerLazy;
        this.flightManagerLazy = flightManagerLazy;
    }

    /**
     * Creates a new unlink crew command factory with the given crew manager
     * and the flight manager.
     *
     * @param crewManager   the crew manager.
     * @param flightManager the flight manager.
     */
    public UnlinkCrewToFlightCommandFactory(
            ReadOnlyItemManager<Crew> crewManager,
            ReadOnlyItemManager<Flight> flightManager
    ) {
        this(Lazy.of(crewManager), Lazy.of(flightManager));
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                CABIN_SERVICE_DIRECTOR_PREFIX,
                SENIOR_FLIGHT_ATTENDANT_PREFIX,
                FLIGHT_ATTENDANT_PREFIX,
                TRAINEE_PREFIX,
                FLIGHT_PREFIX
        ));
    }

    private boolean addCrew(
            Optional<String> crewIdOptional,
            FlightCrewType type,
            Map<FlightCrewType, Crew> target
    ) throws IndexOutOfBoundException, CommandException {
        if (crewIdOptional.isEmpty()) {
            return false;
        }
        int indexOfCrew =
                Command.parseIntegerToZeroBasedIndex(crewIdOptional.get());
        Optional<Crew> crewOptional =
                crewManagerLazy.get().getItemOptional(indexOfCrew);
        if (crewOptional.isEmpty()) {
            return false;
        }
        target.put(type, crewOptional.get());
        return true;
    }

    private Flight getFlightOrThrow(
            Optional<String> flightIdOptional
    ) throws ParseException, IndexOutOfBoundException, CommandException {
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
    public UnlinkCrewToFlightCommand createCommand(CommandParam param)
            throws ParseException, IndexOutOfBoundException, CommandException {
        Optional<String> cabinServiceDirectorIdOptional =
                param.getNamedValues(CABIN_SERVICE_DIRECTOR_PREFIX);
        Optional<String> seniorFlightAttendantIdOptional =
                param.getNamedValues(SENIOR_FLIGHT_ATTENDANT_PREFIX);
        Optional<String> flightAttendantIdOptional =
                param.getNamedValues(FLIGHT_ATTENDANT_PREFIX);
        Optional<String> traineeIdOptional =
                param.getNamedValues(TRAINEE_PREFIX);

        Flight flight = getFlightOrThrow(param.getNamedValues(FLIGHT_PREFIX));
        Map<FlightCrewType, Crew> crews = new HashMap<>();

        boolean hasFoundCrew = addCrew(
                cabinServiceDirectorIdOptional,
                FlightCrewType.CABIN_SERVICE_DIRECTOR,
                crews
        ) || addCrew(
                seniorFlightAttendantIdOptional,
                FlightCrewType.SENIOR_FLIGHT_ATTENDANT,
                crews
        ) || addCrew(
                flightAttendantIdOptional,
                FlightCrewType.FLIGHT_ATTENDANT,
                crews
        ) || addCrew(
                traineeIdOptional,
                FlightCrewType.TRAINEE,
                crews
        );

        if (!hasFoundCrew) {
            throw new ParseException(NO_CREW_MESSAGE);
        }

        return new UnlinkCrewToFlightCommand(flight, crews);
    }
}
