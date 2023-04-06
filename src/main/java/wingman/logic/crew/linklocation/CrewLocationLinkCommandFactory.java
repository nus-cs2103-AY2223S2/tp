package wingman.logic.crew.linklocation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import wingman.commons.fp.Lazy;
import wingman.commons.util.GetUtil;
import wingman.logic.core.Command;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.CommandException;
import wingman.logic.core.exceptions.ParseException;
import wingman.logic.toplevel.link.LinkFactoryBase;
import wingman.logic.toplevel.link.LinkFunction;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.model.location.CrewLocationType;
import wingman.model.location.Location;

/**
 * Links a crew to location.
 * The location could be a place where they reside.
 * To link a crew to a flight, the crew should reside in
 * the same location as the flight's starting location.
 */
public class CrewLocationLinkCommandFactory<T extends Command>
        extends LinkFactoryBase<T, Location, Crew, CrewLocationType> {
    private static final String COMMAND_WORD = "linklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String CREW_PREFIX = "/cr";

    private static final String NO_LOCATION_MESSAGE =
            "No location has been entered.\n"
                    + "Please enter /lo followed by the location ID.";
    private static final String NO_CREW_MESSAGE =
            "No crew has been entered.\n"
                    + "Please enter /cr followed by the crew ID.";
    private static final String INVALID_INDEX_VALUE_MESSAGE =
            "%s is an invalid value.\n"
                    + "Please try using an integer instead.";
    private static final String INDEX_OUT_OF_BOUNDS_MESSAGE =
            "Index %s is out of bounds.\n"
                    + "Please enter a valid index.";

    private CrewLocationLinkFunction<T> linkFunction;

    /**
     * Creates a new link command factory with the model registered.
     */
    public CrewLocationLinkCommandFactory(CrewLocationLinkFunction<T> linkFunction) {
        this(GetUtil.getLazy(Model.class), linkFunction);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public CrewLocationLinkCommandFactory(
            Lazy<Model> modelLazy,
            CrewLocationLinkFunction<T> linkFunction
    ) {
        this(
                modelLazy.map(Model::getCrewManager),
                modelLazy.map(Model::getLocationManager),
                linkFunction
        );
    }

    /**
     * Creates a new link crew command factory with the given crew manager
     * lazy and the flight manager lazy.
     *
     * @param crewManagerLazy     the lazy instance of the crew manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public CrewLocationLinkCommandFactory(
            Lazy<ReadOnlyItemManager<Crew>> crewManagerLazy,
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            CrewLocationLinkFunction<T> linkFunction
    ) {
        super(locationManagerLazy, crewManagerLazy);
        this.linkFunction = linkFunction;
    }

    /**
     * Returns a new link command factory.
     *
     * @return a new link command factory.
     */
    public static CrewLocationLinkCommandFactory<LinkCrewToLocationCommand> linkFactory() {
        return new CrewLocationLinkCommandFactory<>(LinkCrewToLocationCommand::new);
    }

    /**
     * Returns a new unlink command factory.
     *
     * @return a new unlink command factory.
     */
    public static CrewLocationLinkCommandFactory<UnlinkCrewFromLocationCommand> unlinkFactory() {
        return new CrewLocationLinkCommandFactory<>(
                UnlinkCrewFromLocationCommand::new);
    }


    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                LOCATION_PREFIX,
                CREW_PREFIX
        ));
    }

    @Override
    public T createCommand(CommandParam param) throws ParseException,
                                                      CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> crewIdOptional =
                param.getNamedValues(CREW_PREFIX);

        Location location = getSourceOrThrow(locationIdOptional);
        Map<CrewLocationType, Crew> crews = new HashMap<>();

        boolean hasFoundCrew = addTarget(
                crewIdOptional,
                CrewLocationType.LOCATION_USING,
                crews
        );

        if (!hasFoundCrew) {
            throw new ParseException(NO_CREW_MESSAGE);
        }
        return linkFunction.create(location, crews);
    }

    /**
     * Returns the source location or throws a parse exception.
     *
     * @param <T> the type of the command.
     */
    @FunctionalInterface
    public interface CrewLocationLinkFunction<T extends Command>
            extends LinkFunction<T, Location, Crew, CrewLocationType> {
    }
}
