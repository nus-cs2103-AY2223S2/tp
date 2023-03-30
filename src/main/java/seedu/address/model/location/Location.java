package seedu.address.model.location;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import seedu.address.commons.util.GetUtil;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyItemManager;
import seedu.address.model.crew.Crew;
import seedu.address.model.item.Item;
import seedu.address.model.link.Link;
import seedu.address.model.pilot.Pilot;
import seedu.address.model.plane.Plane;

/**
 * Location is a unit place that the flight can travel to or
 *  arrive at.
 */
public class Location implements Item {

    /**
     * The shape of the link between location and flight
     */
    public static final Map<FlightLocationType, Integer> SHAPE =
            Map.of(FlightLocationType.LOCATION_DEPARTURE, 1,
                    FlightLocationType.LOCATION_ARRIVAL, 1
            );

    private final Link<CrewLocationType, Crew, ReadOnlyItemManager<Crew>> crewLink;
    private final Link<PilotLocationType, Pilot, ReadOnlyItemManager<Pilot>> pilotLink;
    private final Link<PlaneLocationType, Plane, ReadOnlyItemManager<Plane>> planeLink;
    private final String name;
    private final String id;

    /**
     * Creates a Location object with the given name.
     * @param name name of the location
     */
    public Location(
            String name
    ) {
        this(
            UUID.randomUUID().toString(),
            name,
            new Link<>(
                    Crew.SHAPE_FOR_LOCATION,
                    GetUtil.getLazy(Model.class).map(Model::getCrewManager)
            ),
            new Link<>(
                    Pilot.SHAPE_FOR_LOCATION,
                    GetUtil.getLazy(Model.class).map(Model::getPilotManager)
            ),
            new Link<>(
                    Plane.SHAPE_FOR_LOCATION,
                    GetUtil.getLazy(Model.class).map(Model::getPlaneManager)
            )
        );
    }

    /**
     * Creates a Location object from the given id and name
     * @param id a unique id assigned to the location
     * @param name the name of the location
     * @param crewLink the link to the crews that stay in
     *                 this location
     * @param pilotLink the link to the pilot that stays
     *                  in this location
     * @param planeLink the link to the planes that stays in
     *                  this location
     */
    public Location(
            String id,
            String name,
            Link<CrewLocationType, Crew, ReadOnlyItemManager<Crew>> crewLink,
            Link<PilotLocationType, Pilot, ReadOnlyItemManager<Pilot>> pilotLink,
            Link<PlaneLocationType, Plane, ReadOnlyItemManager<Plane>> planeLink
    ) {
        this.id = id;
        this.name = name;
        this.crewLink = crewLink;
        this.pilotLink = pilotLink;
        this.planeLink = planeLink;
    }

    /**
     * Returns the name of the location in string.
     * @return the name of the location
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public List<String> getDisplayList() {
        return List.of(
                String.format("%s", name),
                String.format("%s: %s\n", "Plane", planeLink.toString()),
                String.format("%s: %s\n", "Pilots", pilotLink.toString()),
                String.format("%s: %s\n", "Crew", crewLink.toString())
        );
    }

    /**
     * Returns the crew link.
     *
     * @return the link to the crew
     */
    public Link<CrewLocationType, Crew, ReadOnlyItemManager<Crew>> getCrewLink() {
        return crewLink;
    }

    /**
     * Returns the pilot link.
     *
     * @return the link to the pilot
     */
    public Link<PilotLocationType, Pilot, ReadOnlyItemManager<Pilot>> getPilotLink() {
        return pilotLink;
    }

    /**
     * Returns the plane link.
     *
     * @return the link to the plane
     */
    public Link<PlaneLocationType, Plane, ReadOnlyItemManager<Plane>> getPlaneLink() {
        return planeLink;
    }

    /**
     * Returns true if both locations have the same name.
     * This defines a weaker notion of equality between two locations.
     */
    public boolean equals(Location otherLocation) {
        if (otherLocation == this) {
            return true;
        }

        return otherLocation != null
                && otherLocation.getId().equals(getId());
    }
}
