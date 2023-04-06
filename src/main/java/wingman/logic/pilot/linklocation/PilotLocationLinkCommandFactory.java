package wingman.logic.pilot.linklocation;

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
import wingman.model.location.Location;
import wingman.model.location.PilotLocationType;
import wingman.model.pilot.Pilot;

/**
 * Links a pilot to location.
 * The location could be a place where they reside.
 * To link a pilot to a flight, the pilot should reside in
 * the same location as the flight's starting location.
 */
public class PilotLocationLinkCommandFactory<T extends Command>
        extends LinkFactoryBase<T, Location, Pilot, PilotLocationType> {
    private static final String COMMAND_WORD = "linklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String PILOT_PREFIX = "/pi";

    private static final String NO_PILOT_MESSAGE =
            "No pilot has been entered.\n"
                    + "Please enter /pi followed by the pilot ID.";

    private final PilotLocationLinkFunction<T> linkFunction;

    /**
     * Creates a new link command factory with the model registered.
     */
    public PilotLocationLinkCommandFactory(
            PilotLocationLinkFunction<T> linkFunction
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public PilotLocationLinkCommandFactory(
            Lazy<Model> modelLazy,
            PilotLocationLinkFunction<T> linkFunction
    ) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getPilotManager),
                linkFunction
        );
    }

    /**
     * Creates a new link pilot command factory with the given pilot manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param pilotManagerLazy    the lazy instance of the pilot manager.
     */
    public PilotLocationLinkCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Pilot>> pilotManagerLazy,
            PilotLocationLinkFunction<T> linkFunction
    ) {
        super(locationManagerLazy, pilotManagerLazy);
        this.linkFunction = linkFunction;
    }

    public static PilotLocationLinkCommandFactory<LinkPilotToLocationCommand> linkFactory() {
        return new PilotLocationLinkCommandFactory<>(LinkPilotToLocationCommand::new);
    }

    public static PilotLocationLinkCommandFactory<UnlinkPilotToLocationCommand> unlinkFactory() {
        return new PilotLocationLinkCommandFactory<>(
                UnlinkPilotToLocationCommand::new);
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                LOCATION_PREFIX,
                PILOT_PREFIX
        ));
    }

    @Override
    public T createCommand(CommandParam param) throws ParseException, CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PILOT_PREFIX);

        Location location = getSourceOrThrow(locationIdOptional);
        Map<PilotLocationType, Pilot> pilot = new HashMap<>();

        boolean hasFoundPilot = addTarget(
                pilotIdOptional,
                PilotLocationType.LOCATION_USING,
                pilot
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PILOT_MESSAGE);
        }

        return linkFunction.apply(location, pilot);
    }

    @FunctionalInterface
    public interface PilotLocationLinkFunction<T extends Command>
            extends LinkFunction<T, Location, Pilot, PilotLocationType> {
    }
}
