package seedu.address.logic.plane.linklocation;

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
import seedu.address.model.location.Location;
import seedu.address.model.location.PlaneLocationType;
import seedu.address.model.plane.Plane;

/**
 * Links a plane to location.
 * The location could be a place where they reside.
 * To link a plane to a flight, the plane should reside in
 * the same location as the flight's starting location.
 *
 */
public class LinkPlaneToLocationCommandFactory implements CommandFactory<LinkPlaneToLocationCommand> {
    private static final String COMMAND_WORD = "linklocation";
    private static final String LOCATION_PREFIX = "/loc";
    private static final String PILOT_PREFIX = "/pl";

    private static final String NO_PLANE_MESSAGE =
            "No plane has been entered. "
                    + "Please enter /pl followed by the plane iD.";

    private static final String NO_LOCATION_INPUT_MESSAGE =
            "No location has been entered. "
                    + "Please enter /loc followed by the location ID.";

    private static final String NO_LOCATION_FOUND_MESSAGE =
            "No location has been found in the list. "
                    + "Please enter /loc followed by the location ID.";

    private final Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy;

    private final Lazy<ReadOnlyItemManager<Location>> locationManagerLazy;

    /**
     * Creates a new link command factory with the model registered.
     *
     */
    public LinkPlaneToLocationCommandFactory() {
        this(GetUtil.getLazy(Model.class));
    }

    /**
     * Creates a new link command factory with the given modelLazy.
     *
     * @param modelLazy the modelLazy used for the creation of the link command factory.
     */
    public LinkPlaneToLocationCommandFactory(Lazy<Model> modelLazy) {
        this(
                modelLazy.map(Model::getPlaneManager),
                modelLazy.map(Model::getLocationManager)
        );
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * lazy and the flight manager lazy.
     *
     * @param planeManagerLazy  the lazy instance of the plane manager.
     * @param locationManagerLazy the lazy instance of the location manager.
     */
    public LinkPlaneToLocationCommandFactory(
            Lazy<ReadOnlyItemManager<Plane>> planeManagerLazy,
            Lazy<ReadOnlyItemManager<Location>> locationManagerLazy
    ) {
        this.planeManagerLazy = planeManagerLazy;
        this.locationManagerLazy = locationManagerLazy;
    }

    /**
     * Creates a new link plane command factory with the given plane manager
     * and the location manager.
     *
     * @param planeManager  the plane manager.
     * @param locationManager the flight manager.
     */
    public LinkPlaneToLocationCommandFactory(
            ReadOnlyItemManager<Plane> planeManager,
            ReadOnlyItemManager<Location> locationManager
    ) {
        this(
                Lazy.of(planeManager),
                Lazy.of(locationManager)
        );
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

    private boolean addPlane(
            Optional<String> pilotIdOptional,
            PlaneLocationType type,
            Map<PlaneLocationType, Plane> target
    ) {
        if (pilotIdOptional.isEmpty()) {
            return false;
        }
        int indexOfPilot =
                Integer.parseInt(pilotIdOptional.get());
        Optional<Plane> planeOptional =
                planeManagerLazy.get().getItemByIndex(indexOfPilot);
        if (planeOptional.isEmpty()) {
            return false;
        }
        target.put(type, planeOptional.get());
        return true;
    }

    private Location getLocationOrThrow(
            Optional<String> locationIdOptional
    ) throws ParseException {
        if (locationIdOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_INPUT_MESSAGE);
        }
        int indexOfLocation =
                Integer.parseInt(locationIdOptional.get());
        Optional<Location> locationOptional =
                locationManagerLazy.get().getItemByIndex(indexOfLocation);
        if (locationOptional.isEmpty()) {
            throw new ParseException(NO_LOCATION_FOUND_MESSAGE);
        }

        return locationOptional.get();
    }

    @Override
    public LinkPlaneToLocationCommand createCommand(CommandParam param) throws ParseException {
        Optional<String> locationIdOptional =
                param.getNamedValues(LOCATION_PREFIX);
        Optional<String> pilotIdOptional =
                param.getNamedValues(PILOT_PREFIX);

        Map<PlaneLocationType, Plane> plane = new HashMap<>();

        boolean hasFoundPilot = addPlane(
                pilotIdOptional,
                PlaneLocationType.LOCATION_USING,
                plane
        );

        if (!hasFoundPilot) {
            throw new ParseException(NO_PLANE_MESSAGE);
        }

        Location location = getLocationOrThrow(locationIdOptional);
        return new LinkPlaneToLocationCommand(plane, location);
    }
}
