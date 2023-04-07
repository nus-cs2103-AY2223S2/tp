package wingman.logic.plane.linklocation;

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
import wingman.model.location.PlaneLocationType;
import wingman.model.plane.Plane;

/**
 * Links a plane to location.
 * The location could be a place where they reside.
 * To link a plane to a flight, the plane should reside in
 * the same location as the flight's starting location.
 */
public class PlaneLocationLinkCommandFactory<T extends Command>
        extends LinkFactoryBase<T, Location, Plane, PlaneLocationType> {
    private static final String LINK_COMMAND_WORD = "linklocation";
    private static final String UNLINK_COMMAND_WORD = "unlinklocation";
    private static final String LOCATION_PREFIX = "/lo";
    private static final String PLANE_PREFIX = "/pl";

    private static final String NO_PLANE_MESSAGE =
            "%s: No plane has been entered.\n"
                    + "Please enter /pl followed by the plane ID.\n"
                    + "Command format: %s";
    private static final String COMMAND_FORMAT =
            "%s /lo location-index /pl plane-index";

    private final PlaneLocationLinkFunction<T> linkFunction;
    private final String commandWord;

    /**
     * Creates a new link command factory with the model registered.
     */
    public PlaneLocationLinkCommandFactory(
            PlaneLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction, commandWord);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public PlaneLocationLinkCommandFactory(
            Lazy<Model> modelLazy,
            PlaneLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        this(
                modelLazy.map(Model::getLocationManager),
                modelLazy.map(Model::getPlaneManager),
                linkFunction,
                commandWord
        );
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param locationManagerLazy the lazy instance of the location manager.
     * @param planeManagerLazy    the lazy instance of the plane manager.
     */
    public PlaneLocationLinkCommandFactory(
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy,
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy,
            PlaneLocationLinkFunction<T> linkFunction,
            String commandWord
    ) {
        super(locationManagerLazy, planeManagerLazy);
        this.linkFunction = linkFunction;
        this.commandWord = commandWord;
    }

    /**
     * Creates a new link plane command factory.
     *
     * @return a new link plane command factory.
     */
    public static PlaneLocationLinkCommandFactory<LinkPlaneToLocationCommand> linkFactory() {
        return new PlaneLocationLinkCommandFactory<>(
                LinkPlaneToLocationCommand::new,
                LINK_COMMAND_WORD
        );
    }

    /**
     * Creates a new unlink plane command factory.
     *
     * @return a new unlink plane command factory.
     */
    public static PlaneLocationLinkCommandFactory<UnlinkPlaneToLocationCommand> unlinkFactory() {
        return new PlaneLocationLinkCommandFactory<>(
                UnlinkPlaneToLocationCommand::new,
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
                PLANE_PREFIX
        ));
    }

    @Override
    public T createCommand(CommandParam param)
            throws ParseException, CommandException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> planeIdOptional =
                param.getNamedValues(PLANE_PREFIX);

        Location location = getSourceOrThrow(
                PLANE_PREFIX,
                locationIdOptional
        );
        Map<PlaneLocationType, Plane> plane = new HashMap<>();

        boolean hasFoundPlane = addTarget(
                LOCATION_PREFIX,
                planeIdOptional,
                PlaneLocationType.LOCATION_USING,
                plane
        );

        if (!hasFoundPlane) {
            throw ParseException.formatted(
                    NO_PLANE_MESSAGE,
                    commandWord,
                    getCommandFormatHint()
            );
        }

        return linkFunction.apply(location, plane);
    }

    @Override
    protected String getCommandFormatHint() {
        return String.format(COMMAND_FORMAT, commandWord);
    }

    /**
     * A functional interface for creating a link command.
     *
     * @param <T> the type of the command.
     */
    @FunctionalInterface
    public interface PlaneLocationLinkFunction<T extends Command>
            extends LinkFunction<T, Location, Plane, PlaneLocationType> {
    }
}
