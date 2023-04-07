package wingman.logic.plane.linkflight;

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
import wingman.model.exception.IndexOutOfBoundException;
import wingman.model.flight.Flight;
import wingman.model.plane.FlightPlaneType;
import wingman.model.plane.Plane;

/**
 * The factory that creates {@code LinkPlaneCommand}.
 */
public class PlaneFlightLinkCommandFactory<T extends Command>
        extends LinkFactoryBase<T, Flight, Plane, FlightPlaneType> {
    private static final String COMMAND_WORD = "linkflight";
    private static final String FLIGHT_PREFIX = "/fl";
    private static final String PLANE_USING_PREFIX = "/pl";
    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered.\n"
                    + "Please enter /pl followed by the plane ID.";

    private final PlaneFlightCommandFactory<T> linkFunction;

    /**
     * Creates a new link command factory with the model registered.
     */
    public PlaneFlightLinkCommandFactory(
            PlaneFlightCommandFactory<T> linkFunction
    ) {
        this(GetUtil.getLazy(Model.class), linkFunction);
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public PlaneFlightLinkCommandFactory(
            Lazy<Model> modelLazy,
            PlaneFlightCommandFactory<T> linkFunction
    ) {
        this(
                modelLazy.map(Model::getFlightManager),
                modelLazy.map(Model::getPlaneManager),
                linkFunction
        );
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param flightManagerLazy the lazy instance of the flight manager.
     * @param planeManagerLazy  the lazy instance of the plane manager.
     * @param linkFunction      the link function used to create the link
     *                          command.
     */
    public PlaneFlightLinkCommandFactory(
            Lazy<ReadOnlyItemManager<Flight>> flightManagerLazy,
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy,
            PlaneFlightCommandFactory<T> linkFunction
    ) {
        super(flightManagerLazy, planeManagerLazy);
        this.linkFunction = linkFunction;
    }

    /**
     * Creates a new link plane command factory.
     *
     * @return the link plane command factory.
     */
    public static PlaneFlightLinkCommandFactory<LinkPlaneToFlightCommand> linkFactory() {
        return new PlaneFlightLinkCommandFactory<>(LinkPlaneToFlightCommand::new);
    }

    /**
     * Creates a new unlink plane command factory.
     *
     * @return the unlink plane command factory.
     */
    public static PlaneFlightLinkCommandFactory<UnlinkPlaneToFlightCommand> unlinkFactory() {
        return new PlaneFlightLinkCommandFactory<>(UnlinkPlaneToFlightCommand::new);
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.of(Set.of(
                FLIGHT_PREFIX,
                PLANE_USING_PREFIX
        ));
    }

    @Override
    public T createCommand(CommandParam param)
            throws ParseException, IndexOutOfBoundException, CommandException {
        Optional<String> planeUsingIdOptional =
                param.getNamedValues(PLANE_USING_PREFIX);

        Flight flight = getSourceOrThrow(
                FLIGHT_PREFIX,
                param.getNamedValues(FLIGHT_PREFIX)
        );
        Map<FlightPlaneType, Plane> planes = new HashMap<>();

        boolean hasFoundPlane = addTarget(
                PLANE_USING_PREFIX,
                planeUsingIdOptional,
                FlightPlaneType.PLANE_USING,
                planes
        );

        if (!hasFoundPlane) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        return linkFunction.apply(flight, planes);
    }

    @Override
    protected String getCommandFormatHint() {
        return "(un)linkflight /fl flight-index /pl plane-index";
    }

    /**
     * The functional interface that creates a link command.
     *
     * @param <T> the type of the command.
     */
    @FunctionalInterface
    public interface PlaneFlightCommandFactory<T extends Command>
            extends LinkFunction<T, Flight, Plane, FlightPlaneType> {
    }
}
