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
    private static final String LINK_COMMAND_WORD = "linklocation";
    private static final String UNLINK_COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String PILOT_PREFIX = "/pi";

    private static final String NO_PILOT_MESSAGE =
            "%s: No pilot has been entered.\n"
                    + "Please enter /pi followed by the pilot ID.\n"
                    + "Command format: %s";

    private static final String COMMAND_FORMAT =
            "%s /lo location-index /pi pilot-index";

    private final PilotLocationLinkFunction<T> linkFunction;

    private final String commandWord;

    /**
     * Creates a new link command factory with the model registered.
     */
    public PilotLocationLinkCommandFactory(
            PilotLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction, commandWord);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public PilotLocationLinkCommandFactory(
            Lazy<Model> modelLazy,
            PilotLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getPilotManager),
                linkFunction,
                commandWord
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
            PilotLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        super(locationManagerLazy, pilotManagerLazy);
        this.linkFunction = linkFunction;
        this.commandWord = commandWord;
    }

    /**
     * Creates a new link command factory with the model registered.
     *
     * @return the link command factory.
     */
    public static PilotLocationLinkCommandFactory<LinkPilotToLocationCommand> linkFactory() {
        return new PilotLocationLinkCommandFactory<>(
                LinkPilotToLocationCommand::new,
                LINK_COMMAND_WORD
        );
    }

    /**
     * Creates a new unlink command factory with the model registered.
     *
     * @return the unlink command factory.
     */
    public static PilotLocationLinkCommandFactory<UnlinkPilotToLocationCommand> unlinkFactory() {
        return new PilotLocationLinkCommandFactory<>(
                UnlinkPilotToLocationCommand::new,
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

        Location location = getSourceOrThrow(
                LOCATION_PREFIX,
                locationIdOptional
        );
        Map<PilotLocationType, Pilot> pilot = new HashMap<>();

        boolean hasFoundPilot = addTarget(
                PILOT_PREFIX,
                pilotIdOptional,
                PilotLocationType.LOCATION_USING,
                pilot
        );

        if (!hasFoundPilot) {
            throw ParseException.formatted(
                    NO_PILOT_MESSAGE,
                    commandWord,
                    getCommandFormatHint()
            );
        }

        return linkFunction.apply(location, pilot);
    }

    @Override
    protected String getCommandFormatHint() {
        return String.format(COMMAND_FORMAT, commandWord);
    }

    /**
     * Represents a function that accepts a location, a pilot and returns a command.
     *
     * @param <T> the type of the command.
     */
    @FunctionalInterface
    public interface PilotLocationLinkFunction<T extends Command>
            extends LinkFunction<T, Location, Pilot, PilotLocationType> {
    }
}
