package wingman.logic.crew.linkflight;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import wingman.commons.fp.Lazy;
import wingman.commons.util.GetUtil;
import wingman.logic.core.Command;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.logic.toplevel.link.LinkFactoryBase;
import wingman.logic.toplevel.link.LinkFunction;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.model.crew.FlightCrewType;
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.flight.Flight;

/**
 * The factory that creates {@code LinkCrewCommand}.
 */
public class CrewFlightLinkCommandFactory<T extends Command>
        extends LinkFactoryBase<T, Flight, Crew, FlightCrewType> {
    private static final String LINK_COMMAND_WORD = "linkflight";
    private static final String UNLINK_COMMAND_WORD = "unlinkflight";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String CABIN_SERVICE_DIRECTOR_PREFIX = "/csd";
    private static final String SENIOR_FLIGHT_ATTENDANT_PREFIX = "/sfa";
    private static final String FLIGHT_ATTENDANT_PREFIX = "/fa";
    private static final String TRAINEE_PREFIX = "/tr";
    private static final String NO_CREW_MESSAGE =
            "%s: No crew has been entered.\n"
                    + "Please enter at least 1 of the following:\n"
                    + "     /csd for the Cabin Service Director, "
                    + "/sfa for the Senior Flight Attendants,\n"
                    + "     /fa for the Flight Attendants, "
                    + "/tr for the Trainees. \nCommand format: %s";
    private static final String COMMAND_FORMAT =
            "%s /fl flight-index /csd crew-index /sfa crew-index /fa "
                    + "crew-index /tr crew-index\n"
                    + "/csd: Cabin Service Director, /sfa: Senior Flight "
                    + "Attendant, /fa: Flight Attendant, /tr: Trainee";

    private final CrewFlightLinkFunction<T> linkFunction;
    private final String commandWord;

    /**
     * Creates a new link command factory with the model registered.
     */
    public CrewFlightLinkCommandFactory(
            CrewFlightLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction, commandWord);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public CrewFlightLinkCommandFactory(
            Lazy<Model> modelLazy,
            CrewFlightLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(
                modelLazy.map(Model::getCrewManager),
                modelLazy.map(Model::getFlightManager),
                linkFunction,
                commandWord
        );
    }

    /**
     * Creates a new link crew command factory with the given crew manager
     * lazy and the flight manager lazy.
     *
     * @param crewManagerLazy   the lazy instance of the crew manager.
     * @param flightManagerLazy the lazy instance of the flight manager.
     * @param linkFunction      the link function used to create the link
     *                          command.
     */
    public CrewFlightLinkCommandFactory(
            Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy,
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy,
            CrewFlightLinkFunction<T> linkFunction,
            String commandWord
    ) {
        super(flightManagerLazy, crewManagerLazy);
        this.linkFunction = linkFunction;
        this.commandWord = commandWord;
    }

    /**
     * Returns a new link crew command factory.
     *
     * @return a new link crew command factory.
     */
    public static CrewFlightLinkCommandFactory<LinkCrewToFlightCommand> linkFactory() {
        return new CrewFlightLinkCommandFactory<>(
                LinkCrewToFlightCommand::new,
                LINK_COMMAND_WORD
        );
    }

    /**
     * Returns a new unlink crew command factory.
     *
     * @return a new unlink crew command factory.
     */
    public static CrewFlightLinkCommandFactory<UnlinkCrewToFlightCommand> unlinkFactory() {
        return new CrewFlightLinkCommandFactory<>(
                UnlinkCrewToFlightCommand::new,
                UNLINK_COMMAND_WORD
        );
    }

    @Override
    public String getCommandWord() {
        return commandWord;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                FLIGHT_PREFIX,
                CABIN_SERVICE_DIRECTOR_PREFIX,
                SENIOR_FLIGHT_ATTENDANT_PREFIX,
                FLIGHT_ATTENDANT_PREFIX,
                TRAINEE_PREFIX
        ));
    }

    @Override
    public T createCommand(CommandParam param) throws ParseException,
                                                      IndexOutOfBoundException {
        Optional<String> cabinServiceDirectorIdOptional =
                param.getNamedValues(CABIN_SERVICE_DIRECTOR_PREFIX);
        Optional<String> seniorFlightAttendantIdOptional =
                param.getNamedValues(SENIOR_FLIGHT_ATTENDANT_PREFIX);
        Optional<String> flightAttendantIdOptional =
                param.getNamedValues(FLIGHT_ATTENDANT_PREFIX);
        Optional<String> traineeIdOptional =
                param.getNamedValues(TRAINEE_PREFIX);

        Flight flight = getSourceOrThrow(
                FLIGHT_PREFIX,
                param.getNamedValues(FLIGHT_PREFIX)
        );

        Map<FlightCrewType, Crew> crews = new HashMap<>();

        boolean hasFoundCrew = addTarget(
                CABIN_SERVICE_DIRECTOR_PREFIX,
                cabinServiceDirectorIdOptional,
                FlightCrewType.CABIN_SERVICE_DIRECTOR,
                crews
        ) | addTarget(
                SENIOR_FLIGHT_ATTENDANT_PREFIX,
                seniorFlightAttendantIdOptional,
                FlightCrewType.SENIOR_FLIGHT_ATTENDANT,
                crews
        ) | addTarget(
                FLIGHT_ATTENDANT_PREFIX,
                flightAttendantIdOptional,
                FlightCrewType.FLIGHT_ATTENDANT,
                crews
        ) | addTarget(
                TRAINEE_PREFIX,
                traineeIdOptional,
                FlightCrewType.TRAINEE,
                crews
        );

        if (!hasFoundCrew) {
            throw ParseException.formatted(
                    NO_CREW_MESSAGE,
                    commandWord,
                    getCommandFormatHint()
            );
        }
        return linkFunction.apply(flight, crews);
    }

    @Override
    protected String getCommandFormatHint() {
        return String.format(COMMAND_FORMAT, commandWord);
    }

    /**
     * The functional interface that creates the link command.
     *
     * @param <T> the type of the link command.
     */
    @FunctionalInterface
    public interface CrewFlightLinkFunction<T extends Command>
            extends LinkFunction<T, Flight, Crew, FlightCrewType> {
    }
}
