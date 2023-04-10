package wingman.storage.json.adapted;

import java.util.Deque;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import wingman.commons.exceptions.IllegalValueException;
import wingman.commons.util.GetUtil;
import wingman.model.Model;
import wingman.model.ReadOnlyItemManager;
import wingman.model.crew.Crew;
import wingman.model.link.Link;
import wingman.model.location.CrewLocationType;
import wingman.model.location.Location;
import wingman.model.location.PilotLocationType;
import wingman.model.location.PlaneLocationType;
import wingman.model.pilot.Pilot;
import wingman.model.plane.Plane;
import wingman.storage.json.JsonAdaptedModel;

/**
 * Represents a Jackson-friendly version of a Pilot.
 */
public class JsonAdaptedLocation implements JsonAdaptedModel<Location> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT =
            "Location's %s field is missing!";

    /**
     * The id of the location.
     */
    private final String id;

    /**
     * The name of the location.
     */
    private final String name;

    /**
     * Linked crews, i.e., crews that reside in this location.
     */
    private final Map<CrewLocationType, Deque<String>> crewLink;

    /**
     * Linked pilots
     */
    private final Map<PilotLocationType, Deque<String>> pilotLink;

    /**
     * Linked planes
     */
    private final Map<PlaneLocationType, Deque<String>> planeLink;

    /**
     * Constructs a {@code JsonAdaptedLocation} with the given location details.
     * This is intended for Jackson to use.
     *
     * @param id   The id of the location.
     * @param name The name of the location.
     * @param crewLink The link between locations to crews.
     * @param pilotLink The link between locations to pilots.
     * @param planeLink The link between locations to planes.
     */
    @JsonCreator
    public JsonAdaptedLocation(
            @JsonProperty("id") String id,
            @JsonProperty("name") String name,
            @JsonProperty("crewLink") Map<CrewLocationType, Deque<String>> crewLink,
            @JsonProperty("pilotLink") Map<PilotLocationType, Deque<String>> pilotLink,
            @JsonProperty("planeLink") Map<PlaneLocationType, Deque<String>> planeLink
    ) {
        this.id = id;
        this.name = name;
        this.crewLink = crewLink;
        this.pilotLink = pilotLink;
        this.planeLink = planeLink;
    }

    /**
     * Converts a given {@code Location} into this class for Jackson use.
     *
     * @param location The location to be converted.
     */
    public JsonAdaptedLocation(Location location) {
        this.id = location.getId();
        this.name = location.getName();
        this.crewLink = location.getCrewLink().getCopiedContents();
        this.pilotLink = location.getPilotLink().getCopiedContents();
        this.planeLink = location.getPlaneLink().getCopiedContents();
    }

    @Override
    public Location toModelType() throws IllegalValueException {
        if (id == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "id")
            );
        }

        if (name == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "name")
            );
        }

        Location location;

        Link<CrewLocationType, Crew, ReadOnlyItemManager<Crew>> linkCrew =
                Link.fromOrCreate(
                        Crew.SHAPE_FOR_LOCATION,
                        crewLink,
                        GetUtil.getLazy(Model.class)
                               .map(Model::getCrewManager)
                );

        Link<PilotLocationType, Pilot, ReadOnlyItemManager<Pilot>> linkPilot =
                Link.fromOrCreate(
                        Pilot.SHAPE_FOR_LOCATION,
                        pilotLink,
                        GetUtil.getLazy(Model.class)
                                .map(Model::getPilotManager)
                );

        Link<PlaneLocationType, Plane, ReadOnlyItemManager<Plane>> linkPlane =
                Link.fromOrCreate(
                        Plane.SHAPE_FOR_LOCATION,
                        planeLink,
                        GetUtil.getLazy(Model.class)
                                .map(Model::getPlaneManager)
                );

        location = new Location(
                id,
                name,
                linkCrew,
                linkPilot,
                linkPlane
        );

        return location;
    }
}
